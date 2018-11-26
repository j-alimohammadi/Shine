package com.shine.core.search.service;

import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("databaseSearchServiceImpl")
public class DatabaseSearchServiceImpl implements ShineSearchService {
    @Override
    public SearchResult findResult(SearchCriteria searchCriteria) {
        return null;
    }

}
