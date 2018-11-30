package com.shine.core.search;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum EntityType {
    POST("post"), QUESTION("question");

    public String value;

    EntityType(String value) {
        this.value = value;
    }
}
