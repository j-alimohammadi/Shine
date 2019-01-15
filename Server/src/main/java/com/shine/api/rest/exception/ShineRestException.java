package com.shine.api.rest.exception;

import org.apache.commons.collections4.MapUtils;

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

    private Map<String, Object[]> messages = new LinkedHashMap<>();

    private Map<String, Object> additionalData = new LinkedHashMap<>();


    /////////////////////////////////////////////
    //            General Error Code
    /////////////////////////////////////////////
    public final static String UNKNOWN = "shine.platform.restapi.exception.unknownError";
    public final static String INVALID_POST_BODY_CONTENT = "shine.platform.restapi.exception.post.contentInvalid";


    /////////////////////////////////////////////
    //            Question Error Code
    /////////////////////////////////////////////
    public final static String TAGS_NOT_FOUND = "shine.platform.restapi.exception.question.tagNotFound";
    public final static String INVALID_TITLE = "shine.platform.restapi.exception.question.titleInvalid";
    public final static String INVALID_QUESTION_ID = "shine.platform.restapi.exception.question.questionIdInvalid";


    /////////////////////////////////////////////
    //            Answer Error Code
    /////////////////////////////////////////////
    public final static String INVALID_ANSWER_ID = "shine.platform.restapi.exception.answer.answerIdInvalid";


    /////////////////////////////////////////////
    //            User Management Code
    /////////////////////////////////////////////
    public final static String UNAUTHENTICATED_USER = "shine.platform.restapi.exception.user.unauthenticated";
    public final static String INVALID_USER_REGISTRATION_INFO = "shine.platform.restapi.exception.user.registrations";


    public ShineRestException(int httpStatusCode, Locale locale, Map<String, Object[]> messages,
                              Map<String, Object> additionalData, Throwable cause) {
        super(cause);
        this.httpStatusCode = httpStatusCode;
        this.locale = locale;
        if (MapUtils.isNotEmpty(messages)) {
            this.messages = messages;
        }

        if (MapUtils.isNotEmpty(additionalData)) {
            this.additionalData = additionalData;
        }
    }


    public static ShineRestException build(int httpStatusCode) {
        return build(httpStatusCode, null);
    }

    public static ShineRestException build(int httpStatusCode, Throwable cause) {
        return build(httpStatusCode, null, cause);
    }

    public static ShineRestException build(int httpStatusCode, Locale locale, Throwable cause) {
        return build(httpStatusCode, locale, null, null, cause);
    }


    public static ShineRestException build(int httpStatusCode, Locale locale, Map<String, Object[]> messages,
                                           Map<String, Object> additionalData, Throwable cause) {
        return new ShineRestException(httpStatusCode, locale, messages, additionalData, cause);
    }

    public ShineRestException addMessage(String key, Object params[]) {
        messages.put(key, params);
        return this;
    }

    public ShineRestException addMessage(String key) {
        return addMessage(key, null);
    }

    public ShineRestException addAdditionalData(String key, Object value) {
        additionalData.put(key, value);
        return this;
    }

    public ShineRestException addAdditionalData(Map<String, Object> additionalData) {
        this.additionalData.putAll(additionalData);
        return this;
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
