package com.shine.core.service;

import com.shine.core.domain.Post;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostService {
    <T extends Post> T voteUp(Long postId);

    <T extends Post> T voteDown(Long postId);

}
