package com.shine.api.rest.exception;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class ShineRestException extends RuntimeException {
    private int httpStatusCode;

    private Locale locale;

    private Map<String, Object[]> messages;

    public final static String UNKNOWN = "shine.platform.restapi.exception.unknownError";
    public final static String TAGS_NOT_FOUND = "shine.platform.restapi.exception.question.tagNotFound";
    public final static String INVALID_TITLE = "shine.platform.restapi.exception.question.titleInvalid";
    public final static String INVALID_CONTENT = "shine.platform.restapi.exception.question.contentInvalid";
    public final static String INVALID_QUESTION = "shine.platform.restapi.exception.question.questionIdInvalid";


    public ShineRestException(int httpStatusCode, Locale locale, Map<String, Object[]> messages, Throwable cause) {
        super(cause);
        this.httpStatusCode = httpStatusCode;
        this.locale = locale;
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

    public static ShineRestException build(int httpStatusCode, Locale locale, Throwable cause) {
        return build(httpStatusCode, null, null, cause);
    }


    public static ShineRestException build(int httpStatusCode, Locale locale, Map<String, Object[]> messages, Throwable cause) {
        return new ShineRestException(httpStatusCode, locale, messages, cause);
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public Map<String, Object[]> getMessages() {
        return Collections.unmodifiableMap(messages);
    }
}
