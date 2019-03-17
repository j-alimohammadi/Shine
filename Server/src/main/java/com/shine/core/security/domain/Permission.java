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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PERMISSION_TYPE")
    private PermissionType permissionType;

    @Column(name = "TARGET")
    private String target;

    @Column(name = "VALUE")
    private Integer value;


    @JoinColumn(name = "SHINE_ROLE")
    @ManyToOne
    private ShineRole shineRole;


}
