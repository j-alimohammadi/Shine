package com.shine.web.profile.service;

import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;
import com.shine.core.security.service.UserSessionService;
import com.shine.core.security.utils.TokenMasker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class AnonymousUserHolderImp implements AnonymousUserHolder {
    private final static Logger log = LoggerFactory.getLogger(AnonymousUserHolderImp.class);

    @Resource
    protected UserSessionService userSessionService;

    @Value("${anonymous_role}")
    protected String anonymousRoleName;

    @Value("${anonymous_user_name}")
    protected String anonymousUsername;

    @Value("${anonymous_session_id}")
    protected String anonymousSessionId;


    protected UserSession anonymousUserSession;

    @PostConstruct
    public void init() {
        ShineUser shineUser = new ShineUser();
        shineUser.setLogin(anonymousUsername);

        ShineRole shineRole = new ShineRole();
        shineRole.setName(anonymousRoleName);
        shineRole.setDescription("role_anonymous");

        shineUser.setShineRoles(Collections.singleton(shineRole));

        anonymousUserSession = userSessionService.createUserSession(UUID.fromString(anonymousSessionId), shineUser);

        log.info("Created anonymous user session [{}] successfully",
                TokenMasker.maskToken(anonymousUserSession.getId().toString()));

    }


    @Override
    public UserSession getAnonymousUserSession() {
        return anonymousUserSession;
    }


}
