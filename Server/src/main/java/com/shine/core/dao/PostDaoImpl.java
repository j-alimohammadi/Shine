package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Post;
import com.shine.core.search.domain.SearchCriteria;
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


        // query parameters
        ParameterExpression<String> bodyParameter = criteriaBuilder.parameter(String.class);


        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower()like(criteriaBuilder.lo))
        ))


        TypedQuery<Post> query = entityManager.createQuery(

                .where(criteriaBuilder.like(postRoot.get("body"), bodyParameter))
        );

        addSortBy()


        query.setParameter(bodyParameter, searchCriteria.getQuery());

        return query.getResultList();
    }

    private void addSortBy() {

    }
}
