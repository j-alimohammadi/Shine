package com.shine.core.service;

import com.shine.core.dao.QuestionDao;
import com.shine.core.domain.Question;
import com.shine.core.dto.QuestionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionDao questionDao;

    @Transactional
    @Override
    public Question createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = new Question();
        question.setTitle(questionRequestDTO.getTitle());
        question.setBody(questionRequestDTO.getBody());
        question.setTags(questionRequestDTO.getTags());

        question = questionDao.createOrUpdate(question);

        return question;
    }
}
