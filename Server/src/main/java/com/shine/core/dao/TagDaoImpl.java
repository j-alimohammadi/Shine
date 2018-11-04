package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {
    @Override
    public List<Tag> readTagsById(List<Long> tagIds) {
        TypedQuery<Tag> query = entityManager.createNamedQuery(Tag.FIND_TAGS_BY_ID, Tag.class);
        query.setParameter("tagId", tagIds);
        return ListUtils.emptyIfNull(query.getResultList());

    }

    @Override
    public List<Tag> readTagByName(List<String> tagNames) {
        TypedQuery<Tag> query = entityManager.createNamedQuery(Tag.FIND_TAGS_BY_NAME, Tag.class);
        query.setParameter("tagNames", tagNames);
        return ListUtils.emptyIfNull(query.getResultList());

    }

    @Override
    public Long readTagCountById(List<Long> tagIds) {
        TypedQuery<Long> query = entityManager.createNamedQuery(Tag.FIND_TAGS_COUNT_BY_ID, Long.class);
        query.setParameter("tagId", tagIds);
        return query.getSingleResult();

    }

    @Override
    public List<Tag> readTagsForQuestion(Question question) {
        TypedQuery<Tag> query = entityManager.createNamedQuery("readTagByQuestion", Tag.class);
        query.setParameter("quesyionIds", question.getId());
        return query.getResultList();

    }

}
