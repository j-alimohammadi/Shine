package com.shine.core.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Table(name = "SHINE_QUESTION")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BODY")
    private String body;

    private Date createdTimeStamp;

    private Date editedTimeStamp;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
