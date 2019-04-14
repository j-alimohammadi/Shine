package com.shine.core.qa.domain;

import java.util.EnumSet;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PostType {
    POST(Constants.POST, Post.class),
    QUESTION(Constants.QUESTION, Question.class),
    ANSWER(Constants.ANSWER, Answer.class);

    public String typeName;

    public Class typeClass;

    PostType(String typeName, Class typeClass) {
        this.typeName = typeName;
        this.typeClass = typeClass;
    }

    public static PostType getPostType(final String postType) {
        for (PostType post : EnumSet.allOf(PostType.class)) {
            if (post.typeName.equalsIgnoreCase(postType)) {
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
