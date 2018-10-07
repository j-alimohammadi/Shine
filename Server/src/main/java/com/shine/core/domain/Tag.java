package com.shine.core.domain;

import javax.persistence.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USED_COUNT")
    private Long usedCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Long usedCount) {
        this.usedCount = usedCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
