package com.shine.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class AnswerResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("body")
    private Map<String, Object> body;

    @JsonProperty("vote")
    private Long vote;

    @JsonProperty("is_answer_accept")
    private boolean isAnswerAccept;


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

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }

    public boolean isAnswerAccept() {
        return isAnswerAccept;
    }

    public void setAnswerAccept(boolean answerAccept) {
        isAnswerAccept = answerAccept;
    }


    public static final class AnswerResponseBuilder {
        private AnswerResponse answerResponse;

        private AnswerResponseBuilder() {
            answerResponse = new AnswerResponse();
        }

        public static AnswerResponseBuilder anAnswerResponse() {
            return new AnswerResponseBuilder();
        }

        public AnswerResponseBuilder withId(Long id) {
            answerResponse.setId(id);
            return this;
        }

        public AnswerResponseBuilder withBody(Map<String, Object> body) {
            answerResponse.setBody(body);
            return this;
        }

        public AnswerResponseBuilder withVote(Long vote) {
            answerResponse.setVote(vote);
            return this;
        }

        public AnswerResponse build() {
            return answerResponse;
        }
    }
}
