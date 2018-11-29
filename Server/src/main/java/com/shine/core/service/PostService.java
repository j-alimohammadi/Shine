package com.shine.core.service;

import com.shine.core.domain.Post;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostService {
    <T extends Post> T voteUp(Long postId);

    <T extends Post> T voteDown(Long postId);

    <T extends Post> List<T> findFilteredPostsByCriteria(SearchCriteria searchCriteria);


}
