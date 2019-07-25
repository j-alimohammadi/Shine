package com.shine.web.security.authentication;

import com.shine.api.rest.exception.ShineRestException;
import com.shine.common.utils.JSONMapper;
import com.shine.api.rest.wrapper.ErrorWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("simpleAuthenticationFailHandlerImpl")
public class SimpleAuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    private final static Logger log = LoggerFactory.getLogger(SimpleAuthenticationFailHandlerImpl.class);

    @Resource(name = "shineMessageSource")
    private MessageSource messageSource;


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Authentication failed");


        final Locale locale = Locale.getDefault();
        String errorMessage = messageSource.getMessage(ShineRestException.UNAUTHENTICATED_USER, null, locale);

        ErrorWrapper errorWrapper = ErrorWrapper.ErrorResponseBuilder.anErrorResponse()
                .withHttpStatus(HttpServletResponse.SC_UNAUTHORIZED)
                .withMessages(errorMessage)
                .build();

        final String responseJSON = JSONMapper.createJSON(errorWrapper);
        response.getWriter().print(responseJSON);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    }
}
