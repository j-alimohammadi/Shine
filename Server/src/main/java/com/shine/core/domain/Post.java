package com.shine.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@Table(name = "SHINE_POST")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "POST_TYPE")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BODY", nullable = false, length = 2000)
    private String body;

    @Column(name = "CREATED_TIMESTAMP", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimeStamp;

    @Column(name = "EDITED_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedTimeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Date createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public Date getEditedTimeStamp() {
        return editedTimeStamp;
    }

    public void setEditedTimeStamp(Date editedTimeStamp) {
        this.editedTimeStamp = editedTimeStamp;
    }
}
