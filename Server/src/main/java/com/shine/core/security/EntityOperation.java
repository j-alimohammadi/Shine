package com.shine.core.security;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum EntityOperation {
    READ("read"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");

    String op;

    EntityOperation(final String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }
}
