package com.shine.core.search;

import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineSearchService {

    SearchResult searchPosts(SearchCriteria searchCriteria);

    SearchResult searchTags(SearchCriteria searchCriteria);

}
