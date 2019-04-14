package com.shine.web.security.authentication;

import com.shine.common.persistence.TransactionAPI;
import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;
import com.shine.core.security.service.UserSessionService;
import com.shine.core.security.service.jwt.JWTInfo;
import com.shine.core.security.service.jwt.JWTTokenService;
import com.shine.core.security.service.token.TokenStore;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("oauthAuthenticationSuccessHandlerImpl")
public class OAuthAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource(name = "JWTTokenServiceImpl")
    protected JWTTokenService jwtTokenService;

    @Resource(name = "userSessionServiceImpl")
    protected UserSessionService userSessionService;

    @Resource
    protected ShineUserService shineUserService;

    @Resource
    protected TokenStore tokenStore;

    @Resource
    protected PlatformTransactionManager jpaTransactionManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken oAuth2Authentication = (OAuth2AuthenticationToken) authentication;
        final String loginName = oAuth2Authentication.getPrincipal().getName();

        TransactionAPI transactionAPI = new TransactionAPI(jpaTransactionManager);

        UserSession userSession = transactionAPI.doInTransaction(status -> {
            ShineUser shineUser = shineUserService.findUserByUserNameNN(loginName);
            return userSessionService.createUserSession(shineUser);
        });
//
        JWTInfo jwtInfo = jwtTokenService.generateAuthenticationToken(loginName, userSession.getId().toString());

        tokenStore.storeToken(jwtInfo.getTokenValue(), userSession.getId(), jwtInfo.getExpirationDate());

        response.addHeader("Authorization", "Bearer " + jwtInfo.getTokenValue());
    }
}
