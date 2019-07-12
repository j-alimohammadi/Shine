package com.shine.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class AnswerRequest {
    @JsonProperty("body")
    public Map<String, Object> body;

    @JsonProperty("question_id")
    public String questionId;
}
