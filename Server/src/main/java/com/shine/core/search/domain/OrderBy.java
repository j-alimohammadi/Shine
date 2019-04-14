package com.shine.core.search.domain;

import java.util.EnumSet;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum OrderBy {
    ASCENDING(Constants.ASCENDING),
    DESCENDING(Constants.DESCENDING);


    public String value;

    OrderBy(String value) {
        this.value = value;
    }


    public static boolean isAscending(final String orderString) {
        for (OrderBy orderBy : EnumSet.allOf(OrderBy.class)) {
            if (orderBy.value.equalsIgnoreCase(orderString)) {
                return true;
            }
        }

        return false;
    }


    public interface Constants {
        String ASCENDING = "asc";
        String DESCENDING = "des";
    }

}
