package com.shine.core.service;

import com.shine.common.web.TextUtility;
import com.shine.core.dao.QuestionDao;
import com.shine.core.domain.Answer;
import com.shine.core.domain.Question;
import com.shine.core.domain.Tag;
import com.shine.search.SearchOrder;
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
    private AnswerService answerService;

    @Resource
    private PostService postService;


    @Resource
    private TagService tagService;

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
    public Question findQuestionById(Long id) {
        return questionDao.find(id);
    }

    @Transactional
    @Override
    public List<Question> findQuestions(int questionOffset, int questionLimit, SearchOrder orderBy) {
        switch (orderBy) {
            case RECENT_UPDATE:
                return questionDao.findQuestionOrderByUpdate(questionOffset, questionLimit);
            case MOST_VOTES:
                return questionDao.findQuestionOrderByVotes(questionOffset, questionLimit);
            case ANSWER_COUNTS:
                return questionDao.findQuestionOrderByAnswerCount(questionOffset, questionLimit);
            case VIEW_COUNTS:
                return questionDao.findQuestionOrderByViewCount(questionOffset, questionLimit);

            default:
                return questionDao.findQuestionOrderByUpdate(questionOffset, questionLimit);
        }


    }


    @Transactional
    @Override
    public Long voteUp(Question question) {
        Question updateQuestion = postService.voteUp(question);
        questionDao.update(updateQuestion);
        return updateQuestion.getVote();
    }

    @Transactional
    @Override
    public Long voteDown(Question question) {
        Question updateQuestion = postService.voteDown(question);
        questionDao.update(updateQuestion);
        return updateQuestion.getVote();

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
    public Long addAnswerCount(Long questionId, final Long count) {
        Question question;
        synchronized (this) {
            question = questionDao.find(questionId);
            final long oldCount = question.getAnswerCount();
            question.setAnswerCount(oldCount + count);
            questionDao.update(question);
        }

        return question.getAnswerCount();
    }

    @Transactional
    @Override
    public Long subtractAnswerCount(Long questionId, final Long count) {
        return addAnswerCount(questionId, -count);
    }
}
