package com.shine.core.security.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_ROLE")
public class ShineRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEFAULT_ROLE")
    private boolean defaultRole;


    @OneToMany(mappedBy = "shineRole")
    private Set<UserRoleXRef> userRoleXRefs = new HashSet<>();

    @OneToMany
    private Set<Permission> permissions = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    public Set<UserRoleXRef> getUserRoleXRefs() {
        return userRoleXRefs;
    }

    public void setUserRoleXRefs(Set<UserRoleXRef> userRoleXRefs) {
        this.userRoleXRefs = userRoleXRefs;
    }
}
