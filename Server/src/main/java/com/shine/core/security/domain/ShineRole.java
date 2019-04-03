package com.shine.core.security.domain;

import com.shine.core.security.RoleType;
import com.shine.core.security.jpa.RoleTypeConverter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_ROLE", indexes = {
        @Index(name = "INDEX_ROLE_NAME", columnList = "NAME", unique = true)
})
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

    @Convert(converter = RoleTypeConverter.class)
    @Column(name = "ROLE_TYPE")
    private RoleType roleType;


    @OneToMany(mappedBy = "shineRole")
    private Set<UserRoleXRef> userRole = new HashSet<>();

    @OneToMany(mappedBy = "shineRole")
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

    public Set<UserRoleXRef> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRoleXRef> userRoleXRefs) {
        this.userRole = userRoleXRefs;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
