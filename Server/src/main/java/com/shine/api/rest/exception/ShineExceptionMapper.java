package com.shine.api.rest.exception;

import com.shine.api.rest.wrapper.ErrorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.shine.api.rest.wrapper.ErrorWrapper.ErrorResponseBuilder.anErrorResponse;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@ControllerAdvice
public class ShineExceptionMapper {
    private final static Logger log = LoggerFactory.getLogger(ShineExceptionMapper.class);

    @Resource(name = "shineMessageSource")
    private MessageSource messageSource;

    @ExceptionHandler(ShineRestException.class)
    @ResponseBody
    public ErrorWrapper handleShineRestException(HttpServletRequest request, HttpServletResponse response,
                                                 ShineRestException ex) {
        response.setStatus(ex.getHttpStatusCode());
        if (!Objects.isNull(ex.getCause())) {
            log.error("An error occurred invoking a REST service.", ex.getCause());
        } else {
            log.error("An error occurred invoking a REST service.", ex);
        }

        final Locale locale = ex.getLocale() == null ? Locale.getDefault() : ex.getLocale();


        List<String> errorMessage = ex.getMessages().entrySet()
                .stream()
                .map(messageEntry -> messageSource.getMessage(messageEntry.getKey(), messageEntry.getValue(), locale))
                .collect(Collectors.toList());

        Map<String, Object> additionalData = ex.getAdditionalData();


        ErrorWrapper errorWrapper = anErrorResponse()
                .withHttpStatus(ex.getHttpStatusCode())
                .withMessages(errorMessage)
                .withAddionalData(additionalData)
                .build();

        return errorWrapper;
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorWrapper handleException(HttpServletRequest request, HttpServletResponse response,
                                        Exception ex) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        log.error("An error occurred invoking a REST service.", ex);

        final Locale locale = Locale.getDefault();


        String errorMessage = messageSource.getMessage(ShineRestException.UNKNOWN, null, locale);

        ErrorWrapper errorWrapper = anErrorResponse()
                .withHttpStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .withMessages(errorMessage)
                .build();

        return errorWrapper;

    }

}
