package com.shine.config;

import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.service.authentication.JWTAuthenticationProvider;
import com.shine.core.security.service.jwt.TokenService;
import com.shine.core.security.service.jwt.JWTTokenServiceImpl;
import com.shine.core.security.service.oauth.CustomOAuth2UserService;
import com.shine.core.security.service.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.shine.web.profile.filter.ShineUserStatusFilter;
import com.shine.web.profile.service.AnonymousUserHolder;
import com.shine.web.security.filter.AuthenticationFilter;
import com.shine.web.security.filter.LoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${anonymous_user_name}")
    protected String anonymousUsername;

    @Resource(name = "shineUserDetailService")
    protected UserDetailsService userDetailsService;

    @Resource
    protected AnonymousUserHolder anonymousUserHolder;


    @Resource(name = "simpleAuthenticationSuccessHandlerImpl")
    protected AuthenticationSuccessHandler simpleAuthenticationHandler;

    @Resource(name = "oauthAuthenticationSuccessHandlerImpl")
    protected AuthenticationSuccessHandler oauthAuthenticationSuccessHandler;

    @Resource(name = "OAuth2AuthenticationFailureHandler")
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Resource(name = "simpleAuthenticationFailHandlerImpl")
    protected AuthenticationFailureHandler failureHandler;

    @Resource(name = "shineUserServiceImpl")
    protected ShineUserService shineUserService;

    @Resource
    private CustomOAuth2UserService customOAuth2UserService;

    @Bean("BCryptPasswordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean("JWTTokenServiceImpl")
    public TokenService getJwtTokenService() {
        return new JWTTokenServiceImpl();
    }


    private AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(getPasswordEncoder());

        return provider;
    }


    private AuthenticationProvider jwtAuthenticationProvider() {
        JWTAuthenticationProvider jwtAuthenticationProvider =
                new JWTAuthenticationProvider(userDetailsService, getJwtTokenService());

        return jwtAuthenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.cors();
        
        httpSecurity
                .anonymous()
                .principal(anonymousUsername);


        // permit all URL
        httpSecurity.authorizeRequests().anyRequest().permitAll();


        httpSecurity.addFilterBefore(
                new AuthenticationFilter("/**", authenticationManager(), failureHandler),
                UsernamePasswordAuthenticationFilter.class
        );

        httpSecurity.addFilterBefore(
                new LoginFilter("/api/user/loginWithUserPassword", authenticationManager(),
                        simpleAuthenticationHandler, failureHandler),
                AuthenticationFilter.class
        );


        httpSecurity.addFilterAfter(new ShineUserStatusFilter(anonymousUserHolder, shineUserService),
                AnonymousAuthenticationFilter.class);

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // social login
        httpSecurity.oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorization");
        httpSecurity.oauth2Login().authorizationEndpoint().authorizationRequestRepository(cookieAuthorizationRequestRepository());
        httpSecurity.oauth2Login().redirectionEndpoint().baseUri("/oauth2/callback/*");
        httpSecurity.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
        httpSecurity.oauth2Login().successHandler(oauthAuthenticationSuccessHandler);
        httpSecurity.oauth2Login().failureHandler(authenticationFailureHandler);

        httpSecurity.headers().frameOptions().disable();


    }


    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean(name = "shineAuthenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}

