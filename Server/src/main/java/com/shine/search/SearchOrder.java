package com.shine.search;

import java.util.EnumSet;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum SearchOrder {
    RECENT_UPDATE("RECENT"),
    MOST_VOTES("VOTES"),
    ANSWER_COUNTS("ANSWERS"),
    VIEW_COUNTS("VIEWS");

    public String value;

    SearchOrder(String value) {
        this.value = value;
    }

    public static Optional<SearchOrder> getSearchOrder(String value) {
        for (SearchOrder searchOrder : EnumSet.allOf(SearchOrder.class)) {
            if (searchOrder.value.equalsIgnoreCase(value)) {
                return Optional.of(searchOrder);
            }
        }

        return Optional.empty();
    }


}
