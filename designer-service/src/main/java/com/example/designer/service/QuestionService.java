package com.example.designer.service;

import com.example.designer.dto.AnswerDTO;
import com.example.designer.dto.PlayerQuestionDTO;
import com.example.designer.dto.PlayerScoreDTO;
import com.example.designer.dto.QuestionDTO;
import com.example.designer.dto.UserDTO;
import com.example.designer.exception.BadRequestException;
import com.example.designer.model.Answer;
import com.example.designer.model.Follow;
import com.example.designer.model.Question;
import com.example.designer.model.User;
import com.example.designer.model.UserAnswer;
import com.example.designer.model.UserType;
import com.example.designer.repository.AnswerRepository;
import com.example.designer.repository.CategoryRepository;
import com.example.designer.repository.QuestionRepository;
import com.example.designer.utils.DateUtils;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private AnswerRepository answerRepository;


    
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
