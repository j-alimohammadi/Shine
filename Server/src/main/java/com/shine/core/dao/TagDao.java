package com.shine.core.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.domain.Tag;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TagDao extends DAO<Tag> {
    List<Tag> readTagsById(List<Long> tagId);

    Long readTagCountById(List<Long> tagId);
}
