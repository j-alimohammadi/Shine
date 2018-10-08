package com.shine.core.service;

import com.shine.core.dao.TagDao;
import com.shine.core.domain.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagDao tagDao;

    @Transactional
    @Override
    public List<Tag> findTagsById(List<Long> tagIds) {
        return tagDao.readTagsById(tagIds);
    }

    @Transactional
    @Override
    public Long findTagCountById(List<Long> tagIds) {
        return tagDao.readTagCountById(tagIds);
    }
}
