package com.shine.api.rest.exception;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class ShineRestException extends RuntimeException {
    private int httpStatusCode;

    private Map<String, Object[]> messages;

    public final static String TAGS_NOT_FOUND = "shine.rest.api.exception.tag.tagNotFound";


    public ShineRestException(int httpStatusCode, Map<String, Object[]> messages, Throwable cause) {
        super(cause);
        this.httpStatusCode = httpStatusCode;

        if (messages == null) {
            this.messages = new LinkedHashMap<>();
        } else {
            this.messages = messages;
        }
    }


    public ShineRestException addMessage(String key, Object params[]) {
        messages.put(key, params);
        return this;
    }

    public ShineRestException addMessage(String key) {
        return addMessage(key, null);
    }


    public static ShineRestException build(int httpStatusCode) {
        return build(httpStatusCode, null);
    }

    public static ShineRestException build(int httpStatusCode, Throwable cause) {
        return build(httpStatusCode, null, cause);
    }

    public static ShineRestException build(int httpStatusCode, Map<String, Object[]> messages, Throwable cause) {
        return new ShineRestException(httpStatusCode, messages, cause);
    }


}
