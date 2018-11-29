package com.shine.core.search.service;

import com.shine.core.search.ShineSearchService;
import com.shine.core.search.dao.SearchFieldDao;
import com.shine.core.search.domain.EntityType;
import com.shine.core.search.domain.SearchCriteria;
import com.shine.core.search.domain.SearchField;
import com.shine.core.search.domain.SearchResult;
import com.shine.core.service.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

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
    public SearchResult findResult(SearchCriteria searchCriteria) {
        setPostType(searchCriteria);
        setFilterCriteria(searchCriteria);

        return postService.findFilteredPostsByCriteria(searchCriteria);
    }

    private void setPostType(SearchCriteria searchCriteria) {
        Map<String, String[]> filterCriteria = searchCriteria.getFilterCriteria();



        for (Map.Entry<String, String[]> entry : filterCriteria.entrySet()) {

            Optional<SearchField> searchField = searchFieldDao.readFieldByAbbreviation(entry.getKey());
            searchField.ifPresent(searchField1 -> {
                if (searchField1.getEntityType().equalsIgnoreCase(EntityType.QUESTION.value)) {
                    searchCriteria.setPostType(searchField1.getEntityType());
                    return;
                }
            });
        }


    }

    private void setFilterCriteria(SearchCriteria searchCriteria) {

        // set post type

        searchFieldDao.readFieldByAbbreviation()
    }


}
