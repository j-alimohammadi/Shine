package com.shine.core.security.service;

import org.springframework.security.access.AccessDeniedException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineSecurity {


    /**
     * Check if current user has a specific permission.
     *
     * @param permission
     * @return true if user has permission, otherwise false
     */
    boolean isSpecificPermitted(String permission);

    /**
     * Check if current user has a specific permission.
     *
     * @param permission specific permission id
     * @throws AccessDeniedException if the user has no specified permission
     */
    void checkSpecificPermission(String permission) throws AccessDeniedException;


    /**
     * Check if current user has a specific permission with provided value.
     *
     * @param permission
     * @return true if user has permission, otherwise false
     */
    boolean isSpecificPermitted(String permission, Integer value);

}
