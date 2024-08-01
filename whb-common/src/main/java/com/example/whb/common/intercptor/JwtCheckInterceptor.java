package com.example.whb.common.intercptor;

import com.example.whb.common.annotation.TokenCheck;
import com.example.whb.common.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class JwtCheckInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("into JwtCheckInterceptor preHandle..........................");
        // 不处理拦截操作
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 强制类型转换的原因: 可以获取当前要执行的控制器方法反射对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断该方法上是否有提供的指定的注解
        if (method.isAnnotationPresent(TokenCheck.class)) {
            // 获取指定注解
            TokenCheck tokenCheck = method.getAnnotation(TokenCheck.class);
            // true: 表示要进行 Token 检查
            if (tokenCheck.required()) {
                // 获取 Token 数据
                tokenService.getToken(request);
            }
            return true;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
