package com.shine.core.search.service;

import com.shine.core.search.ShineSearchService;
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

    @Resource
    private PostService postService;

    @Override
    public SearchResult findResult(SearchCriteria searchCriteria) {
        postService.
        return null;
    }


}
