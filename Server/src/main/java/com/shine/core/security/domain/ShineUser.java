package com.shine.core.security.domain;

import com.shine.core.security.service.OAuthProvider;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Entity
@Table(name = "SHINE_USER", indexes = {
        @Index(name = "INDEX_LOGIN", columnList = "LOGIN", unique = true)
})
public class ShineUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NaturalId
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Transient
    private String unEncodedPassword;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "REPUTATION")
    private Integer reputation;

    @Column(name = "REGISTER_TIME")
    private Date registerTime;

    @Column(name = "FLAG_STATUS")
    private Boolean flagStatus;

    @Column(name = "OAUTH_PROVIDER")
    private OAuthProvider OAuthProvider;

    @OneToMany(mappedBy = "shineUser")
    private Set<UserRoleXRef> userRoles = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer repudiation) {
        this.reputation = repudiation;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Boolean getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(Boolean flagStatus) {
        this.flagStatus = flagStatus;
    }

    public String getUnEncodedPassword() {
        return unEncodedPassword;
    }

    public void setUnEncodedPassword(String unEncodedPassword) {
        this.unEncodedPassword = unEncodedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRoleXRef> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleXRef> shineRoles) {
        this.userRoles = shineRoles;
    }

    public OAuthProvider getOAuthProvider() {
        return OAuthProvider;
    }

    public void setOAuthProvider(OAuthProvider OAuthProvider) {
        this.OAuthProvider = OAuthProvider;
    }

    public void addRole(ShineRole shineRole) {
        UserRoleXRef userRoleXRef = new UserRoleXRef();
        userRoleXRef.setShineRole(shineRole);
        userRoleXRef.setShineUser(this);

        userRoles.add(userRoleXRef);
    }


    public static final class ShineUserBuilder {
        private ShineUser shineUser;

        private ShineUserBuilder() {
            shineUser = new ShineUser();
        }

        public static ShineUserBuilder aShineUser() {
            return new ShineUserBuilder();
        }

        public ShineUserBuilder withId(Long id) {
            shineUser.setId(id);
            return this;
        }

        public ShineUserBuilder withEmail(String email) {
            shineUser.setEmail(email);
            return this;
        }

        public ShineUserBuilder withLogin(String login) {
            shineUser.setLogin(login);
            return this;
        }

        public ShineUserBuilder withActiveStatusFlag(Boolean flag) {
            shineUser.setFlagStatus(flag);
            return this;
        }

        public ShineUserBuilder withUnEncodedPassword(String password) {
            shineUser.setUnEncodedPassword(password);
            return this;
        }

        public ShineUserBuilder withRepudiation(Integer repudiation) {
            shineUser.setReputation(repudiation);
            return this;
        }

        public ShineUserBuilder withRegisterTime(Date registerTime) {
            shineUser.setRegisterTime(registerTime);
            return this;
        }

        public ShineUser build() {
            return shineUser;
        }
    }
}
