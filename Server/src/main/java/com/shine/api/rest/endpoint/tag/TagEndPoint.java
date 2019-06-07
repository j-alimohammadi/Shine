package com.shine.api.rest.endpoint.tag;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.wrapper.TagWrapper;
import com.shine.core.qa.domain.Tag;
import com.shine.core.qa.service.TagService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@RestController
@RequestMapping(value = "/tag", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TagEndPoint extends BaseEndpoint {

    @Resource
    private TagService tagService;

    @GetMapping("")
    public List<TagWrapper> getWrapper(@RequestParam(value = "offset", defaultValue = "0") int questionOffset,
                                       @RequestParam(value = "limit", defaultValue = "20") int questionLimit) {
        List<Tag> allTags = tagService.findAllTags(questionOffset, questionLimit);

        return null;
    }

}
