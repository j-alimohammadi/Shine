package com.shine.core.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@DiscriminatorValue("ANSWER")
public class Answer extends Post {

    @Column(name = "IS_ACCEPTED")
    private Boolean accepted;

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
