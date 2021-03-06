package com.shine.core.qa.service;

import com.shine.core.qa.dao.PostDao;
import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.PostType;
import com.shine.core.search.domain.SearchCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostDao postDao;


    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public synchronized <T extends Post> T voteUp(Long postId) {
        Post post = postDao.find(postId)
                .orElseThrow(() -> {
                    return new RuntimeException(String.format("Post id [%s] not found", postId));
                });
        long vote = post.getVote();
        vote++;
        post.setVote(vote);
        return (T) postDao.createOrUpdate(post);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public synchronized <T extends Post> T voteDown(Long postId) {
        Post post = postDao.find(postId)
                .orElseThrow(() -> {
                    return new RuntimeException(String.format("Post id [%s] not found", postId));
                });
        long vote = post.getVote();
        vote--;
        post.setVote(vote);
        return (T) postDao.createOrUpdate(post);
    }

    @Transactional
    @Override
    public List<Post> findFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postTypeList) {
        return postDao.readFilteredPostsByCriteria(searchCriteria, postTypeList);
    }

    @Transactional
    @Override
    public Long findFilteredPostsCountByCriteria(SearchCriteria searchCriteria, List<PostType> postTypeList) {
        return postDao.findFilteredPostCountByCriteria(searchCriteria, postTypeList);
    }
}
