package com.example.whb.service.impl;

import com.example.whb.domain.LoginUser;
import com.example.whb.domain.SysUser;
import com.example.whb.domain.vo.LoginBody;
import com.example.whb.exception.CoderitlException;
import com.example.whb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utils.AuthenticationContextHolder;

/**
 * 登录认证处理
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginBody loginBody) {
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getUsername());
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UsernameNotFoundException("用户不存在");
            } else {
                throw new CoderitlException("服务器异常");
            }
        }
        LoginUser sysUser = (LoginUser) authentication.getPrincipal();
        return sysUser.toString() ;
    }
}
