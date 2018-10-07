package com.shine.core.service;

import com.shine.core.dao.QuestionDao;
import com.shine.core.domain.Question;
import com.shine.core.dto.QuestionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Service
public class QuestionServiceImpl implements QuestionService {
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Resource
    private QuestionDao questionDao;

    @Transactional
    @Override
    public Question createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = new Question();
        question.setTitle(questionRequestDTO.getTitle());
        question.setBody(questionRequestDTO.getBody());
        question.setTags(questionRequestDTO.getTags());
        question.setCreatedTimeStamp(new Date());
        question = questionDao.createOrUpdate(question);

        log.debug("Question with title [{}] created successfully", question.getTitle());

        return question;
    }
}
