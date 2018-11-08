package com.shine.common.exception;

/**
 * @author Javad Alimohammadi [<bs.alimohammadi@gmail.com>]
 */
public class DenaInternalException extends ShineException {
    public DenaInternalException(String message) {
        super(message);
    }

    public DenaInternalException(String message, Throwable cause) {
        super(message, cause);
    }

}
