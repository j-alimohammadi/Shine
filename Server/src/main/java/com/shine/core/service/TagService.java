package com.shine.core.service;

import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TagService {
    List<Tag> findTagsById(List<Long> tagIds);

    Long findTagCountById(List<Long> tagIds);

    List<Tag> findTagsForQuestion(Question question);
}
