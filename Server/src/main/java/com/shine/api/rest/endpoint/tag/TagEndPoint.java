package com.shine.api.rest.endpoint.tag;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.wrapper.TagWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagEndPoint extends BaseEndpoint {

    @GetMapping("")
    public List<TagWrapper> getWrapper() {
        return null;
    }

}
