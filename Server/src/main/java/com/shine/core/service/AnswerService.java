package com.shine.core.service;

import com.shine.core.domain.Answer;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface AnswerService {
    Answer createAnswer(Answer answer);

    Answer createAnswerFromId(Long id);

    Answer updateAnswer(Answer answer);

    void deleteAnswerById(Long id);

    Answer findAnswerById(Long id);

    List<Answer> findAnswers(int answerOffset, int answerLimit);
}
