package com.shine.core.service;

import com.shine.core.domain.Post;
import org.springframework.stereotype.Service;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class PostServiceImpl implements PostService {

    // todo : check for race condition
    @Override
    public <T extends Post> T voteUp(T post) {
        long vote = post.getVote();
        vote++;
        post.setVote(vote);

        return post;

    }

    // todo : check for race condition
    @Override
    public <T extends Post> T voteDown(T post) {
        long vote = post.getVote();
        vote--;
        post.setVote(vote);

        return post;

    }
}
