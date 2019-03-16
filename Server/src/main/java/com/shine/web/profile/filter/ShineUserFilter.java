package com.shine.web.profile.filter;

import com.shine.common.web.FilterOrder;
import com.shine.common.web.ShineRequestContext;
import com.shine.core.security.dto.UserSession;
import com.shine.web.profile.service.AnonymousUserHolder;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("shineUserFilter")
public class ShineUserFilter extends OncePerRequestFilter implements Ordered {

    @Resource
    protected AnonymousUserHolder anonymousUserHolder;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ShineRequestContext shineRequestContext = ShineRequestContext.getShineRequestContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // set session id
        if (authentication.getPrincipal().equals("anonymousUser")) {
            UserSession anonymousUserSession = anonymousUserHolder.getAnonymousUserSession();
            shineRequestContext.setSessionId(anonymousUserSession.getId());
        } else {
            shineRequestContext.setSessionId(UUID.fromString(authentication.getDetails().toString()));
        }


    }

    @Override
    public int getOrder() {
        return FilterOrder.POST_SECURITY_LOW;
    }
}
