package com.shine.core.service;

import com.shine.common.persistence.PersistenceCommonConfig;
import com.shine.core.dao.TagDao;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public List<Tag> createNotExistTags(List<String> tagNames) {
        List<String> foundTagsNames = findTagsByName(tagNames)
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toList());


        List<String> shouldCreateTags = tagNames
                .stream()
                .filter(tag -> !foundTagsNames.contains(tag))
                .collect(Collectors.toList());


        for (int i = 0; i < shouldCreateTags.size(); i++) {
            Tag item = new Tag();
            item.setName(shouldCreateTags.get(i));
            tagDao.createOrUpdate(item);
            if (i % PersistenceCommonConfig.TransactionBatchSize == 0) { // 20, same as step 1 config
                // flush a batch of inserts and release memory
                em.flush();
                em.clear();
            }
        }


    }
}
