package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.core.search.domain.SearchResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("SearchResultWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchResultWrapper extends BaseWrapper implements APIWrapper<SearchResult> {
    @JsonProperty("page")
    private Integer page;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("total_page")
    private Long totalPage;

    @JsonProperty("result_size")
    private Long resultSize;

    @JsonProperty("posts")
    private List<PostWrapper> posts = new ArrayList<>();

    @JsonProperty("tags")
    private List<TagWrapper> tags = new ArrayList<>();


    @Override
    public void wrap(SearchResult model, HttpServletRequest request) {
        model.getPosts().forEach(post -> {
            PostWrapper postWrapper = context.getBean(PostWrapper.class);
            postWrapper.wrap(post, request);
            posts.add(postWrapper);
        });

        model.getTags().forEach(tag -> {
            TagWrapper tagWrapper = context.getBean(TagWrapper.class);
            tagWrapper.setId(tag.getId());
            tagWrapper.setName(tag.getName());
            tagWrapper.setUsedCount(tag.getUsedCount());

            tags.add(tagWrapper);
        });

        this.page = model.getPage();
        this.pageSize = model.getPageSize();
        this.totalPage = model.getTotalPage();
        this.resultSize = model.getTotalResult();


    }
}
