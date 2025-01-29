package com.example.question.service;

import com.example.question.model.Answer;
import com.example.question.model.Question;
import com.example.question.model.User;
import com.example.question.model.UserAnswer;
import com.example.question.model.UserType;
import com.example.question.repository.QuestionRepository;
import com.example.question.repository.UserAnswerRepository;
import com.example.question.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private RedisService redisService;

    public Map<String, Object> getPlayerQuestions(Long userId, int page, int limit, String search) {
        try {
            Pageable pageable = PageRequest.of(page - 1, limit);

            List<UserAnswer> userAnswers = userAnswerRepository.findPlayerQuestions(userId, search, pageable);
            long totalAnswers = userAnswerRepository.countPlayerQuestions(userId, search);

            List<Map<String, Object>> data = new ArrayList<>();
            for (UserAnswer userAnswer : userAnswers) {
                Question question = userAnswer.getQuestion();
                if (question == null)
                    continue;

                Map<String, Object> questionData = new HashMap<>();
                questionData.put("category_id", question.getCategoryId());
                questionData.put("body", question.getBody());
                questionData.put("correct_answer_id", question.getCorrectAnswerId());
                questionData.put("duration", question.getDuration());

                Map<String, Object> categoryData = new HashMap<>();
                if (question.getCategory() != null) {
                    categoryData.put("name", question.getCategory().getName());
                }
                questionData.put("category", categoryData);
                List<Map<String, Object>> answers = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    answers.add(getAnswer(answer));
                }
                questionData.put("answers", answers);
                questionData.put("answer", getAnswer(userAnswer.getAnswer()));
                questionData.put("selected_answer", userAnswer.getAnswerId());

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

    Map<String, Object> getAnswer(Answer answer) {
        Map<String, Object> new_answer = new HashMap<>();
        new_answer.put("body", answer.getBody());
        new_answer.put("order", answer.getOrder());
        return new_answer;
    }

    public Map<String, Object> getPlayersScores(int page, int limit, String search) {
        try {
            Pageable pageable = PageRequest.of(page - 1, limit);

            Page<User> users = userRepository.findByTypeAndSearch(UserType.player, search, pageable);
            
            List<Map<String, Object>> newPlayers = new ArrayList<>();

            for (User player : users.getContent()) {
                Map<String, Object> playerData = new HashMap<>();
                playerData.put("id", player.getId());
                playerData.put("first_name", player.getFirstName());
                playerData.put("last_name", player.getLastName());
                playerData.put("score", player.getScore());
                playerData.put("wrong_answers", 0);
                playerData.put("correct_answers", 0);

                List<UserAnswer> userAnswers = userAnswerRepository.findByUserId(player.getId());
                for (UserAnswer userAnswer : userAnswers) {
                    if (userAnswer.getAnswerId().equals(userAnswer.getQuestion().getCorrectAnswerId())) {
                        playerData.put("correct_answers", (int) playerData.get("correct_answers") + 1);
                    } else {
                        playerData.put("wrong_answers", (int) playerData.get("wrong_answers") + 1);
                    }
                }

                newPlayers.add(playerData);
            }

            int totalPages = (int) Math.ceil((double) users.getTotalElements() / limit);

            Map<String, Object> response = new HashMap<>();
            response.put("data", newPlayers);
            response.put("meta", Map.of(
                    "currentPage", page,
                    "totalPages", totalPages,
                    "totalItems", users.getTotalElements()
            ));

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    // public Question getSingleQuestion(Long userId, Long questionId) {
    // String cacheKey = "questions:" + userId + ":" + questionId;
    // Long duration = (Long) redisService.get(cacheKey);

    // if (duration == null) {
    // redisService.set(cacheKey, System.currentTimeMillis());
    // }

    // Question question = questionRepository.findByIdWithDetails(questionId)
    // .orElseThrow(() -> new RuntimeException("Question not found"));

    // long newDuration = duration != null ? System.currentTimeMillis() - duration :
    // 0;
    // question.setDuration(question.getDuration() - (int) Math.ceil(newDuration /
    // 1000.0));

    // return question;
    // }

    // public Long getRandomQuestion(Long userId) {
    // List<Long> answeredQuestionIds =
    // userAnswerRepository.findAnsweredQuestionIds(userId);

    // return questionRepository.findRandomQuestionId(answeredQuestionIds, new
    // Date())
    // .orElse(null);
    // }
}
