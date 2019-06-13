package com.shine.core.qa.dao;

import com.shine.common.persistence.PersistenceCommonConfig;
import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.Question;
import com.shine.core.qa.domain.Tag;
import com.shine.core.qa.domain.Tag_;
import com.shine.core.search.domain.SearchCriteria;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class TagDaoImpl extends AbstractDao<Tag> implements TagDao {
    private final static Logger log = LoggerFactory.getLogger(TagDaoImpl.class);

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

    @Override
    public Integer bulkSaveOrUpdateTags(List<Tag> tags) {
        for (int i = 0; i < tags.size(); i++) {
            createOrUpdate(tags.get(i));
            if (i % PersistenceCommonConfig.TransactionBatchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }

        return tags.size();
    }


    @Override
    public List<Tag> findFilteredTagsByCriteria(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = criteriaBuilder.createQuery(Tag.class);

        Root<Tag> tagRoot = criteria.from(Tag.class);

        List<Predicate> restrictions = new ArrayList<>();
        addSearchCriteria(searchCriteria, tagRoot, restrictions);
        criteria.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Tag> postTypedQuery = entityManager.createQuery(criteria);

        final int firstIndex = searchCriteria.getPageSize() * (searchCriteria.getPage() - 1);
        postTypedQuery.setFirstResult(firstIndex).setMaxResults(searchCriteria.getPageSize());

        return postTypedQuery.getResultList();

    }

    @Override
    public Long findFilteredTagsCountByCriteria(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Tag> tagRoot = criteria.from(Tag.class);
        criteria.select(criteriaBuilder.count(tagRoot));

        List<Predicate> restrictions = new ArrayList<>();
        addSearchCriteria(searchCriteria, tagRoot, restrictions);
        criteria.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Long> postTypedQuery = entityManager.createQuery(criteria);


        return postTypedQuery.getSingleResult();
    }

    private void addSearchCriteria(SearchCriteria searchCriteria, Root<Tag> tagRoot, List<Predicate> restrictions) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // search in the post body and question title
        final String query = searchCriteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Predicate searchInTag = criteriaBuilder.like(
                    criteriaBuilder.lower(tagRoot.get(Tag_.name)), '%' + query + '%');

            restrictions.add(searchInTag);
        }

    }

}
