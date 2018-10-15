package com.shine.core.service;

import com.shine.core.domain.Answer;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface AnswerService {
    Answer createAnswer(Answer answer);

    Answer createAnswerFromId(Long id);
}
