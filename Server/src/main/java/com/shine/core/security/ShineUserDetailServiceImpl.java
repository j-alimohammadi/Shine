package com.shine.core.security;

import com.shine.core.profile.domain.ShineUser;
import com.shine.core.profile.service.ShineUserService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserDetailService")
public class ShineUserDetailServiceImpl implements UserDetailsService {

    private final static Logger log = LoggerFactory.getLogger(ShineUserDetailServiceImpl.class);


    @Resource(name = "shineUserServiceImpl")
    protected ShineUserService shineUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        ShineUser shineUser = shineUserService
                .findUserByUsername(username).orElseThrow(() -> {
                    throw new UsernameNotFoundException(String.format("Username [%s] not found", username));
                });


        if (BooleanUtils.isFalse(shineUser.getFlagStatus())) {
            log.warn("User [{}] is disabled", username);
            throw new DisabledException(String.format("Username [%s] not found", username));
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(username, shineUser.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
