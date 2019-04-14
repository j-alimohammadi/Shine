package com.shine.core.security.domain;

import javax.persistence.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_USER_ROLE_XREF")
public class UserRoleXRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHINE_USER_ID")
    private ShineUser shineUser;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHINE_ROLE_ID")
    private ShineRole shineRole;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShineUser getShineUser() {
        return shineUser;
    }

    public void setShineUser(ShineUser shineUser) {
        this.shineUser = shineUser;
    }

    public ShineRole getShineRole() {
        return shineRole;
    }

    public void setShineRole(ShineRole shineRole) {
        this.shineRole = shineRole;
    }
}
