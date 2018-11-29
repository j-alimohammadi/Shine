package com.shine.core.search;

import java.util.EnumSet;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum OrderByParameter {
    RECENT_UPDATE("post.editedTimeStamp"),
    MOST_VOTES("post.vote"),
    ANSWER_COUNTS("answer.answerCount"),
    VIEW_COUNTS("answer.viewCount");

    public String value;

    OrderByParameter(String value) {
        this.value = value;
    }

    public static Optional<OrderByParameter> getOrderByParameter(String value) {
        for (OrderByParameter orderByParameter : EnumSet.allOf(OrderByParameter.class)) {
            if (orderByParameter.value.equalsIgnoreCase(value)) {
                return Optional.of(orderByParameter);
            }
        }

        return Optional.empty();
    }

    public static boolean isExist(String value) {
        return getOrderByParameter(value).isPresent();
    }


}
