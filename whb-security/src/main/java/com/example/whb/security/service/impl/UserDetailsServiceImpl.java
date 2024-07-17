package com.example.whb.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.whb.domain.LoginUser;
import com.example.whb.domain.SysUser;
import com.example.whb.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("into UserDetailsServiceImpl loadUserByUsername........................................");
        SysUser sysUser = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        // 根据用户id获取用户的角色id,再根据角色id获取用户的权限菜单
        Set<String> authorities = new HashSet<>();
        authorities.add("user:find:all");
        authorities.add("admin:find:all");
        LoginUser loginUser = new LoginUser(sysUser, authorities);
        return loginUser;
    }
}
