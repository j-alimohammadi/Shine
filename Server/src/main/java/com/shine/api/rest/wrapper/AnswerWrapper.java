package com.shine.api.rest.wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.common.rest.api.wrapper.APIUnWrapper;
import com.shine.common.rest.api.wrapper.APIWrapper;
import com.shine.common.rest.api.wrapper.BaseWrapper;
import com.shine.common.utils.JSONMapper;
import com.shine.core.domain.Answer;
import com.shine.core.service.AnswerServiceImpl;
import com.shine.core.service.QuestionServiceImpl;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("AnswerWrapper")
@Scope("prototype")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AnswerWrapper extends BaseWrapper implements APIUnWrapper<Answer>, APIWrapper<Answer> {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("body")
    private Map<String, Object> body;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("vote")
    private Long vote;

    @JsonProperty("is_answer_accept")
    private Boolean isAnswerAccept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }

    public Boolean getAnswerAccept() {
        return isAnswerAccept;
    }

    public void setAnswerAccept(Boolean answerAccept) {
        isAnswerAccept = answerAccept;
    }

    @Override
    public Answer unwrap(HttpServletRequest request, ApplicationContext context) {
        final AnswerServiceImpl answerService = context.getBean(AnswerServiceImpl.class);
        final QuestionServiceImpl questionService = context.getBean(QuestionServiceImpl.class);

        Answer answer = answerService.createAnswerFromId(id);
        answer.setBody(JSONMapper.createJSON(body));
        answer.setId(id);
        answer.setQuestion(questionService.findQuestionById(questionId)
                .orElseThrow(() -> {
                    return ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                            .addMessage(ShineRestException.INVALID_QUESTION_ID);
                }));
        answer.setAccepted(BooleanUtils.toBoolean(isAnswerAccept));
        return answer;
    }

    @Override
    public void wrap(Answer answer, HttpServletRequest request) {
        this.id = answer.getId();
        this.body = JSONMapper.createHashMapFromJSON(answer.getBody());
        this.vote = answer.getVote();
        this.isAnswerAccept = BooleanUtils.toBoolean(answer.getAccepted());

    }
}
