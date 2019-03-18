package com.shine.core.security.service;

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
     * Check if current user has a specific permission with provided value.
     *
     * @param permission
     * @return true if user has permission, otherwise false
     */
    boolean isSpecificPermitted(String permission, Integer value);

}
