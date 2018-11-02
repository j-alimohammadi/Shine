package com.shine.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Table(name = "SHINE_QUESTION")
@Entity
public class Question extends Post {

    @Column(name = "TITLE",, nullable = false)
    private String title;

    @Column(name = "TAGS")
    private String tags;

    @JoinColumn(name = "ANSWER_ID", nullable = false)
    @OneToMany
    private List<Answer> answerList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswerList() {
        return Collections.unmodifiableList(answerList);
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
