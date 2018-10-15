package com.shine.api.rest.endpoint.question;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.wrapper.AnswerWrapper;
import com.shine.core.domain.Answer;
import com.shine.core.service.AnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping("/answer")
public class AnswerEndPoint extends BaseEndpoint {

    @Resource
    private AnswerService answerService;

    @PostMapping(path = "")
    public AnswerWrapper createNewAnswer(HttpServletRequest httpServletRequest,
                                         @RequestBody AnswerWrapper answerWrapper) {

        Answer answer = answerWrapper.unwrap(httpServletRequest, applicationContext);

        answer = answerService.createAnswer(answer);

        AnswerWrapper response = applicationContext.getBean(AnswerWrapper.class);
        response.wrap(answer, httpServletRequest);

        return response;
    }

}
