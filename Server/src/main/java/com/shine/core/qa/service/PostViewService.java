package com.shine.core.qa.service;

import com.shine.core.qa.domain.PostView;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostViewService {
    Optional<PostView> findPostViewByPostIdAndUserId(long postId, long userId);

    Optional<PostView> findPostViewByPostIdAndIpAddress(long postId, String ipAddress);

    PostView createPostView(PostView postView);

}
