package com.shine.api.rest;

import com.shine.core.domain.Question;
import com.shine.core.dto.QuestionRequestDTO;
import com.shine.core.service.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "question",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionEndPoint {

    @Resource
    private QuestionService questionService;

    @PostMapping(path = "")
    public ResponseEntity createNewQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        Question question = questionService.createQuestion(questionRequestDTO);

        questionRequestDTO.setId(question.getId());
        questionRequestDTO.setTitle(question.getTitle());
        questionRequestDTO.setBody(question.getBody());
        questionRequestDTO.setTags(question.getTags());

        return ResponseEntity.ok(questionRequestDTO);
    }

    

}
