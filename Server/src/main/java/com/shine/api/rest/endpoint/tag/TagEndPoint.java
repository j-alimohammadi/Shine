package com.shine.api.rest.endpoint.tag;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.wrapper.SearchResultWrapper;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;
import com.shine.web.search.SearchServiceDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagEndPoint extends BaseEndpoint {

    @Resource
    private SearchServiceDTO searchServiceDTO;

    @Resource(name = "databaseSearchServiceImpl")
    private ShineSearchService shineSearchService;


    @GetMapping("/search")
    public SearchResultWrapper searchTags(HttpServletRequest httpServletRequest) {
        SearchCriteria searchCriteria = searchServiceDTO.buildSearchCriteria(httpServletRequest);

        SearchResult searchResult = shineSearchService.searchTags(searchCriteria);

        SearchResultWrapper searchResultWrapper = applicationContext.getBean(SearchResultWrapper.class);
        searchResultWrapper.wrap(searchResult, httpServletRequest);

        return searchResultWrapper;

    }

}
