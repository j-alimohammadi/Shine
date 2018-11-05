package com.shine.core.service;

import com.shine.core.dao.TagDao;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class TagServiceImpl implements TagService {
    private final int ONE_HUNDRED_THOUSAND = 100;

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
    public Integer createNotExistTags(List<String> tagNames) {
        List<Tag> newTags = new ArrayList<>();

        List<String> existTagsNames = findTagsByName(tagNames)
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toList());


        List<String> shouldCreateTags = tagNames
                .stream()
                .filter(tag -> !existTagsNames.contains(tag))
                .collect(Collectors.toList());


        shouldCreateTags.forEach(tagName -> {
            Tag item = new Tag();
            item.setName(tagName);
            newTags.add(item);
        });

        if (CollectionUtils.isNotEmpty(newTags)) {
            return tagDao.bulkSaveOrUpdateTags(newTags);
        } else {
            return 0;
        }


    }

    @Override
    public Integer addTagUsedCount(List<Tag> tagList, Long count) {
        tagList.forEach(tag -> {
            tag.setUsedCount(tag.getUsedCount() + count);
        });
        return tagDao.bulkSaveOrUpdateTags(tagList);
    }

    @Override
    public Integer subtractTagUsedCount(List<Tag> tagList, Long count) {
        tagList.forEach(tag -> {
            tag.setUsedCount(tag.getUsedCount() - count);
        });
        return tagDao.bulkSaveOrUpdateTags(tagList);

    }
}
