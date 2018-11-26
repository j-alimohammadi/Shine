package com.shine.api.rest.endpoint.post;

import com.shine.api.rest.wrapper.SearchResultHolderWrapper;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.domain.SearchCriteria;
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
    public SearchResultHolderWrapper searchInPosts(HttpServletRequest httpServletRequest) {
        SearchCriteria searchCriteria = searcServiceDTO.buildSearchCriteria(httpServletRequest);

        shineSearchService.findResult(searchCriteria);

    }
}
