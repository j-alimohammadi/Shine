package com.shine.web.profile.filter;

import com.shine.common.web.FilterOrder;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.profile.service.ShineUserContext;
import com.shine.web.profile.service.UserInHttpRequest;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("shineUserFilter")
public class UserFilter extends OncePerRequestFilter implements Ordered {
    @Resource
    private UserInHttpRequest userInHttpRequest;

    @Resource
    private ShineUserContext shineUserContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ShineUser shineUser = userInHttpRequest.getUserFromRequest(request);
        shineUserContext.setCurrentLoginUser(shineUser);

        filterChain.doFilter(request, response);
    }

    @Override
    public int getOrder() {
        return FilterOrder.POST_SECURITY_LOW;
    }
}
