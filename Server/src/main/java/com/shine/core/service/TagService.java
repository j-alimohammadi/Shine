package com.shine.core.service;

import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TagService {
    List<Tag> findTagsById(List<Long> tagIds);

    List<Tag> findTagsByName(List<String> tagNames);

    Long findTagCountById(List<Long> tagIds);

    List<Tag> findTagsForQuestion(Question question);

    List<Tag> createNotExistTags(List<String> tagNames);
}
