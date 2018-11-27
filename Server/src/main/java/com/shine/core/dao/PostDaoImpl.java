package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Post;
import com.shine.core.search.domain.SearchCriteria;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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
        ParameterExpression<String> titleParameter = criteriaBuilder.parameter(String.class);
        TypedQuery<Post> query = entityManager.createQuery(criteria.select())


        criteria.select(postRoot).


        return null;
    }
}
