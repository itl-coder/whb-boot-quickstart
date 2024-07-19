package com.example.whb.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface TokenService {
    /**
     * 生成一个合法的 Token 数据
     *
     * @param userId  这个Token的唯一Id(本次采用用户Id)
     * @param subject 所有附加的信息内容数据
     * @return 返回一个有效的Token数据字符串
     */
    public String createToken(Integer userId, Map<String, Object> subject);

    /**
     * 是根据Token字符串内容解析出其组成的信息(头信息与附加信息)
     *
     * @param token 要解析的Token完整数据
     * @return 返回一个Jws对象，其中包含了Token的头信息与附加信息
     * @throws JwtException
     */
    public Jws<Claims> parseToken(String token) throws JwtException;

    /**
     * 校验当前Token是否合法
     *
     * @param token
     * @return 返回true表示合法，false表示不合法
     */
    public boolean verfiyToken(String token);

    /**
     * 加密 key
     *
     * @return SecretKey
     */
    public SecretKey getSecretKey();

    /**
     * Token存在有效时间的定义,所以一定要提供有Token刷新机制
     *
     * @param token 原始的Token数据
     * @return 新的Token数据
     */
    public String refreshToken(String token);

    public Integer fromTokenGetUserId(HttpServletRequest request);

    public String getToken(HttpServletRequest request);
}
