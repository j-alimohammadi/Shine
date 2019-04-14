package com.shine.common.exception;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
public class ShineInternalException extends ShineException {
    public ShineInternalException(String message) {
        super(message);
    }

    public ShineInternalException(String message, Throwable cause) {
        super(message, cause);
    }

}
