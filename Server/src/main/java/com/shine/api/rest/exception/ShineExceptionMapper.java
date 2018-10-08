package com.shine.api.rest.exception;

import com.shine.api.rest.dto.ErrorResponse;
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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@ControllerAdvice
public class ShineExceptionMapper {
    private final static Logger log = LoggerFactory.getLogger(ShineExceptionMapper.class);

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(ShineRestException.class)
    @ResponseBody
    public ErrorResponse handleShineRestException(HttpServletRequest request, HttpServletResponse response,
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

        ErrorResponse errorResponse = ErrorResponse.ErrorResponseBuilder.anErrorResponse()
                .withStatus(ex.getHttpStatusCode())
                .withMessages(errorMessage)
                .build();

        return errorResponse;
    }

}
