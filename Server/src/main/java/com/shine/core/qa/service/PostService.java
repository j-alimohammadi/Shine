package com.shine.core.qa.service;

import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.PostType;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostService {
    <T extends Post> T voteUp(Long postId);

    <T extends Post> T voteDown(Long postId);

    List<Post> findFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postTypeList);

    Long findFilteredPostsCountByCriteria(SearchCriteria searchCriteria, List<PostType> postTypeList);
}
