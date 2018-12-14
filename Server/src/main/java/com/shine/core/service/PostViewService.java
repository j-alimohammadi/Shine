package com.shine.core.service;

import com.shine.core.domain.PostView;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostViewService {
    PostView findPostViewByPostIdAndUserId(long postId, long userId);

    Optional<PostView> findPostViewByPostIdAndIpAddress(long postId, String ipAddress);

    PostView createPostView(PostView postView);

}
