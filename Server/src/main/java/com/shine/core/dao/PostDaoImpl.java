package com.shine.core.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.domain.Post;
import com.shine.core.search.domain.SearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository
public class PostDaoImpl extends AbstractDao<Post> implements PostDao {
    @Override
    public List<Post> readFilteredProductByCriteria(SearchCriteria searchCriteria) {
        return null;
    }
}
