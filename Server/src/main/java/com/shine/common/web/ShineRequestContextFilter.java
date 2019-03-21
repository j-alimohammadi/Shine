package com.shine.common.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("shineRequestContextFilter")
public class ShineRequestContextFilter extends OncePerRequestFilter implements Ordered {

    @Value("${spring.h2.console.path}")
    protected String inMemoryDataBasePath;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isIgnoredURL(request)) {

        } else {
            ShineRequestContext shineRequestContext = ShineRequestContext.getShineRequestContext();

            shineRequestContext.setIpAddress(getIpAddr(request));
            filterChain.doFilter(request, response);
        }

    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    private boolean isIgnoredURL(HttpServletRequest request) {
        return false;
    }

    @Override
    public int getOrder() {
        return FilterOrder.POST_SECURITY_LOW;
    }
}
