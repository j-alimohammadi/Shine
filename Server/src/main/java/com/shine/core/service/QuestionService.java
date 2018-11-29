package com.shine.core.service;

import com.shine.core.domain.Answer;
import com.shine.core.domain.Question;
import com.shine.core.search.OrderByParameter;

import java.util.List;
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

    List<Question> findQuestions(int questionOffset, int questionLimit, OrderByParameter orderByParameter);

    Question voteUp(Question question);

    Question voteDown(Question question);

    Answer acceptAnswer(Answer answer);

    Long addAnswerCount(Question question, Long count);

    Long subtractAnswerCount(Question question, Long count);
}
