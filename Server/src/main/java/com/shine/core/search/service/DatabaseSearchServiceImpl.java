package com.shine.core.search.service;

import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Resource(name = "databaseSearchServiceImpl")
public class DatabaseSearchServiceImpl implements ShineSearchService {
    @Override
    public SearchResult findResult(SearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public SearchResult findResultByQuery(SearchCriteria searchCriteria, String query) {

        return null;
    }
}
