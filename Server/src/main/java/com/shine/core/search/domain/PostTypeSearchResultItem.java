package com.shine.core.search.domain;

import com.shine.core.domain.PostType;

import java.util.List;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class PostTypeSearchResultItem {

    private Long postId;

    private String questionTitle;

    private String body;

    private List<String> tags;

    private Long vote;

    private PostType postType;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }


    public static final class PostTypeSearchResultItemBuilder {
        private PostTypeSearchResultItem postTypeSearchResultItem;

        private PostTypeSearchResultItemBuilder() {
            postTypeSearchResultItem = new PostTypeSearchResultItem();
        }

        public static PostTypeSearchResultItemBuilder aPostTypeSearchResultItem() {
            return new PostTypeSearchResultItemBuilder();
        }

        public PostTypeSearchResultItemBuilder withPostId(Long postId) {
            postTypeSearchResultItem.setPostId(postId);
            return this;
        }

        public PostTypeSearchResultItemBuilder withQuestionTitle(String questionTitle) {
            postTypeSearchResultItem.setQuestionTitle(questionTitle);
            return this;
        }

        public PostTypeSearchResultItemBuilder withBody(String body) {
            postTypeSearchResultItem.setBody(body);
            return this;
        }

        public PostTypeSearchResultItemBuilder withTags(List<String> tags) {
            postTypeSearchResultItem.setTags(tags);
            return this;
        }

        public PostTypeSearchResultItemBuilder withVote(Long vote) {
            postTypeSearchResultItem.setVote(vote);
            return this;
        }

        public PostTypeSearchResultItemBuilder withPostType(PostType postType) {
            postTypeSearchResultItem.setPostType(postType);
            return this;
        }

        public PostTypeSearchResultItem build() {
            return postTypeSearchResultItem;
        }
    }
}
