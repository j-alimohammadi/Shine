package com.shine.core.qa.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.PostType;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PostDao extends DAO<Post> {

     List<Post> readFilteredPostsByCriteria(SearchCriteria searchCriteria, List<PostType> postType);

     Long findFilteredPostCountByCriteria(SearchCriteria searchCriteria, List<PostType> postTypes);

}
