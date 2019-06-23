package com.shine.core.qa.service;

import com.shine.core.qa.domain.Question;
import com.shine.core.qa.domain.Tag;
import com.shine.core.search.domain.SearchCriteria;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TagService {
    List<Tag> findTagsById(List<Long> tagIds);

    List<Tag> findTagsByName(List<String> tagNames);

    Long findTagCountById(List<Long> tagIds);

    List<Tag> findTagsForQuestion(Question question);

    Integer createNotExistTags(List<String> tagNames);

    /**
     * Add number of used count to Tag
     *
     * @param tagList
     * @param count
     * @return number of affected Tags
     */
    Integer addTagUsedCount(List<Tag> tagList, Long count);

    /**
     * Subtract number of used count to Tag
     *
     * @param tagList
     * @param count
     * @return number of affected Tags
     */
    Integer subtractTagUsedCount(List<Tag> tagList, Long count);

    List<Tag> findFilteredTagsByCriteria(SearchCriteria searchCriteria);

    Long findFilteredTagsCountByCriteria(SearchCriteria searchCriteria);

}
