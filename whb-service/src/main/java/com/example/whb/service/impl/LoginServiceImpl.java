package com.example.whb.service.impl;

import com.example.whb.common.constants.CacheConstant;
import com.example.whb.common.exception.CoderitlException;
import com.example.whb.common.service.TokenService;
import com.example.whb.common.utils.cache.CacheKeyUtils;
import com.example.whb.domain.LoginUser;
import com.example.whb.domain.vo.LoginBody;
import com.example.whb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utils.AuthenticationContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录认证处理
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CacheKeyUtils cacheKeyUtils;

    @Override
    public String login(LoginBody loginBody) {
        // TODO: 用户参数校验
        // TODO: 验证码校验
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
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
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return createToken(loginUser);
    }

    private String createToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginUser.getSysUser().getUsername());
        Integer userId = loginUser.getSysUser().getId();
        String token = tokenService.createToken(userId, claims);
        cacheKeyUtils.cacheToken(userId, token);
        return token;
    }
}
