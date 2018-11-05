package com.shine.core.domain;

import com.shine.common.persistence.TableGeneratorParameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.shine.core.domain.Tag.*;

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
        ),
        @NamedQuery(
                name = FIND_TAGS_BY_NAME,
                query = "SELECT tag FROM Tag tag WHERE tag.name in (:tagNames)"
        )
}
)
@Entity
@Table(name = "SHINE_TAG", indexes = {@Index(name = "INDEX_NAME", columnList = "NAME", unique = true)})
public class Tag {
    public static final String FIND_TAGS_BY_ID = "FIND_TAGS_BY_ID";

    public static final String FIND_TAGS_BY_NAME = "FIND_TAGS_BY_NAME";

    public static final String FIND_TAGS_COUNT_BY_ID = "FIND_TAGS_COUNT_BY_ID";


    @TableGenerator(name = TableGeneratorParameter.GENERATOR_NAME, // name of generator
            table = TableGeneratorParameter.TABLE_GENERATOR_NAME, // name of table
            pkColumnName = TableGeneratorParameter.COLUMN_NAME, // Column name of generator name
            valueColumnName = TableGeneratorParameter.COLUMN_VALUE, // Column name of generator value
            pkColumnValue = "TAG_ID_GEN", // a row in the table that has a value corresponding to the the value we inserted
            allocationSize = TableGeneratorParameter.ALLOCATION_SIZE)
    @Id
    @GeneratedValue(generator = TableGeneratorParameter.GENERATOR_NAME, strategy = GenerationType.TABLE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USED_COUNT")
    private Long usedCount = 0L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
