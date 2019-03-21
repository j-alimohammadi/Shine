package com.shine.core.security.domain;

import com.shine.core.security.PermissionType;
import com.shine.core.security.PermissionValueType;

import javax.persistence.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Entity
@Table(name = "SHINE_PERMISSION")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERMISSION_TYPE")
    private PermissionType permissionType;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "PERMISSION_VALUE_TYPE")
    private PermissionValueType permissionValueType;

    @Column(name = "VALUE")
    private Integer value;

    @JoinColumn(name = "SHINE_ROLE")
    @ManyToOne
    private ShineRole shineRole;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public PermissionValueType getPermissionValueType() {
        return permissionValueType;
    }

    public void setPermissionValueType(PermissionValueType permissionValueType) {
        this.permissionValueType = permissionValueType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ShineRole getShineRole() {
        return shineRole;
    }

    public void setShineRole(ShineRole shineRole) {
        this.shineRole = shineRole;
    }
}
