package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("PostWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResultWrapper {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("post_title")
    private String postTitle;

    @JsonProperty("body")
    private Map<String, Object> body;

    @JsonProperty("vote")
    private Long vote;

    @JsonProperty("post_type")
    private String postType;


}
