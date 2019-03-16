package com.shine.core.security.utils;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public final class TokenMasker {
    private static final int MASK_LENGTH = 23;

    private static final String MASK_STRING = "***";

    public static String maskToken(final String token) {
        if (token.length() < MASK_LENGTH) {
            return MASK_STRING;
        } else {
            return MASK_STRING + token.substring(MASK_LENGTH);
        }
    }
}
