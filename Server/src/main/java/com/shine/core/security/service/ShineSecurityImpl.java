package com.shine.core.security.service;

import com.shine.common.web.ShineRequestContext;
import com.shine.core.security.PermissionType;
import com.shine.core.security.dto.UserSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("ShineSecurityImpl")
public class ShineSecurityImpl implements ShineSecurity {

    @Resource
    private UserSessionService userSessionService;


    @Override
    public boolean isSpecificPermitted(final String permission) {
        return isSpecificPermitted(permission, 1);
    }

    @Override
    public boolean isSpecificPermitted(final String permission, final Integer value) {
        return getCurrentUserSession().isPermitted(PermissionType.SPECIFIC, permission, value);

    }

    private UserSession getCurrentUserSession() {
        ShineRequestContext shineRequestContext = ShineRequestContext.getShineRequestContext();
        UUID sessionId = shineRequestContext.getSessionId();

        return userSessionService.findSessionById(sessionId);
    }
}
