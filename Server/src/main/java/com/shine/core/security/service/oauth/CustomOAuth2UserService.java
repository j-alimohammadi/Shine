package com.shine.core.security.service.oauth;

import com.shine.common.exception.OAuth2AuthenticationProcessingException;
import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.service.OAuthProvider;
import com.shine.core.security.service.oauth.user.OAuth2UserInfo;
import com.shine.core.security.service.oauth.user.OAuth2UserInfoFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    @Resource
    protected ShineUserService shineUserService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return processOAuth2User(userRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(),
                oAuth2User.getAttributes());

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<ShineUser> foundUser = shineUserService.findUserByUserName(oAuth2UserInfo.getEmail());


        ShineUser shineUser1 = foundUser.map(shineUser -> {
            if (!shineUser.getOAuthProvider().equals(OAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        shineUser.getOAuthProvider() + " account. Please use your " + shineUser.getOAuthProvider() +
                        " account to login.");
            }
            return shineUser;
        }).orElseGet(() -> {
            return registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        });


        Set<ShineRole> foundRoles = shineUserService.findRoleByUserName(shineUser1.getLogin());
        Set<GrantedAuthority> collect = foundRoles.stream()
                .map(shineRole -> new SimpleGrantedAuthority(shineRole.getName()))
                .collect(Collectors.toSet());

        DefaultOAuth2User oAuth2User1 = new DefaultOAuth2User(collect, oAuth2User.getAttributes(), "email");

        return oAuth2User1;
    }

    private ShineUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        ShineUser shineUser = ShineUser.ShineUserBuilder.aShineUser()
                .withLogin(oAuth2UserInfo.getEmail())
                .withEmail(oAuth2UserInfo.getEmail())
                .withRegisterTime(new Date())
                .withRepudiation(0)
                .withActiveStatusFlag(true)
                .withUnEncodedPassword("")
                .build();

        shineUser.setOAuthProvider(OAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase()));

        return shineUserService.createNewUser(shineUser);
    }


}
