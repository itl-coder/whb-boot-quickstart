package com.example.whb.service.impl;

import com.example.whb.common.domain.LoginUser;
import com.example.whb.common.domain.SysUser;
import com.example.whb.common.exception.CoderitlException;
import com.example.whb.common.service.TokenService;
import com.example.whb.common.utils.cache.CacheKeyUtils;
import com.example.whb.common.utils.ip.IpUtils;
import com.example.whb.common.utils.time.DateUtils;
import com.example.whb.domain.vo.LoginBody;
import com.example.whb.service.LoginService;
import com.example.whb.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import utils.AuthenticationContextHolder;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginBody loginBody) {
        // 验证码校验
        validateCaptcha(loginBody.getCode());
        // 登录前置校验
        loginPreCheck(loginBody.getUsername(), loginBody.getPassword());

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
        return generateToken(loginUser);
    }

    /**
     * 生成并缓存 Token
     *
     * @param loginUser
     * @return
     */
    private String generateToken(LoginUser loginUser) {
        String token = tokenService.createToken(loginUser);
        cacheKeyUtils.cacheToken(loginUser.getSysUser().getId(), token);
        return token;
    }


    /**
     * 用户信息前置检查
     *
     * @param username
     * @param password
     */
    private void loginPreCheck(String username, String password) {

    }

    /**
     * 验证码校验
     *
     * @param code
     */
    public void validateCaptcha(String code) {

    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Integer userId, HttpServletRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(request));
        sysUser.setLoginDate(DateUtils.getNowDate());
        sysUserService.updateById(sysUser);
    }
}
