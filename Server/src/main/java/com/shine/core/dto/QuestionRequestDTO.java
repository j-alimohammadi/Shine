package com.shine.core.dto;

import java.util.ArrayList;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class QuestionRequestDTO {

    private Long id;

    private String title;

    private String body;

    private ArrayList<Long> tagIds = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(ArrayList<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
