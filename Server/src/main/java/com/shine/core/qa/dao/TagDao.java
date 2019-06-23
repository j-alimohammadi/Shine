package com.shine.core.qa.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.qa.domain.Question;
import com.shine.core.qa.domain.Tag;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TagDao extends DAO<Tag> {
    List<Tag> readTagsById(List<Long> tagId);

    List<Tag> readTagByName(List<String> tagNames);

    Long readTagCountById(List<Long> tagId);

    List<Tag> readTagsForQuestion(Question question);

    Integer bulkSaveOrUpdateTags(List<Tag> tags);

    List<Tag> findFilteredTagsByCriteria(SearchCriteria searchCriteria);

    Long findFilteredTagsCountByCriteria(SearchCriteria searchCriteria);


}
