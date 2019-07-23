package com.shine.api.rest.endpoint.answer;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.api.rest.wrapper.AnswerWrapper;
import com.shine.core.qa.domain.Answer;
import com.shine.core.qa.service.AnswerService;
import com.shine.core.qa.service.QuestionService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping("/answer")
public class AnswerEndPoint extends BaseEndpoint {
    private final static Logger log = LoggerFactory.getLogger(AnswerEndPoint.class);

    @Resource
    private AnswerService answerService;

    @Resource
    private QuestionService questionService;

    @PostMapping(path = "")
    public AnswerWrapper createNewAnswer(HttpServletRequest httpServletRequest,
                                         @RequestBody AnswerWrapper answerWrapper) {

        if (MapUtils.isEmpty(answerWrapper.getBody())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_POST_BODY_CONTENT);
        }

        if (Objects.isNull(questionService.findQuestionById(answerWrapper.getQuestionId()))) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_QUESTION_ID);
        }

        Answer answer = answerWrapper.unwrap(httpServletRequest, applicationContext);

        answer = answerService.saveAnswer(answer);

        AnswerWrapper answerBean = applicationContext.getBean(AnswerWrapper.class);
        answerBean.wrap(answer, httpServletRequest);

        return answerBean;
    }

    @PutMapping(path = "")
    public AnswerWrapper updateAnswer(HttpServletRequest httpServletRequest,
                                      @RequestBody AnswerWrapper answerWrapper) {
        Answer foundAnswer = answerService.findAnswerById(answerWrapper.getQuestionId())
                .orElseThrow(() -> {
                    return ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                            .addMessage(ShineRestException.INVALID_QUESTION_ID);
                });

        if (MapUtils.isEmpty(answerWrapper.getBody())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_POST_BODY_CONTENT);
        }

        if (Objects.isNull(answerWrapper.getId())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_ANSWER_ID);
        }


        Answer answer = answerWrapper.unwrap(httpServletRequest, applicationContext);

        answer = answerService.saveAnswer(answer);

        AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
        response.wrap(answer, httpServletRequest);


        return response;
    }


    @PutMapping(path = "/{answer-id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable("answer-id") Long answerId) {
        Answer answer = answerService.findAnswerById(answerId)
                .orElseThrow(() -> {
                    return ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                            .addMessage(ShineRestException.INVALID_ANSWER_ID);
                });

        questionService.deleteQuestionById(answerId);
        String message = String.format("Answer [%s] deleted successfully", answerId);
        log.debug(message);

        return ResponseEntity.ok(message);

    }


    @GetMapping(path = "/question/{questionId}")
    public List<AnswerWrapper> findAnswersForQuestion(HttpServletRequest httpServletRequest,
                                                      @PathVariable Long questionId,
                                                      @RequestParam(value = "offset", defaultValue = "0") int questionOffset,
                                                      @RequestParam(value = "limit", defaultValue = "20") int questionLimit) {

        List<Answer> answers = answerService.findAnswersForQuestion(questionId, questionOffset, questionLimit);

        List<AnswerWrapper> result = answers.stream().map(question -> {
            AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
            response.wrap(question, httpServletRequest);
            return response;
        }).collect(Collectors.toList());

        return result;
    }

    @GetMapping(path = "")
    public List<AnswerWrapper> findAllAnswers(HttpServletRequest httpServletRequest,
                                              @RequestParam(value = "offset", defaultValue = "0") int questionOffset,
                                              @RequestParam(value = "limit", defaultValue = "20") int questionLimit) {

        List<AnswerWrapper> result = new ArrayList<>();
        List<Answer> questions = answerService.findAllAnswers(questionOffset, questionLimit);

        questions.forEach(question -> {
            AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
            response.wrap(question, httpServletRequest);
            result.add(response);
        });

        return result;
    }

    @PutMapping(path = "/{answer-id}/vote/increment")
    public AnswerWrapper incrementVote(@PathVariable("answer-id") Long answerId,
                                       HttpServletRequest httpServletRequest) {

        Answer foundAnswer = answerService.findAnswerById(answerId)
                .orElseThrow(() -> {
                    return ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                            .addMessage(ShineRestException.INVALID_ANSWER_ID);
                });
        foundAnswer = answerService.voteUp(foundAnswer);

        AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
        response.wrap(foundAnswer, httpServletRequest);

        return response;

    }

    @PutMapping(path = "/{answer-id}/vote/decrement")
    public AnswerWrapper decrementVote(@PathVariable("answer-id") Long answerId,
                                       HttpServletRequest httpServletRequest) {
        Answer foundAnswer = answerService.findAnswerById(answerId)
                .orElseThrow(() -> {
                    return ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                            .addMessage(ShineRestException.INVALID_ANSWER_ID);
                });

        foundAnswer = answerService.voteDown(foundAnswer);

        AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
        response.wrap(foundAnswer, httpServletRequest);

        return response;

    }


}
