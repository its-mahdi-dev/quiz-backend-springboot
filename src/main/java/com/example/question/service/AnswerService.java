package com.example.question.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.question.exception.BadRequestException;
import com.example.question.model.*;
import com.example.question.repository.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private RedisService redisService;

    @Transactional
    public String submitPlayerAnswer(Long userId, Long questionId, Long answerId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new BadRequestException("سوال پیدا نشد");
        }

        Question question = questionOptional.get();
        LocalDateTime now = LocalDateTime.now();

        if (question.getStartTime().isAfter(now)) {
            throw new BadRequestException("هنوز سوال شروع نشده!");
        }
        if (question.getEndTime().isBefore(now)) {
            throw new BadRequestException("زمان پاسخگویی به این سوال تموم شده!");
        }

        Optional<UserAnswer> userAnswerOptional = userAnswerRepository.findByUserIdAndQuestionId(userId, questionId);
        if (userAnswerOptional.isPresent()) {
            throw new BadRequestException("شما قبلا به این سوال پاسخ داده اید");
        }

        String cacheKey = "questions:" + userId + ":" + questionId;
        Long duration = (Long) redisService.get(cacheKey);
        if (duration != null) {
            long newDuration = (System.currentTimeMillis() - duration) / 1000;
            if (newDuration > question.getDuration()) {
                throw new BadRequestException("زمان پاسخگویی به این سوال تموم شده!"+ System.currentTimeMillis());
            }
        } else {
            throw new BadRequestException("شما وارد این سوال نشده اید" );
        }

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setQuestionId(questionId);
        userAnswer.setAnswerId(answerId);
        userAnswer.setUserId(userId);

        userAnswerRepository.save(userAnswer);

        return "پاسخ شما با موفقیت ثبت شد";
    }
}
