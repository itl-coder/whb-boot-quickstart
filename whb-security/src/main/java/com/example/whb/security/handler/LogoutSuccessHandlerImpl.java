package com.example.whb.security.handler;

import com.example.whb.security.service.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //     LoginUser loginUser = tokenService.getLoginUser(request);
        //     if (StringUtils.isNotNull(loginUser)) {
        //         String userName = loginUser.getUsername();
        //         // 删除用户缓存记录
        //         tokenService.delLoginUser(loginUser.getToken());
        //     }
        //     ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.success("用户成功退出!")));
        //
    }
}
