package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Post;
import com.shine.core.domain.PostType;
import com.shine.core.search.Order;
import com.shine.core.search.OrderByParameter;
import com.shine.core.search.domain.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post> implements PostDao {


    @Override
    public <T extends Post> List<T> readFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postTypes) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = criteriaBuilder.createQuery(Post.class);

        Root<Post> postRoot = criteria.from(Post.class);

        List<Predicate> restrictions = new ArrayList<>();


        // query parameter
        final String query = searchCriteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            restrictions.add(
                    criteriaBuilder.like(criteriaBuilder.lower(postRoot.get("body")), '%' + query + '%')
            );

        }


        addPostTypeRestriction(postRoot, restrictions, postTypes);

        addSortBy(searchCriteria, postRoot);

        criteria.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Post> typedQuery = entityManager.createQuery(criteria);


        return typedQuery.getResultList();
    }

    private void addPostTypeRestriction(Root<Post> postRoot, List<Predicate> restrictions, List<PostType> postTypes) {
        List<String> postTypeStringList = postTypes
                .stream()
                .map(postType1 -> postType1.type)
                .collect(Collectors.toList());


        Predicate postTypePredicate = postRoot.get("postType").in(postTypeStringList);

        restrictions.add(postTypePredicate);

    }

    private void addSortBy(SearchCriteria searchCriteria, Path<? extends Post> post) {
        final String sortBy = searchCriteria.getSortBy();

        if (StringUtils.isNotBlank(sortBy)) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            List<javax.persistence.criteria.Order> sorts = new ArrayList<>();

            List<String> sortParams = Arrays.asList(sortBy.split(","));

            for (String sortParam : sortParams) {
                String[] parameters = sortParam.split(" ");

                if (OrderByParameter.isExist(parameters[0])) {
                    boolean isAscending = false;
                    if (parameters.length == 2) {
                        isAscending = Order.isAscending(parameters[1]);
                    }

                    String attributeName = parameters[0]
                            .replaceFirst("post.", "")
                            .replaceFirst("answer.", "");

                    if (isAscending) {
                        sorts.add(criteriaBuilder.asc(post.get(attributeName)));
                    } else {
                        sorts.add(criteriaBuilder.desc(post.get(attributeName)));
                    }

                } else {
                    continue;
                }


            }

        }


    }
}
