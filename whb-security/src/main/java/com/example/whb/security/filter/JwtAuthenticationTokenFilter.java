package com.example.whb.security.filter;


import com.example.whb.common.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Token过滤器 验证Token有效性
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info("into JwtAuthenticationTokenFilter doFilterInternal.................................................");
        // 检查是否是Swagger的请求，并排除
        if (isSwaggerRequest(request)) {
            chain.doFilter(request, response);
            return;
        }
        // 其余的认证逻辑保持不变
        String token = tokenService.getToken(request);
        if (tokenService.verifiyToken(token)) {
            Integer userId = tokenService.fromTokenGetUserId(request);
            if (ObjectUtils.isNotEmpty(userId)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isSwaggerRequest(HttpServletRequest request) {
        String path = request.getServletPath();
        List<String> swaggerPaths = Arrays.asList(
                "/swagger-ui",
                "/swagger-resources",
                "/api-docs",
                "/webjars",
                "/v3/api-docs",
                "/v2/api-docs",
                "/configuration",
                "/doc.html",
                "/swagger-ui.html",
                "/auth/**"
        );
        return swaggerPaths.stream().anyMatch(path::startsWith);
    }

}
