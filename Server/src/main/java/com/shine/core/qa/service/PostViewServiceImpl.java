package com.shine.core.qa.service;

import com.shine.core.qa.dao.PostViewDao;
import com.shine.core.qa.domain.PostView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shinePostViewServiceImpl")
public class PostViewServiceImpl implements PostViewService {

    @Resource(name = "shinePostViewDaoImpl")
    private PostViewDao postViewDao;

    @Override
    public Optional<PostView> findPostViewByPostIdAndUserId(long postId, long userId) {
        return postViewDao.readPostViewByPostIdAndUserId(postId, userId);
    }

    @Override
    public Optional<PostView> findPostViewByPostIdAndIpAddress(long postId, String ipAddress) {
        return postViewDao.readPostViewByPostIdAndIpAddress(postId, ipAddress);
    }

    @Transactional
    @Override
    public PostView createPostView(PostView postView) {
        return postViewDao.createOrUpdate(postView);
    }

}
