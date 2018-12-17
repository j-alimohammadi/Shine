package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.PostView;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostViewDao extends DAO<PostView> {
    Optional<PostView> readPostViewByPostIdAndIpAddress(long postId, String ipAddress);
}
