package com.shine.core.qa.service;

import com.shine.common.web.ShineRequestContext;
import com.shine.common.web.TextUtility;
import com.shine.core.profile.service.ShineUserService;
import com.shine.core.qa.dao.QuestionDao;
import com.shine.core.qa.domain.Answer;
import com.shine.core.qa.domain.PostView;
import com.shine.core.qa.domain.Question;
import com.shine.core.qa.domain.Tag;
import com.shine.core.security.domain.ShineUser;
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
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Service("shineQuestionServiceImpl")
public class QuestionServiceImpl implements QuestionService {
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionDao questionDao;

    @Resource
    private AnswerService answerService;

    @Resource
    private PostService postService;

    @Resource
    private TagService tagService;

    @Resource
    private ShineUserService shineUserService;

    @Resource
    private PostViewService postViewService;


    @Transactional
    @Override
    public Question createQuestion(Question question) {
        question.setCreatedTimeStamp(new Date());
        question.setTitle(TextUtility.normalizeText(question.getTitle()));
        question.setQuestionAddress(TextUtility.normalizeToURL(question.getTitle()));

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
            question = questionDao.find(questionId).orElse(new Question());
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
        question.setTitle(TextUtility.normalizeText(question.getTitle()));
        question.setQuestionAddress(TextUtility.normalizeToURL(question.getTitle()));
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
    public Optional<Question> findQuestionById(Long id) {
        return questionDao.find(id);
    }

    @Transactional
    @Override
    public Question voteUp(Question question) {
        return postService.voteUp(question.getId());
    }

    @Transactional
    @Override
    public Question voteDown(Question question) {
        return postService.voteDown(question.getId());
    }

    @Transactional
    @Override
    public Long addViewCountIfPossible(Question question) {

        if (Objects.isNull(question.getId())) {
            throw new RuntimeException("Question should be saved before we can add view count");
        }

        Optional<PostView> postView;
        ShineUser shineUser = shineUserService.currentLoggedInUser();
        final String ipAddress = ShineRequestContext.getShineRequestContext().getIpAddress();
        if (shineUserService.isAnonymousUser(shineUser.getLogin())) {
            postView = postViewService.findPostViewByPostIdAndIpAddress(question.getId(), ipAddress);
        } else {
            postView = postViewService.findPostViewByPostIdAndUserId(question.getId(), shineUser.getId());
        }


        if (!postView.isPresent()) {
            PostView postViewTemp = new PostView();
            postViewTemp.setIp(ipAddress);
            postViewTemp.setPostId(question.getId());
            postViewTemp.setUserId(shineUser.getId());

            postViewService.createPostView(postViewTemp);
            question.setViewCount(question.getViewCount() + 1);
            log.info("Adding view count to post [{}] ", question);
            questionDao.createOrUpdate(question);
        }

        return question.getViewCount();

    }

    @Transactional
    @Override
    public Answer acceptAnswer(Answer answer) {
        if (answer.getAccepted()) {
            log.info("Answer [{}] is already accepted. Nothing to do.", answer);
            return answer;
        }

        questionDao.rejectAllAnswerForQuestion(answer.getQuestion().getId());
        log.debug("Rejected all answer for question [{}]", answer.getQuestion());

        answer.setAccepted(true);
        log.debug("Accepted answer [{}] for question [{}]", answer, answer.getQuestion());
        answerService.updateAnswer(answer);

        return answer;
    }

    @Transactional
    @Override
    public Long addAnswerCount(Question question, final Long count) {
        synchronized (this) {
            questionDao.refresh(question);
            final long oldCount = question.getAnswerCount();
            question.setAnswerCount(oldCount + count);
            questionDao.update(question);
        }

        return question.getAnswerCount();
    }

    @Transactional
    @Override
    public Long subtractAnswerCount(Question question, final Long count) {
        return addAnswerCount(question, -count);
    }
}
