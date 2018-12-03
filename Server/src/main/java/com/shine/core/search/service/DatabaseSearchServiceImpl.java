package com.shine.core.search.service;

import com.shine.core.domain.PostType;
import com.shine.core.search.ShineSearchService;
import com.shine.core.search.dao.SearchFieldDao;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchField;
import com.shine.core.search.domain.SearchResult;
import com.shine.core.service.PostService;
import org.springframework.stereotype.Service;

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


    @Override
    public SearchResult searchPosts(SearchCriteria searchCriteria) {

        List<PostType> postTypes = findPostType(searchCriteria);

        setFilterCriteria(searchCriteria);

        postService.findFilteredPostsByCriteria(searchCriteria, postTypes);
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

    private void setFilterCriteria(SearchCriteria searchCriteria) {

        // set post type
        Map<String, String[]> filterCriteria = searchCriteria.getFilterCriteria();
        for (Map.Entry<String, String[]> entry : searchCriteria.getFilterCriteria().entrySet()) {
            searchFieldDao.readFieldByAbbreviation(entry.getKey());
        }

    }


}
