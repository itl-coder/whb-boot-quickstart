package com.example.whb.domain;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


/**
 * 登录用户身份权限
 */
@Data
@Slf4j
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    private SysUser sysUser;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 权限列表
     */
    private Set<String> permissions;


    public LoginUser() {
    }

    public LoginUser(SysUser sysUser, Set<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("into LoginUser getAuthorities...........................");
        return null;
    }

    @Override
    public String getPassword() {
        log.info("into LoginUser getPassword...........................");
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("into LoginUser getUsername...........................");
        return sysUser.getUsername();
    }

    /**
     * 账号没有过期状态(true账号没有过期，false账号已经过期)
     *
     * @return true
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        log.info("into LoginUser isAccountNonExpired: {}", sysUser.getExpired().equals(0));
        return sysUser.getExpired().equals(0);
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return true
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        log.info("into LoginUser isAccountNonLocked: {}", sysUser.getLocked().equals(0));
        return sysUser.getLocked().equals(0);
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return true
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        log.info("into LoginUser isCredentialsNonExpired: {}", sysUser.getExpired().equals(0));
        return sysUser.getExpired().equals(0);
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        log.info("into LoginUser isEnabled: {}", sysUser.getEnabled().equals(0));
        return sysUser.getEnabled().equals(0);
    }
}
