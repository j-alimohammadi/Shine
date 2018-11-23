package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.Question;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface QuestionDao extends DAO<Question> {
    List<Question> findQuestionOrderByUpdate(int offset, int limit);

    List<Question> findQuestionOrderByVotes(int offset, int limit);

    List<Question> findQuestionOrderByAnswerCount(int offset, int limit);

    List<Question> findQuestionOrderByViewCount(int offset, int limit);

    int rejectAllAnswerForQuestion(Long questionId);
}
