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
    public PostView findPostViewByPostIdAndUserId(long postId, long userId) {
        return null;
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
