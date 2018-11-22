package com.shine.core.service;

import com.shine.core.domain.Answer;
import com.shine.core.domain.Question;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface QuestionService {
    Question createQuestion(Question question);

    Question createQuestionFromId(Long questionId);

    Question updateQuestion(Question question);

    void deleteQuestionById(Long id);

    Question findQuestionById(Long id);

    List<Question> findQuestions(int questionOffset, int questionLimit);

    Long voteUp(Question question);

    Long voteDown(Question question);

    Answer acceptAnswer(Answer answer);
}
