package com.shine.config;

import com.shine.web.security.authentication.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static Logger log = LoggerFactory.getLogger(SecurityConfig.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // URL that needs authentication
        http.authorizeRequests()
                .antMatchers("/question/**/vote/increment")
                .authenticated();

        // permit all other URL
        http.authorizeRequests().anyRequest().permitAll();

        http.addFilterAfter(new AuthenticationFilter("/**", authenticationManager()), UsernamePasswordAuthenticationFilter.class);


//        http.requiresChannel().anyRequest().requiresSecure();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }

    @Bean(name = "shineAuthenticationManager")
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
