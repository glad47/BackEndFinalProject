package com.jugu.www.pcbonlinev2.domain.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserDetailsDTO implements Serializable, UserDetails {

    private static final long serialVersionUID = 8891923450163934178L;

    private Integer id;
    private String username;
    private String password;
    private Integer invalidMark;


    private Integer businessId;
    private String businessName;
    private String userSystemId;
    private Integer auditMark; //是否需要审核标识(0默认需要 1不需要)

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(String userSystemId) {
        this.userSystemId = userSystemId;
    }

    public Integer getAuditMark() {
        return auditMark;
    }

    public void setAuditMark(Integer auditMark) {
        this.auditMark = auditMark;
    }


    public void setInvalidMark(Integer invalidMark) {
        this.invalidMark = invalidMark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户账号是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.invalidMark == 0;
    }
}
