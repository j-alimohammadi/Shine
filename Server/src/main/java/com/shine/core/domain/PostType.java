package com.shine.core.domain;

import java.util.EnumSet;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PostType {
    POST(Constants.POST, Post.class),
    QUESTION(Constants.QUESTION, Question.class),
    ANSWER(Constants.ANSWER, Answer.class);

    public String type;

    public Class postType;

    PostType(String type, Class postType) {
        this.type = type;
        this.postType = postType;
    }

    public static PostType getPostType(final String postType) {
        for (PostType post : EnumSet.allOf(PostType.class)) {
            if (post.type.equalsIgnoreCase(postType)) {
                return post;
            }
        }

        return null;
    }

    public interface Constants {
        String POST = "POST";
        String QUESTION = "QUESTION";
        String ANSWER = "ANSWER";
    }
}
