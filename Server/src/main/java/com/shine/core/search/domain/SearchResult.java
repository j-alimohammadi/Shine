package com.shine.core.search.domain;

import java.util.List;

/**
 * Holds the search result for posts
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class SearchResult {
    private List<SearchItemDTO> searchItems;

    private int pageSize;

    private int page;

    private int totalSize;

    public List<SearchItemDTO> getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(List<SearchItemDTO> searchItems) {
        this.searchItems = searchItems;
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
