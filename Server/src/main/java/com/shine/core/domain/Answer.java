package com.shine.core.domain;

import javax.persistence.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@DiscriminatorValue(PostType.Constants.ANSWER)
public class Answer extends Post {

    @Column(name = "IS_ACCEPTED")
    private Boolean accepted;

    @JoinColumn(name = "QUESTION_ID")
    @ManyToOne
    private Question question;

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id= '" + getId() + '\'' +
                "} ";
    }

}
