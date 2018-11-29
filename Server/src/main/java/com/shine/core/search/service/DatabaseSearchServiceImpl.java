package com.shine.core.search.service;

import com.shine.core.search.ShineSearchService;
import com.shine.core.search.dao.SearchFieldDao;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;
import com.shine.core.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("databaseSearchServiceImpl")
public class DatabaseSearchServiceImpl implements ShineSearchService {

    @Resource(name = "searchFieldDaoImpl")
    private SearchFieldDao searchFieldDao;

    @Resource
    private PostService postService;


    @Override
    public SearchResult findResult(SearchCriteria searchCriteria) {

        setFilterCriteria(searchCriteria);

        return postService.findFilteredPostsByCriteria(searchCriteria);
    }

    private void setFilterCriteria(SearchCriteria searchCriteria) {

        // set post type
        searchFieldDao.readFieldByAbbreviation()
    }


}
