package com.shine.core.service;

import com.shine.core.dao.AnswerDao;
import com.shine.core.domain.Answer;
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
@Service("shineAnswerServiceImpl")
public class AnswerServiceImpl implements AnswerService {
    private final static Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Resource
    private AnswerDao answerDao;

    @Resource
    private QuestionService questionService;

    @Resource
    private PostService postService;

    @Transactional
    @Override
    public Answer saveAnswer(Answer answer) {
        answer.setCreatedTimeStamp(new Date());
        answer = answerDao.createOrUpdate(answer);
        questionService.addAnswerCount(answer.getQuestion().getId(), 1L);

        log.info("Created answer with id [{}] successfully", answer.getId());
        return answer;

    }

    @Transactional
    @Override
    public Answer updateAnswer(Answer answer) {
        answer = answerDao.createOrUpdate(answer);
        log.info("Updated answer with id [{}] successfully", answer.getId());
        return answer;
    }

    @Override
    public Answer createAnswerFromId(Long answerId) {
        Answer answer;
        if (Objects.isNull(answerId)) {
            answer = new Answer();
        } else {
            answer = answerDao.find(answerId);
        }

        return answer;
    }

    @Transactional
    @Override
    public void deleteAnswerById(Long id) {
        answerDao.deleteById(id);
    }

    @Transactional
    @Override
    public Answer findAnswerById(Long id) {
        return answerDao.find(id);
    }

    @Transactional
    @Override
    public List<Answer> findAnswersForQuestion(Long questionId, int answerOffset, int answerLimit) {
        return answerDao.readAnswerForQuestions(questionId, answerOffset, answerLimit);
    }


    @Transactional
    @Override
    public List<Answer> findAllAnswers(int answerOffset, int answerLimit) {
        return answerDao.readAnswer(answerOffset, answerLimit);
    }


    @Transactional
    @Override
    public Long voteUp(Answer answer) {
        Answer updateAnswer = postService.voteUp(answer);
        answerDao.update(updateAnswer);
        return updateAnswer.getVote();
    }

    @Transactional
    @Override
    public Long voteDown(Answer answer) {
        Answer updateAnswer = postService.voteDown(answer);
        answerDao.update(updateAnswer);
        return updateAnswer.getVote();

    }

}
