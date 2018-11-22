package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Answer;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    @Override
    public List<Answer> readAnswer(int offset, int limit) {
        TypedQuery<Answer> answer = entityManager.createNamedQuery("readAnswer", Answer.class);
        answer.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(answer.getResultList());

    }

    @Override
    public List<Answer> readAnswerForQuestions(Long questionId, int offset, int limit) {
        TypedQuery<Answer> answer = entityManager.createNamedQuery("readAnswerForQuestion", Answer.class);

        answer.setParameter("questionId", questionId)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return ListUtils.emptyIfNull(answer.getResultList());

    }


}
