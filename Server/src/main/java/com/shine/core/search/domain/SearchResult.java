package com.shine.core.search.domain;

import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the search result
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class SearchResult {
    private List<Post> posts = new ArrayList<>();

    private List<Tag> tags = new ArrayList<>();

    private int pageSize;

    private int page;

    private long totalResult;

    private long totalPage;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(long totalResult) {
        this.totalResult = totalResult;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
