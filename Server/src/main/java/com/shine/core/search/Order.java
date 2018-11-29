package com.shine.core.search;

import java.util.EnumSet;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum Order {
    ASCENDING("asc"),
    DESCENDING("des");


    public String value;

    Order(String value) {
        this.value = value;
    }


    public static boolean isAscending(final String orderString) {
        for (Order order : EnumSet.allOf(Order.class)) {
            if (order.value.equalsIgnoreCase(orderString)) {
                return true;
            }
        }

        return false;
    }

}
