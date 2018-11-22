package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.Question;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface QuestionDao extends DAO<Question> {
    List<Question> findQuestion(int offset, int limit);

    int rejectAllAnswerForQuestion(Long questionId);
}
