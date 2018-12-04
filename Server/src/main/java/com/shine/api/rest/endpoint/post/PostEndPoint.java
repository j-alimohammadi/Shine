package com.shine.api.rest.endpoint.post;

import com.shine.api.rest.wrapper.SearchResultWrapper;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchResult;
import com.shine.web.SearcServiceDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class PostEndPoint {

    @Resource
    private SearcServiceDTO searcServiceDTO;

    @Resource(name = "databaseSearchServiceImpl")
    private ShineSearchService shineSearchService;

    @RequestMapping(name = "/search", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public SearchResultWrapper searchInPosts(HttpServletRequest httpServletRequest) {
        SearchCriteria searchCriteria = searcServiceDTO.buildSearchCriteria(httpServletRequest);
        SearchResult searchResult = shineSearchService.searchPosts(searchCriteria);

        return null;


    }
}
