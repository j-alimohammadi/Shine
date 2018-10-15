package com.shine.core.service;

import com.shine.core.dao.AnswerDao;
import com.shine.core.domain.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineAnswerServiceImpl")
public class AnswerServiceImpl implements AnswerService {
    private final static Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Resource
    private AnswerDao answerDao;

    @Transactional
    @Override
    public Answer createAnswer(Answer answer) {
        answer.setCreatedTimeStamp(new Date());
        answer = answerDao.createOrUpdate(answer);

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
}
