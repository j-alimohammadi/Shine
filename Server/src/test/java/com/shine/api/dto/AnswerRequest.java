package com.shine.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Builder
public class AnswerRequest {
    @JsonProperty("id")
    public Long id;

    @JsonProperty("body")
    public Map<String, Object> body;

    @JsonProperty("question_id")
    public Long questionId;
}
