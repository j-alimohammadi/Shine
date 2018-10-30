package com.shine.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.shine.core.domain.Tag.FIND_TAGS_BY_ID;
import static com.shine.core.domain.Tag.FIND_TAGS_COUNT_BY_ID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@NamedQueries({
        @NamedQuery(
                name = FIND_TAGS_BY_ID,
                query = "SELECT tag FROM Tag tag WHERE tag.id in (:tagId)"
        ),
        @NamedQuery(
                name = FIND_TAGS_COUNT_BY_ID,
                query = "SELECT COUNT(tag) FROM Tag tag WHERE tag.id in (:tagId)"
        )
}
)
@Entity
@Table(name = "SHINE_TAG")
public class Tag {
    public static final String FIND_TAGS_BY_ID = "FIND_TAGS_BY_ID";

    public static final String FIND_TAGS_COUNT_BY_ID = "FIND_TAGS_COUNT_BY_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USED_COUNT")
    private Long usedCount;

    @ManyToMany(mappedBy = "tagList")
    private List<Question> questions = new ArrayList<>();


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

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
