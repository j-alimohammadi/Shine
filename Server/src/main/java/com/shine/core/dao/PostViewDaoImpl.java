package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.PostView;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Repository("shinePostViewDaoImpl")
public class PostViewDaoImpl extends AbstractDao<PostView> implements PostViewDao {
    @Override
    public Optional<PostView> readPostViewByPostIdAndIpAddress(long postId, String ipAddress) {
        return Optional.empty();
    }
}
