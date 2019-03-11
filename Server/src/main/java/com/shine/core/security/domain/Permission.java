package com.shine.core.security.domain;

import com.shine.core.security.PermissionType;

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

    private String description;

    private PermissionType permissionType;





}
