package com.shine.core.qa.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.qa.domain.PostView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository("shinePostViewDaoImpl")
public class PostViewDaoImpl extends AbstractDao<PostView> implements PostViewDao {
    private final static Logger log = LoggerFactory.getLogger(PostViewDaoImpl.class);

    @Override
    public Optional<PostView> readPostViewByPostIdAndIpAddress(long postId, String ipAddress) {
        TypedQuery<PostView> typedQuery = entityManager.createNamedQuery("findPostViewByPostIdAndIpAddress", PostView.class);
        typedQuery.setParameter("ip", ipAddress)
                .setParameter("postId", postId);

        try {
            PostView postView = typedQuery.getSingleResult();
            return Optional.of(postView);
        } catch (NonUniqueResultException ex) {
            log.error("More than one result found for [{}]", PostView.class.getSimpleName());
            throw ex;
        } catch (NoResultException noResultException) {
            return Optional.empty();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }

    }

    @Override
    public Optional<PostView> readPostViewByPostIdAndUserId(long postId, long userId) {
        TypedQuery<PostView> typedQuery = entityManager.createNamedQuery("findPostViewByPostIdAndUserId", PostView.class);
        typedQuery.setParameter("userId", userId)
                .setParameter("postId", postId);

        try {
            PostView postView = typedQuery.getSingleResult();
            return Optional.of(postView);
        } catch (NonUniqueResultException ex) {
            log.error("More than one result found for [{}]", PostView.class.getSimpleName());
            throw ex;
        } catch (NoResultException noResultException) {
            return Optional.empty();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }


}
