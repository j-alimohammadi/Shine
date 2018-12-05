package com.shine.core.search.domain;

import com.shine.core.domain.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the search result for posts
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class SearchResult {
    private List<Post> searchItems = new ArrayList<>();

    private int pageSize;

    private int page;

    private int totalResultSize;

    private int totalPageSize;

    public List<Post> getSearchItems() {
        return searchItems;
    }

    public void setPosts(List<Post> posts) {
        this.searchItems = posts;
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

    public int getTotalResultSize() {
        return totalResultSize;
    }

    public void setTotalResultSize(int totalResultSize) {
        this.totalResultSize = totalResultSize;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }
}
