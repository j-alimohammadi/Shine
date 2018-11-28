package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Post;
import com.shine.core.search.domain.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post> implements PostDao {

    @Override
    public List<Post> readFilteredPostByCriteria(SearchCriteria searchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = criteriaBuilder.createQuery(Post.class);

        Root<Post> postRoot = criteria.from(Post.class);
        TypedQuery<Post> typedQuery = entityManager.createQuery(criteria);
        List<Predicate> restrictions = new ArrayList<>();


        // query parameter
        final String query = searchCriteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            ParameterExpression<String> bodyParameter = criteriaBuilder.parameter(String.class);
            typedQuery.setParameter(bodyParameter, '%' + query + '%');

            restrictions.add(
                    criteriaBuilder.like(criteriaBuilder.lower(postRoot.get("body")), bodyParameter)
            );

        }


        criteria.select(postRoot).where(restrictions.toArray(new Predicate[0]));
        addSortBy(searchCriteria, criteriaBuilder, restrictions);


        return typedQuery.getResultList();
    }

    private void addSortBy(SearchCriteria searchCriteria,
                           CriteriaBuilder criteriaBuilder,
                           List<Predicate> restrictions) {
        final String sortQuery = searchCriteria.getSortQuery();


    }
}
