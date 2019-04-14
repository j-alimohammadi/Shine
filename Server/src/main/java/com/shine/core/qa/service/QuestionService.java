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

    Long addViewCountIfPossible(Question question);

    Answer acceptAnswer(Answer answer);

    Long addAnswerCount(Question question, Long count);

    Long subtractAnswerCount(Question question, Long count);
}
