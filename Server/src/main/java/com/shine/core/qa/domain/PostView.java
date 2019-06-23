package com.shine.core.qa.domain;

import javax.persistence.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_POST_VIEW", indexes = {
        @Index(name = "IP_INDEX", columnList = "IP", unique = false),
        @Index(name = "USER_NAME_INDEX", columnList = "USER_ID", unique = false),
        @Index(name = "POST_ID_INDEX", columnList = "POST_ID", unique = false)
})
public class PostView {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IP", nullable = false)
    private String ip;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "POST_ID", nullable = false)
    private Long postId;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
