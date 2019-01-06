package com.shine.common.web;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    @JsonProperty("httpStatus")
    private int httpStatus;

    @JsonProperty("messages")
    private List<String> messages;

    @JsonProperty("additional_data")
    private Map<String, Object> additionalData = new HashMap<>();

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    public static final class ErrorResponseBuilder {
        private int status;
        private List<String> messages = new ArrayList<>();
        private Map<String, Object> additionalData = new HashMap<>();

        private ErrorResponseBuilder() {
        }

        public static ErrorResponseBuilder anErrorResponse() {
            return new ErrorResponseBuilder();
        }

        public ErrorResponseBuilder withHttpStatus(int httpStatus) {
            this.status = httpStatus;
            return this;
        }

        public ErrorResponseBuilder withMessages(List<String> message) {
            this.messages = message;
            return this;
        }

        public ErrorResponseBuilder withMessages(String message) {
            this.messages.add(message);
            return this;
        }

        public ErrorResponseBuilder withAddionalData(Map<String, Object> addionalData) {
            this.additionalData = addionalData;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setHttpStatus(status);
            errorResponse.setMessages(messages);
            errorResponse.setAdditionalData(additionalData);
            return errorResponse;
        }
    }
}
