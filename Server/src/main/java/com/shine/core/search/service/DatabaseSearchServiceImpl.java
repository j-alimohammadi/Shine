package com.shine.core.search.service;

import com.shine.core.qa.domain.Post;
import com.shine.core.qa.domain.PostType;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.dao.SearchFieldDao;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchField;
import com.shine.core.search.domain.SearchResult;
import com.shine.core.qa.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("databaseSearchServiceImpl")
public class DatabaseSearchServiceImpl implements ShineSearchService {

    @Resource(name = "searchFieldDaoImpl")
    private SearchFieldDao searchFieldDao;

    @Resource
    private PostService postService;

    @Transactional
    @Override
    public SearchResult searchPosts(SearchCriteria searchCriteria) {
        SearchResult searchResult = new SearchResult();

        changeFilterKeyToAttribute(searchCriteria);
        searchPosts(searchResult, searchCriteria);
        setPagingAttributes(searchResult, searchCriteria);

        return searchResult;
    }

    private void setPagingAttributes(SearchResult searchResult, SearchCriteria searchCriteria) {
        List<PostType> postTypes = findPostType(searchCriteria);

        final long totalResultCount = postService.findFilteredPostsCountByCriteria(searchCriteria, postTypes);
        final long totalPageCount = (long) Math.ceil(totalResultCount / (double) searchCriteria.getPageSize());
        searchResult.setPage(searchCriteria.getPage());
        searchResult.setPageSize(searchCriteria.getPageSize());
        searchResult.setTotalResult(totalResultCount);
        searchResult.setTotalPage(totalPageCount);
    }

    private List<PostType> findPostType(SearchCriteria searchCriteria) {
        PostType postType = null;
        Map<String, String[]> filterCriteria = searchCriteria.getFilterCriteria();


        for (Map.Entry<String, String[]> entry : filterCriteria.entrySet()) {

            Optional<SearchField> searchField = searchFieldDao.readFieldByAbbreviation(entry.getKey());
            if (searchField.isPresent()) {
                final String entityType = searchField.get().getEntityType();
                postType = PostType.getPostType(entityType);

                if (!Objects.isNull(postType) &&
                        (postType.equals(PostType.QUESTION) | postType.equals(PostType.ANSWER))) {
                    break;
                }
            }
        }

        // We only want to search with Answer or Question post type
        if (Objects.isNull(postType) || postType.equals(PostType.POST)) {
            return Arrays.asList(PostType.ANSWER, PostType.QUESTION);
        } else {
            return Collections.singletonList(postType);
        }


    }

    private void changeFilterKeyToAttribute(SearchCriteria searchCriteria) {

        // Convert search key to attribute
        Map<String, String[]> filterCriteria = new HashMap<>();

        for (Map.Entry<String, String[]> entry : searchCriteria.getFilterCriteria().entrySet()) {
            Optional<SearchField> searchField = searchFieldDao.readFieldByAbbreviation(entry.getKey());


            if (!searchField.isPresent()) {
                continue;
            } else {
                filterCriteria.put(searchField.get().getFullQulificationName(), entry.getValue());
            }
        }

        searchCriteria.setFilterCriteria(filterCriteria);

        // Convert url key to attribute. For example: key1 asc, key2 desc  ==> entity1.field1 asc, entity1.field2 desc
        String sortedBy = searchCriteria.getSortBy();
        if (StringUtils.isNotBlank(sortedBy)) {
            StringBuilder sortedByUrl = new StringBuilder();
            for (String param : sortedBy.split(",")) {
                final String paramName = param.split(" ")[0];
                Optional<SearchField> searchField = searchFieldDao.readFieldByAbbreviation(paramName);
                if (!searchField.isPresent()) {
                    continue;
                } else {
                    String fullQualifiedName = param.replace(paramName, searchField.get().getFullQulificationName());
                    sortedByUrl.append(fullQualifiedName);
                }
            }
            searchCriteria.setSortBy(sortedByUrl.toString());
        }

    }

    private void searchPosts(SearchResult searchResult, SearchCriteria searchCriteria) {
        List<PostType> postTypes = findPostType(searchCriteria);

        List<Post> foundPosts = postService.findFilteredPostsByCriteria(searchCriteria, postTypes);

        searchResult.setPosts(foundPosts);

    }
}
