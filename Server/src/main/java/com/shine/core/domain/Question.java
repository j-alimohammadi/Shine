package com.shine.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@DiscriminatorValue("QUESTION")
public class Question extends Post {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "VIEW_COUNT")
    private Long viewCount;

    @Column(name = "VOTE")
    private Long vote;

    @JoinColumn(name = "ANSWER_ID")
    @OneToMany
    private List<Answer> answerList = new ArrayList<>();

    @JoinTable(
            name = "SHINE_QUESTION_TAG",
            joinColumns = {@JoinColumn(name = "QUESTION_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tagList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Answer> getAnswerList() {
        return Collections.unmodifiableList(answerList);
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }
}
