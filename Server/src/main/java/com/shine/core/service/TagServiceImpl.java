package com.shine.core.service;

import com.shine.core.dao.TagDao;
import com.shine.core.domain.Question;
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

    @Resource
    private QuestionService questionService;

    @Transactional
    @Override
    public List<Tag> findTagsById(List<Long> tagIds) {
        return tagDao.readTagsById(tagIds);
    }

    @Transactional
    @Override
    public List<Tag> findTagsByName(List<String> tagNames) {
        return tagDao.readTagByName(tagNames);
    }

    @Transactional
    @Override
    public Long findTagCountById(List<Long> tagIds) {
        return tagDao.readTagCountById(tagIds);
    }

    @Transactional
    @Override
    public List<Tag> findTagsForQuestion(Question question) {
        List<Tag> tagList = tagDao.readTagsForQuestion(question);
        return tagList;
    }

    @Transactional
    @Override
    public List<Tag> createNotExistTags(List<String> tagNames) {
        
    }
}
