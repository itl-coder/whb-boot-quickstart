package com.example.whb.service.impl;

import com.example.whb.domain.LoginUser;
import com.example.whb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public String login(LoginUser loginUser) {
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
        AuthenticationContextHolder.setContext(authenticationToken);
        // TODO: 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
        authentication = authenticationManager.authenticate(authenticationToken);
        return (String) authentication.getPrincipal();
    }
}
