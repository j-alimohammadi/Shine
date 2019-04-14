package com.shine.web.profile.service;

import com.shine.core.security.dto.UserSession;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface AnonymousUserHolder {
    UserSession getAnonymousUserSession();
}
