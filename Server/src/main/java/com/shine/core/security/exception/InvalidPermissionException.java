package com.shine.core.security.exception;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class InvalidPermissionException extends RuntimeException {
    public InvalidPermissionException(String message) {
        super(message);
    }

    public InvalidPermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
