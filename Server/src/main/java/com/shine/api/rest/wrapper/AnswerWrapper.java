package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.common.rest.api.wrapper.APIUnWrapper;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.core.domain.Answer;
import com.shine.core.service.AnswerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("AnswerWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AnswerWrapper extends BaseWrapper implements APIUnWrapper<Answer>, APIWrapper<Answer> {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String body;

    @JsonProperty
    private Long questionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public Answer unwrap(HttpServletRequest request, ApplicationContext context) {
        final AnswerServiceImpl answerService = context.getBean(AnswerServiceImpl.class);

        Answer answer = answerService.createAnswerFromId(id);
        answer.setBody(body);
        answer.setId(id);

        return answer;
    }

    @Override
    public void wrap(Answer answer, HttpServletRequest request) {
        this.id = answer.getId();
        this.body = answer.getBody();

    }
}
