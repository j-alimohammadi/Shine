package com.shine.web.profile.service;

import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;
import com.shine.core.security.service.UserSessionService;
import com.shine.core.security.utils.TokenMasker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class AnonymousUserHolderImp implements AnonymousUserHolder {
    private final static Logger log = LoggerFactory.getLogger(AnonymousUserHolderImp.class);

    @Resource
    protected UserSessionService userSessionService;

    @Resource
    protected ShineUserService shineUserService;

    @Value("${anonymous_user_name}")
    protected String anonymousUsername;

    @Value("${anonymous_session_id}")
    protected String anonymousSessionId;


    private UserSession anonymousUserSession;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
//        ShineUser anonymousUser = shineUserService.findUserByUserNameNN(anonymousUsername);
//        anonymousUserSession = userSessionService.createUserSession(UUID.fromString(anonymousSessionId), anonymousUser);
//
//        log.info("Created anonymous user session [{}] successfully",
//                TokenMasker.maskToken(anonymousUserSession.getId().toString()));
    }


    @Override
    public UserSession getAnonymousUserSession() {
        return anonymousUserSession;
    }


}
