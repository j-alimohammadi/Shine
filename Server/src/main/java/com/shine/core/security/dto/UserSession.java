package com.shine.core.security.dto;

import com.shine.core.security.domain.Role;
import com.shine.core.security.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class UserSession {
    private UUID id;
    private User user;
    private Set<String> roles = new HashSet<>();
    
    




}
