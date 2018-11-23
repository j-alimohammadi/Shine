package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Answer;
import com.shine.core.domain.Question;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Repository
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    @Override
    public List<Question> findQuestionOrderByUpdate(int offset, int limit) {
        TypedQuery<Question> question = entityManager.createNamedQuery("findQuestionOrderByUpdate", Question.class);
        question.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(question.getResultList());

    }

    @Override
    public List<Question> findQuestionOrderByVotes(int offset, int limit) {
        TypedQuery<Question> question = entityManager.createNamedQuery("findQuestionOrderByUpdate", Question.class);
        question.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(question.getResultList());

    }

    @Override
    public List<Question> findQuestionOrderByAnswerCount(int offset, int limit) {
        TypedQuery<Question> question = entityManager.createNamedQuery("findQuestionOrderByAnswerCount", Question.class);
        question.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(question.getResultList());

    }

    @Override
    public List<Question> findQuestionOrderByViewCount(int offset, int limit) {
        TypedQuery<Question> question = entityManager.createNamedQuery("findQuestionOrderByViewCount", Question.class);
        question.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(question.getResultList());

    }

    @Override
    public int rejectAllAnswerForQuestion(Long questionId) {
        Query rejectAllAnswers = entityManager.createNamedQuery("rejectAllAnswers");

        rejectAllAnswers.setParameter("questionId", questionId);

        return rejectAllAnswers.executeUpdate();

    }
}
