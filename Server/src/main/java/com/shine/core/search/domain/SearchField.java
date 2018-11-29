package com.shine.core.search.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 *
 * <b>Note:</b> Adapted from BroadleafCommerce: https://github.com/BroadleafCommerce/BroadleafCommerce which is under
 * "Fair Use License" located at http://license.broadleafcommerce.org/fair_use_license-1.0.txt
 */
@Entity
@Table(name = "SHINE_SEARCH_FIELD", indexes = {
        @Index(name = "INDEX_ABBREVIATION", columnList = "ABBREVIATION", unique = true)
})
public class SearchField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ENTITY_TYPE")
    private String entityType;

    @Column(name = "FRIENDLY_NAME")
    private String friendlyName;

    @Column(name = "PROPERTY_NAME")
    private String propertyName;

    @NaturalId
    @Column(name = "ABBREVIATION", nullable = false)
    private String abbreviation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchField that = (SearchField) o;
        return Objects.equals(entityType, that.entityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType);
    }
}
