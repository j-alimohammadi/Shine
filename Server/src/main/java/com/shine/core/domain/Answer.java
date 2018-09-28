package com.shine.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Table(name = "SHINE_ANSWER")
@Entity
public class Answer extends Post {

    @Column(name = "IS_ACCEPTED")
    private Boolean accepted;



}
