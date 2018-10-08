package com.shine.api.rest;

import com.shine.api.rest.exception.ShineRestException;
import com.shine.core.domain.Question;
import com.shine.core.dto.QuestionRequestDTO;
import com.shine.core.service.QuestionService;
import com.shine.core.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "question",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionEndPoint {
    private final static Logger log = LoggerFactory.getLogger(QuestionEndPoint.class);


    @Resource
    private QuestionService questionService;

    @Resource
    private TagService tagService;

    @PostMapping(path = "")
    public ResponseEntity createNewQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {

        Long tagCount = (long) questionRequestDTO.getTagIds().size();

        if (!Objects.equals(tagService.findTagCountById(questionRequestDTO.getTagIds()), tagCount)) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.TAGS_NOT_FOUND);
        }


        Question question = questionService.createQuestion(questionRequestDTO);
        questionRequestDTO.setId(question.getId());
        questionRequestDTO.setTitle(question.getTitle());
        questionRequestDTO.setBody(question.getBody());


        return ResponseEntity.ok(questionRequestDTO);
    }


}
