package com.shine.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@DiscriminatorValue(PostType.Constants.QUESTION)
public class Question extends Post {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "URL_ADDRESS")
    private String questionAddress;

    @Column(name = "VIEW_COUNT")
    private Long viewCount;

    @Column(name = "ANSWER_COUNT")
    private Long answerCount = 0L;


    @OneToMany(mappedBy = "question")
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
        return Collections.unmodifiableList(tagList);
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


    public void addTags(List<Tag> tagList) {
        tagList.addAll(tagList);
    }

    public Long getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Long answerCount) {
        this.answerCount = answerCount;
    }

    public String getQuestionAddress() {
        return questionAddress;
    }

    public void setQuestionAddress(String questionAddress) {
        this.questionAddress = questionAddress;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id= '" + getId() + '\'' +
                "title='" + title + '\'' +
                "} ";
    }
}
