package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Question;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Repository
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    @Override
    public List<Question> findQuestion(int offset, int limit) {
        TypedQuery<Question> question = entityManager.createNamedQuery("findQuestion", Question.class);
        question.setFirstResult(offset).setMaxResults(limit);
        return ListUtils.emptyIfNull(question.getResultList());
    }
}
