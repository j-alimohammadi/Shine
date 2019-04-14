package com.shine.common.exception;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
public abstract class ShineException extends RuntimeException {

    public ShineException(String message) {
        super(message);
    }

    public ShineException(String message, Throwable cause) {
        super(message, cause);
    }

}
