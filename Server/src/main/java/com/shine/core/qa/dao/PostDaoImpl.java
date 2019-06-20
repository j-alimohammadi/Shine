package com.shine.core.qa.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.qa.domain.*;
import com.shine.core.search.domain.OrderBy;
import com.shine.core.search.domain.SearchCriteria;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post> implements PostDao {
    private final static Logger log = LoggerFactory.getLogger(PostDaoImpl.class);

    @Override
    public List<Post> readFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postTypes) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = criteriaBuilder.createQuery(Post.class);

        Root<Post> postRoot = criteria.from(Post.class);

        List<Predicate> restrictions = new ArrayList<>();

        addSearchCriteria(searchCriteria, postRoot, restrictions);
        addPostTypeRestriction(postRoot, restrictions, postTypes);
        addSortBy(searchCriteria, postRoot, criteria);

        criteria.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Post> postTypedQuery = entityManager.createQuery(criteria);
        final int firstIndex = searchCriteria.getPageSize() * (searchCriteria.getPage() - 1);
        postTypedQuery.setFirstResult(firstIndex).setMaxResults(searchCriteria.getPageSize());

        return postTypedQuery.getResultList();
    }

    @Override
    public Long findFilteredPostCountByCriteria(SearchCriteria searchCriteria, List<PostType> postTypes) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);

        Root<Post> postRoot = criteria.from(Post.class);

        List<Predicate> restrictions = new ArrayList<>();

        criteria.select(criteriaBuilder.count(postRoot));

        addSearchCriteria(searchCriteria, postRoot, restrictions);
        addPostTypeRestriction(postRoot, restrictions, postTypes);
        criteria.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Long> postTypedQuery = entityManager.createQuery(criteria);

        return postTypedQuery.getSingleResult();
    }


    // todo: remove duplicated code
    private void addSearchCriteria(SearchCriteria searchCriteria, Root<Post> postRoot, List<Predicate> restrictions) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // search in the post body and question title
        final String query = searchCriteria.getQuery();
        if (StringUtils.isNotBlank(query)) {
            Predicate searchInBody = criteriaBuilder.like(
                    criteriaBuilder.lower(postRoot.get(Post_.body)), '%' + query + '%');

            Predicate searchInQuestionTitle = criteriaBuilder.and(
                    criteriaBuilder.equal(postRoot.type(), Question.class),
                    criteriaBuilder.like(((Root<Question>) ((Root<?>) postRoot)).get(Question_.title), '%' + query + '%'));

            restrictions.add(criteriaBuilder.or(searchInBody, searchInQuestionTitle));
        }

        List<String> equalValues = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : searchCriteria.getFilterCriteria().entrySet()) {

            // find post type according to last separated by dot section
            List<String> searchPath = Arrays.asList(entry.getKey().split("\\.", 2));
            final String postTypeString = searchPath.get(0);
            String attributeName = searchPath.get(1);

            PostType postType = PostType.getPostType(postTypeString);

            if (Objects.isNull(postType)) {
                log.warn("Post type [{}] not found", postTypeString);
                continue;
            }

            Root<? extends Post> root = changeRootAccordingToPostType(postRoot, postType);

            Path<?> pathToUse = root;

            // if we have tag, join with tags
            if (attributeName.equalsIgnoreCase("tagList.name")) {
                pathToUse = ((Root<Question>)root).join(Question_.tagList);
                attributeName = attributeName.substring("tagList.".length());
            }

            // todo: add other restrictions for example: range

            // add restrictions based on equality with parameter value
            equalValues.addAll(Arrays.asList(entry.getValue()));

            if (CollectionUtils.isNotEmpty(equalValues)) {
                restrictions.add(pathToUse.get(attributeName).in(equalValues));
            }


        }
    }

    private void addPostTypeRestriction(Root<Post> postRoot, List<Predicate> restrictions, List<PostType> postTypes) {
        List<Class> postTypeList = postTypes
                .stream()
                .map(postType1 -> postType1.typeClass)
                .collect(Collectors.toList());

        Predicate postTypePredicate = postRoot.type().in(postTypeList);
        restrictions.add(postTypePredicate);

    }

    private void addSortBy(SearchCriteria searchCriteria, Root<Post> postRoot, CriteriaQuery<Post> criteriaQuery) {
        final String sortBy = searchCriteria.getSortBy();
        Path<? extends Post> path;

        if (StringUtils.isNotBlank(sortBy)) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            List<Order> orders = new ArrayList<>();

            List<String> sortParams = Arrays.asList(sortBy.split(","));

            for (String sortParam : sortParams) {
                String[] parameters = sortParam.split(" ");

                boolean isAscending = false;
                if (parameters.length == 2) {
                    isAscending = OrderBy.isAscending(parameters[1]);
                }

                List<String> searchPath = Arrays.asList(parameters[0].split("\\."));
                final String postTypeString = searchPath.get(0);
                final String attributeName = searchPath.get(1);

                PostType postType = PostType.getPostType(postTypeString);

                if (Objects.isNull(postType)) {
                    log.warn("Post type [{}] not found", postTypeString);
                    continue;
                }

                path = changeRootAccordingToPostType(postRoot, postType);

                if (isAscending) {
                    orders.add(criteriaBuilder.asc(path.get(attributeName)));
                } else {
                    orders.add(criteriaBuilder.desc(path.get(attributeName)));
                }
            }

            criteriaQuery.orderBy(orders);
        }

    }

    private Root<? extends Post> changeRootAccordingToPostType(Root<Post> postRoot, PostType postType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        switch (postType) {
            case ANSWER:
                return criteriaBuilder.treat(postRoot, Answer.class);
            case QUESTION:
                return criteriaBuilder.treat(postRoot, Question.class);

            default:
                return postRoot;
        }


    }
}
