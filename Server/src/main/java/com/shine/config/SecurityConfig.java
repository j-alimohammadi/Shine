package com.shine.config;

import com.shine.core.security.service.authentication.JWTAuthenticationProvider;
import com.shine.core.security.service.jwt.JWTTokenService;
import com.shine.core.security.service.jwt.JWTTokenServiceImpl;
import com.shine.web.security.filter.AuthenticationFilter;
import com.shine.web.security.filter.LoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Resource(name = "shineUserDetailService")
    protected UserDetailsService userDetailsService;


    @Resource(name = "tokenAuthenticationSuccessHandlerImpl")
    protected AuthenticationSuccessHandler successHandler;

    @Resource(name = "tokenAuthenticationFailHandlerImpl")
    protected AuthenticationFailureHandler failureHandler;

    @Bean("BCryptPasswordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean("JWTTokenServiceImpl")
    public JWTTokenService getJwtTokenService() {
        return new JWTTokenServiceImpl();
    }



    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(getPasswordEncoder());

        return provider;
    }


    public AuthenticationProvider jwtAuthenticationProvider() {
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

        // URL that needs authentication
        httpSecurity.authorizeRequests()
                .antMatchers("/question/**/vote/increment")
                .authenticated();

        // permit all other URL
        httpSecurity.authorizeRequests().anyRequest().permitAll();


        httpSecurity.addFilterBefore(
                new AuthenticationFilter("/**", authenticationManager(), failureHandler),
                UsernamePasswordAuthenticationFilter.class
        );

        httpSecurity.addFilterBefore(
                new LoginFilter("/api/user/login", authenticationManager(), successHandler, failureHandler),
                AuthenticationFilter.class
        );


        // http.requiresChannel().anyRequest().requiresSecure();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.headers().frameOptions().disable();


    }

    @Bean(name = "shineAuthenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

