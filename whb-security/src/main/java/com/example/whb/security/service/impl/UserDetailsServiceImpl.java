package com.example.whb.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.whb.common.domain.LoginUser;
import com.example.whb.common.domain.SysUser;
import com.example.whb.common.utils.security.SecurityUtils;
import com.example.whb.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("into UserDetailsServiceImpl loadUserByUsername........................................");
        SysUser sysUser = getSysUser(username);
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        // 根据用户id获取用户的角色id,再根据角色id获取用户的权限菜单
        Set<String> authorities = new HashSet<>();
        authorities.add("user:find:all");
        authorities.add("admin:find:all");
        return fillLoginUser(sysUser, authorities);
    }

    /**
     * 填充登录用户信息
     *
     * @param sysUser
     * @param authorities
     * @return
     */
    private static LoginUser fillLoginUser(SysUser sysUser, Set<String> authorities) {
        LoginUser loginUser = new LoginUser(sysUser, authorities);
        loginUser.setUserId(sysUser.getId());
        BeanUtils.copyProperties(sysUser, loginUser);
        return loginUser;
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    private SysUser getSysUser(String username) {
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        return sysUser;
    }

    /**
     * 密码加密自动升级
     *
     * @param user
     * @param newPassword
     * @return
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        log.info("into UserDetailsServiceImpl updatePassword........................................");

        SysUser sysUser = getSysUser(user.getUsername());
        sysUser.setPassword(SecurityUtils.encodePassword(newPassword));
        sysUserMapper.updateById(sysUser);
        LoginUser loginUser = new LoginUser(sysUser);
        return loginUser;
    }
}
