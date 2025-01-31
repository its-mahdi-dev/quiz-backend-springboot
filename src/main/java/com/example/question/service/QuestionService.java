package com.example.question.service;

import com.example.question.dto.AnswerDTO;
import com.example.question.dto.PlayerQuestionDTO;
import com.example.question.dto.PlayerScoreDTO;
import com.example.question.dto.QuestionDTO;
import com.example.question.dto.UserDTO;
import com.example.question.exception.BadRequestException;
import com.example.question.model.Answer;
import com.example.question.model.Follow;
import com.example.question.model.Question;
import com.example.question.model.User;
import com.example.question.model.UserAnswer;
import com.example.question.model.UserType;
import com.example.question.repository.AnswerRepository;
import com.example.question.repository.CategoryRepository;
import com.example.question.repository.FollowRepository;
import com.example.question.repository.QuestionRepository;
import com.example.question.repository.UserAnswerRepository;
import com.example.question.repository.UserRepository;
import com.example.question.utils.DateUtils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FollowRepository followRepository;

    public Map<String, Object> getPlayerQuestions(Long userId, int page, int limit, String search) {
        try {
            Pageable pageable = PageRequest.of(page - 1, limit);

            List<UserAnswer> userAnswers = userAnswerRepository.findPlayerQuestions(userId, search, pageable);
            long totalAnswers = userAnswerRepository.countPlayerQuestions(userId, search);

            List<PlayerQuestionDTO> data = new ArrayList<>();
            for (UserAnswer userAnswer : userAnswers) {
                Question question = userAnswer.getQuestion();
                if (question == null)
                    continue;

                PlayerQuestionDTO questionData = new PlayerQuestionDTO();
                questionData.setCategoryName(question.getCategory().getName());
                questionData.setBody(question.getBody());
                questionData.setCorrectAnswerId(question.getCorrectAnswerId());
                questionData.setDuration((long) question.getDuration());
                AnswerDTO answerDTO = new AnswerDTO();
                answerDTO.setBody(userAnswer.getAnswer().getBody());
                answerDTO.setId(userAnswer.getAnswer().getId());
                answerDTO.setOrder(userAnswer.getAnswer().getOrder());
                questionData.setAnswer(answerDTO);
                List<AnswerDTO> answers = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    AnswerDTO answerDto = new AnswerDTO();
                    answerDto.setBody(answer.getBody());
                    answerDto.setId(answer.getId());
                    answerDto.setOrder(answer.getOrder());
                    answers.add(answerDto);
                }
                questionData.setAnswers(answers);
                questionData.setSelectedAnswer(userAnswer.getAnswerId());

                data.add(questionData);
            }

            int totalPages = (int) Math.ceil((double) totalAnswers / limit);

            Map<String, Object> response = new HashMap<>();
            response.put("data", data);
            response.put("meta", Map.of(
                    "currentPage", page,
                    "totalPages", totalPages,
                    "totalItems", totalAnswers));

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Map<String, Object> getPlayersScores(Long userId, int page, int limit, String search) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<User> users = userRepository.findByTypeAndSearch(UserType.player, search, pageable);

        List<PlayerScoreDTO> playerScoreDTOs = users.stream()
                .map(user -> {
                    PlayerScoreDTO dto = new PlayerScoreDTO();
                    dto.setId(user.getId());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setScore(user.getScore());

                    int correctAnswers = 0;
                    int wrongAnswers = 0;
                    List<UserAnswer> userAnswers = userAnswerRepository.findByUserId(user.getId());
                    for (UserAnswer userAnswer : userAnswers) {
                        if (userAnswer.getAnswerId().equals(userAnswer.getQuestion().getCorrectAnswerId())) {
                            correctAnswers++;
                        } else {
                            wrongAnswers++;
                        }
                    }
                    dto.setCorrectAnswers(correctAnswers);
                    dto.setWrongAnswers(wrongAnswers);

                    Optional<Follow> follow = followRepository.findByFollowingIdAndFollowerId(userId, user.getId());
                    dto.setIsFollowing(follow.isPresent());
                    return dto;
                }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("data", playerScoreDTOs);
        response.put("meta", generateMeta(users.getTotalElements(), page, limit));
        return response;
    }

    public QuestionDTO getSingleQuestion(Long userId, Long questionId) {
        String cacheKey = "questions:" + userId + ":" + questionId;

        Long duration = (Long) redisService.get(cacheKey);
        if (duration == null) {
            redisService.set(cacheKey, System.currentTimeMillis());
        }

        Optional<Question> question = questionRepository.findById(questionId);
        if (question.isPresent()) {
            QuestionDTO dto = new QuestionDTO();
            dto.setId(question.get().getId());
            dto.setBody(question.get().getBody());
            dto.setStartTime(question.get().getStartTime().toString());
            dto.setEndTime(question.get().getEndTime().toString());
            dto.setDuration(calculateDuration(question.get(), duration));
            dto.setCategoryName(question.get().getCategory().getName());

            List<AnswerDTO> answers = question.get().getAnswers().stream()
                    .map(answer -> {
                        AnswerDTO answerDTO = new AnswerDTO();
                        answerDTO.setId(answer.getId());
                        answerDTO.setBody(answer.getBody());
                        answerDTO.setOrder(answer.getOrder());
                        return answerDTO;
                    }).collect(Collectors.toList());
            dto.setAnswers(answers);
            UserDTO userDTO = new UserDTO();
            userDTO.setFirst_name(question.get().getUser().getFirstName());
            userDTO.setLast_name(question.get().getUser().getLastName());
            userDTO.setId(question.get().getUser().getId());
            dto.setUser(userDTO);
            return dto;
        }
        throw new EntityNotFoundException("Question not found");
    }

    @Transactional
    public Map<String, Object> getDesignerQuestions(int page, int limit, String search) {
        Pageable pageable = PageRequest.of(page - 1, limit);

        Page<Question> questions = questionRepository.findDesignerQuestions(search, pageable);

        List<QuestionDTO> questionDTOs = questions.stream()
                .map(question -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(question.getId());
                    dto.setBody(question.getBody());
                    dto.setCategoryName(question.getCategory().getName());
                    dto.setStartTime(question.getStartTime().toString());
                    dto.setEndTime(question.getEndTime().toString());
                    List<AnswerDTO> answerDTOs = question.getAnswers().stream()
                            .map(answer -> {
                                AnswerDTO answerDTO = new AnswerDTO();
                                answerDTO.setId(answer.getId());
                                answerDTO.setBody(answer.getBody());
                                answerDTO.setOrder(answer.getOrder());
                                return answerDTO;
                            }).collect(Collectors.toList());

                    dto.setAnswers(answerDTOs);

                    UserDTO user = new UserDTO();
                    user.setFirst_name(question.getUser().getFirstName());
                    user.setId(question.getUser().getId());
                    user.setLast_name(question.getUser().getLastName());
                    dto.setUser(user);

                    dto.setCorrectAnswerId(question.getCorrectAnswerId());
                    return dto;
                }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("data", questionDTOs);
        response.put("meta", generateMeta(questions.getTotalElements(), page, limit));
        return response;
    }

    @Transactional
    public Long getRandomQuestion(Long userId) {
        LocalDateTime now = LocalDateTime.now();

        List<Long> answeredQuestionIds = userAnswerRepository.findAnsweredQuestionIdsByUserId(userId);

        Pageable pageable = PageRequest.of(0, 1);

        Page<Question> page = questionRepository.findRandomQuestionNotAnsweredByUser(answeredQuestionIds, now,
                pageable);

        return page.hasContent() ? page.getContent().get(0).getId() : null;
    }

    @Transactional
    public Question createQuestion(QuestionDTO questionDTO, Long userId) {
        if (questionDTO.getAnswers().size() != 4) {
            throw new IllegalArgumentException("داده های ورودی معتبر نمی باشد.");
        }
        Question question = new Question();
        question.setBody(questionDTO.getBody());
        question.setDuration(questionDTO.getDuration());
        question.setStartTime(DateUtils.parseDateTime(questionDTO.getStartTime()));
        question.setEndTime(DateUtils.parseDateTime(questionDTO.getEndTime()));
        question.setCategoryId(questionDTO.getCategoryId());
        question.setUserId(userId);

        question = questionRepository.save(question);

        Long correctAnswerId = null;

        // Create Answers
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < questionDTO.getAnswers().size(); i++) {
            AnswerDTO answerData = questionDTO.getAnswers().get(i);
            Answer answer = new Answer();
            answer.setBody(answerData.getBody());
            answer.setOrder(i);
            answer.setQuestionId(question.getId());

            if (i == questionDTO.getCorrectAnswerId()) {
                correctAnswerId = answer.getId();
            }
            answers.add(answer);
        }

        List<Answer> savedAnswers = answerRepository.saveAll(answers);

        if (questionDTO.getCorrectAnswerId() != null && questionDTO.getCorrectAnswerId() >= 0
                && questionDTO.getCorrectAnswerId() < savedAnswers.size()) {
            int correctAnswerIndex = questionDTO.getCorrectAnswerId().intValue();
            correctAnswerId = savedAnswers.get(correctAnswerIndex).getId();
        }

        if (correctAnswerId != null) {
            question.setCorrectAnswerId(correctAnswerId);
            questionRepository.save(question);
        }

        return question;
    }

    private Map<String, Object> generateMeta(long totalItems, int page, int limit) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("currentPage", page);
        meta.put("totalPages", (int) Math.ceil((double) totalItems / limit));
        meta.put("totalItems", totalItems);
        return meta;
    }

    private Integer calculateDuration(Question question, Long duration) {
        long newDuration = (duration != null) ? System.currentTimeMillis() - duration : 0;
        return (int) (question.getDuration() - Math.ceil((double) newDuration / 1000));
    }
}
