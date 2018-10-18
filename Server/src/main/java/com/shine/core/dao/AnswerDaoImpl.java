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
    public List<Answer> findAnswer(int offset, int limit) {
        TypedQuery<Answer> answer = entityManager.createNamedQuery("findAnswer", Answer.class);
        answer.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(answer.getResultList());

    }
}
