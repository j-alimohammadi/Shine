package com.shine.core.service;

import com.shine.core.dao.QuestionDao;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Service("shineQuestionServiceImpl")
public class QuestionServiceImpl implements QuestionService {
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionDao questionDao;

    @Resource
    private TagService tagService;

    @Transactional
    @Override
    public Question createQuestion(Question question) {
        question.setCreatedTimeStamp(new Date());
        question = questionDao.createOrUpdate(question);

        if (CollectionUtils.isNotEmpty(question.getTagList())) {
            tagService.addTagUsedCount(question.getTagList(), 1L);
        }

        log.debug("Question with title [{}] created successfully", question.getTitle());

        return question;
    }

    @Override
    public Question createQuestionFromId(Long questionId) {
        Question question;
        if (Objects.isNull(questionId)) {
            question = new Question();
        } else {
            question = questionDao.find(questionId);
        }


        return question;
    }

    @Transactional
    @Override
    public Question updateQuestion(Question question) {
        List<Tag> existTag = tagService.findTagsForQuestion(question);
        List<Tag> updateTags = question.getTagList();

        List<Tag> newTags = ListUtils.subtract(updateTags, existTag);
        List<Tag> deletedTags = ListUtils.subtract(existTag, updateTags);

        if (CollectionUtils.isNotEmpty(newTags)) {
            tagService.addTagUsedCount(question.getTagList(), 1L);
        }

        if (CollectionUtils.isNotEmpty(deletedTags)) {
            tagService.subtractTagUsedCount(question.getTagList(), 1L);
        }


        question.setEditedTimeStamp(new Date());
        question = questionDao.createOrUpdate(question);


        log.debug("Question with id [{}] updated successfully", question.getId());

        return question;
    }

    @Transactional
    @Override
    public void deleteQuestionById(Long id) {
        questionDao.deleteById(id);
    }

    @Transactional
    @Override
    public Question findQuestionById(Long id) {
        return questionDao.find(id);
    }

    @Transactional
    @Override
    public List<Question> findQuestions(int questionOffset, int questionLimit) {
        return questionDao.findQuestion(questionOffset, questionLimit);
    }

    // todo : check for race condition
    @Transactional
    @Override
    public Long voteUp(Question question) {
        long vote = question.getVote();
        vote++;
        question.setVote(vote);

        questionDao.update(question);
        return vote;
    }

    @Transactional
    @Override
    public Long voteDown(Question question) {
        long vote = question.getVote();
        vote--;
        question.setVote(vote);

        questionDao.update(question);
        return vote;

    }


}
