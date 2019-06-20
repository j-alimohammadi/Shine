package com.shine.core.qa.service;

import com.shine.core.qa.domain.Answer;
import com.shine.core.qa.domain.Question;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface QuestionService {
    Question createQuestion(Question question);

    Question createQuestionFromId(Long questionId);

    Question updateQuestion(Question question);

    void deleteQuestionById(Long id);

    Optional<Question> findQuestionById(Long id);

    Question voteUp(Question question);

    Question voteDown(Question question);

    /**
     * Add view count based on:
     * <ul>
     * <li>If anonymous user, use IP address</li>
     * <li>If logged in user, use user name</li>
     * </ul>
     *
     * @param question
     * @return
     */
    Long addViewCountIfPossible(Question question);

    Answer acceptAnswer(Answer answer);

    Long addAnswerCount(Question question, Long count);

    Long subtractAnswerCount(Question question, Long count);
}
