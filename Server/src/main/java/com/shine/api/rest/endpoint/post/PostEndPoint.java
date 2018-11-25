package com.shine.api.rest.endpoint.post;

import com.shine.api.rest.wrapper.SearchResultHolderWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

public class PostEndPoint {

    @RequestMapping(name = "/search")
    public SearchResultHolderWrapper searchInPosts(HttpServletRequest httpServletRequest) {

    }
}
