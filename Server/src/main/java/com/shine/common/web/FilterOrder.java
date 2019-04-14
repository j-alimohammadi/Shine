package com.shine.common.web;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface FilterOrder {
    int PRE_SECURITY_LOW = SecurityProperties.DEFAULT_FILTER_ORDER - 1000;
    int PRE_SECURITY_MEDIUM = SecurityProperties.DEFAULT_FILTER_ORDER - 2000;
    int PRE_SECURITY_HIGH = SecurityProperties.DEFAULT_FILTER_ORDER - 3000;

    int POST_SECURITY_HIGH = SecurityProperties.DEFAULT_FILTER_ORDER + 1000;
    int POST_SECURITY_MEDIUM = SecurityProperties.DEFAULT_FILTER_ORDER + 2000;
    int POST_SECURITY_LOW = SecurityProperties.DEFAULT_FILTER_ORDER + 3000;
}
