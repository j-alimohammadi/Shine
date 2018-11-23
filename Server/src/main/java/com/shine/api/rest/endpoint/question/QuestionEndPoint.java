package com.shine.api.rest.endpoint.question;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.api.rest.wrapper.AnswerWrapper;
import com.shine.api.rest.wrapper.QuestionWrapper;
import com.shine.common.config.ShineConfigReader;
import com.shine.core.domain.Answer;
import com.shine.core.domain.Question;
import com.shine.core.service.AnswerService;
import com.shine.core.service.QuestionService;
import com.shine.search.SearchOrder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/question",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuestionEndPoint extends BaseEndpoint {
    private final static Logger log = LoggerFactory.getLogger(QuestionEndPoint.class);

    @Resource
    private QuestionService questionService;

    @Resource
    private AnswerService answerService;


    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuestionWrapper createNewQuestion(HttpServletRequest httpServletRequest,
                                             @RequestBody QuestionWrapper questionWrapper) {

        if (StringUtils.isBlank(questionWrapper.getTitle())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_TITLE);
        }

        if (StringUtils.isBlank(String.valueOf(questionWrapper.getBody()))) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_POST_BODY_CONTENT);
        }

        Question question = questionWrapper.unwrap(httpServletRequest, applicationContext);
        question = questionService.createQuestion(question);
        QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);

        response.wrap(question, httpServletRequest);


        return response;
    }


    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public QuestionWrapper updateQuestion(HttpServletRequest httpServletRequest,
                                          @RequestBody QuestionWrapper questionWrapper) {

        if (StringUtils.isBlank(questionWrapper.getTitle())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_TITLE);
        }

        if (StringUtils.isBlank(String.valueOf(questionWrapper.getBody()))) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_POST_BODY_CONTENT);
        }

        if (Objects.isNull(questionWrapper.getId())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_QUESTION_ID);
        }

        Question question = questionWrapper.unwrap(httpServletRequest, applicationContext);
        question = questionService.updateQuestion(question);

        QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);
        response.wrap(question, httpServletRequest);

        return response;
    }

    @DeleteMapping(path = "/{question-id}")
    public ResponseEntity deleteQuestionById(@PathVariable("question-id") Long questionId) {
        Question question = questionService.findQuestionById(questionId);

        if (Objects.isNull(question)) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_QUESTION_ID);
        }

        questionService.deleteQuestionById(questionId);
        String message = String.format("Question [%s] deleted successfully", questionId);
        log.debug(message);

        return ResponseEntity.ok(message);
    }

    @GetMapping(path = {"", "/sort/{order-by}"})
    public List<QuestionWrapper> findAllQuestions(HttpServletRequest httpServletRequest,
                                                  @PathVariable(value = "order-by", required = false) String orderBy,
                                                  @RequestParam(value = "offset", defaultValue = "0") int questionOffset,
                                                  @RequestParam(value = "limit", defaultValue = "20") int questionLimit) {

        SearchOrder searchOrder = SearchOrder.getSearchOrder(orderBy)
                .orElse(SearchOrder.valueOf(ShineConfigReader.readProperty("question.default.sort",
                        SearchOrder.RECENT_UPDATE.value)));


        List<QuestionWrapper> result = new ArrayList<>();
        List<Question> questions = questionService.findQuestions(questionOffset, questionLimit, searchOrder);

        questions.forEach(question -> {
            QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);
            response.wrap(question, httpServletRequest);
            result.add(response);
        });

        return result;
    }

    @GetMapping(path = "/{question-id}")
    public QuestionWrapper findQuestionById(HttpServletRequest httpServletRequest,
                                            @PathVariable("question-id") Long questionId) {

        Question question = questionService.findQuestionById(questionId);

        QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);
        response.wrap(question, httpServletRequest);

        return response;
    }


    @PutMapping(path = "/{question-id}/vote/increment")
    public QuestionWrapper incrementVote(@PathVariable("question-id") Long questionId,
                                         HttpServletRequest httpServletRequest) {
        Question question = questionService.findQuestionById(questionId);
        questionService.voteUp(question);

        QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);
        response.wrap(question, httpServletRequest);

        return response;

    }

    @PutMapping(path = "/{question-id}/vote/decrement")
    public QuestionWrapper decrementVote(@PathVariable("question-id") Long questionId,
                                         HttpServletRequest httpServletRequest) {
        Question question = questionService.findQuestionById(questionId);
        questionService.voteDown(question);

        QuestionWrapper response = applicationContext.getBean(QuestionWrapper.class);
        response.wrap(question, httpServletRequest);

        return response;

    }


    @PutMapping(path = "/{question-id}/accept/answer/{answer-id}")
    public AnswerWrapper acceptAnswer(@PathVariable("question-id") Long questionId,
                                      @PathVariable("answer-id") Long answerId,
                                      HttpServletRequest httpServletRequest) {

        Question question = questionService.findQuestionById(questionId);
        Answer answer = answerService.findAnswerById(answerId);

        if (Objects.isNull(question)) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_QUESTION_ID);
        }

        if (Objects.isNull(answer)) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_ANSWER_ID);
        }


        Answer acceptedAnswer = questionService.acceptAnswer(answer);
        AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
        response.wrap(acceptedAnswer, httpServletRequest);

        return response;

    }

}
