package com.shine.common.web;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public final class TextUtility {
    /**
     * Make an url with converting space to dash and lowercase all letter
     *
     * @param text
     * @return normalized URL
     */
    public static String normalizeToURL(String text) {
        return text.toLowerCase().replace(" ", "-");
    }

    /**
     * Make a new text with removing leading and ending space and change multiple space to single space
     *
     * @param text
     * @return
     */
    public static String normalizeText(String text) {
        return text.trim().replace("\\s+", " ");
    }

}
