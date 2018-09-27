package com.shine.core.service;

import com.shine.core.domain.Question;
import com.shine.core.dto.QuestionRequestDTO;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface QuestionService {
    Question createQuestion(QuestionRequestDTO questionRequestDTO);
}
