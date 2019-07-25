package com.shine.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Builder
public class ErrorResponse {
    @JsonProperty("http_status")
    private Integer httpStatus;

    @JsonProperty("messages")
    private List<String> messages;


}
