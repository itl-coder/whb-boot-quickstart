package com.example.whb.security.handler;


import com.example.whb.common.response.AjaxResult;
import com.example.whb.security.utils.ResponseUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 认证失败处理类 返回未授权
 */

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        Integer code = HttpStatus.UNAUTHORIZED.value();
        log.error("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ResponseUtils.responseJson(response, AjaxResult.error(code, "请求访问：{}，认证失败，无法访问系统资源"));
    }
}
