package com.example.whb.common.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.whb.common.domain.LoginUser;
import com.example.whb.common.exception.CoderitlException;
import com.example.whb.common.properties.JwtConfigProperties;
import com.example.whb.common.service.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.whb.common.constants.Constants.LOGIN_TOKEN_KEY;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JwtConfigProperties jwtConfigProperties;
    // 签名算法
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取用于JWT签名的密钥。
     * 该方法首先将配置中的密钥字符串进行Base64编码，然后再进行Base64解码，以生成AES加密算法所需的SecretKey对象。
     * 这样做的目的是为了确保密钥的安全性，并且能够兼容Base64编码的密钥字符串。
     *
     * @return SecretKey 对象，用于JWT签名和验证。
     */
    @Override
    public SecretKey getSecretKey() {
        log.info("into TokenServiceImpl getSecretKey..................................");

        // 对密钥字符串进行Base64编码，然后再解码，以得到原始的字节数组形式的密钥。
        byte[] encodeKey = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));

        // 使用解码后的字节数组以及AES算法创建一个SecretKeySpec对象。
        // 这个对象将用于JWT的签名和验证过程。
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");

        return key;
    }


    /**
     * 创建JWT令牌。
     *
     * @param userId  用户ID，用于标记令牌所属用户。
     * @param subject 令牌主题，包含需要封装进令牌的数据。
     * @return 生成的JWT令牌字符串。
     */
    @Override
    public String createToken(Integer userId, Map<String, Object> subject) {
        // 记录进入createToken方法的日志
        log.info("into TokenServiceImpl createToken..................................");

        // 获取当前时间
        Date currentTime = new Date();
        // 计算令牌的过期时间
        // 失效时间 = 当前时间 + 失效时间的秒数
        Date expireTime = new Date(currentTime.getTime() + JwtConfigProperties.EXPIRETIME * 1000);

        // 初始化令牌的声明信息
        Map<String, Object> claims = new HashMap<>();

        // 使用Jwts.builder()构建JWT令牌，设置主题、签发时间、用户ID等信息
        JwtBuilder jwtBuilder = Jwts.builder()
                // 附加数据
                .setSubject(JSON.toJSONString(subject))
                // 签发日期
                .setIssuedAt(new Date())
                // 用户ID
                .setId(userId.toString())
                .signWith(this.signatureAlgorithm, this.getSecretKey());
        // 如果配置不允许令牌永不过期，则设置令牌的过期时间
        // Token 是否永不过期
        if (!JwtConfigProperties.NEVEREXPIRE) {
            // 记录设置过期时间的日志
            log.info("into TokenServiceImpl nerver-expire..................................");

            // Token 过期时间
            jwtBuilder.setExpiration(expireTime);
        }
        // 返回构建完成的JWT令牌字符串
        return jwtBuilder.compact();
    }


    /**
     * 解析JWT令牌。
     * <p>
     * 本方法用于验证JWT令牌的有效性，并从中提取声明信息。如果令牌有效，它将解析令牌并返回包含声明的Jws对象。
     * 如果令牌无效或已过期，则抛出JwtException异常，提示用户重新登录。
     *
     * @param token 待解析的JWT令牌字符串。
     * @return 解析后的Jws对象，其中包含JWT的声明部分。
     * @throws JwtException 如果令牌验证失败，则抛出此异常。
     */
    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        // 记录进入parseToken方法的日志信息。
        log.info("into TokenServiceImpl parseToken..................................");

        // 验证令牌是否有效。
        if (this.verifiyToken(token)) {
            // 使用预定义的秘钥解析令牌，并返回包含声明的Jws对象。
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(token);
            return claimsJws;
        }

        // 如果令牌无效，抛出自定义异常，提示用户重新登录。
        throw new CoderitlException("操作失败,请重新登录");
    }

    /**
     * 验证令牌的有效性。
     * 该方法使用JWT库解析令牌并检查其是否由有效的签名生成。
     * 如果令牌有效，即未过期且签名匹配，方法返回true。
     * 如果在解析过程中遇到任何问题，例如签名验证失败或令牌已过期，
     * 方法将捕获JwtException并返回false。
     *
     * @param token 待验证的JWT令牌。
     * @return 如果令牌有效则返回true，否则返回false。
     */
    @Override
    public boolean verifiyToken(String token) {
        // 记录进入verifyToken方法的日志信息
        log.info("into TokenServiceImpl verifiyToken()..................................");

        try {
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            // 使用预设的秘钥解析并验证JWT令牌
            Jwts.parser()
                    .setSigningKey(this.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            // 如果解析成功，说明令牌有效，返回true
            return true;
        } catch (JwtException e) {
            // 记录令牌验证失败的日志信息
            log.error("into TokenServiceImpl verifiyToken() error.................................");
            // 解析失败，说明令牌无效，返回false
            return false;
        }
    }

    /**
     * 刷新令牌。
     * <p>
     * 本方法用于当令牌接近过期或已过期时，通过现有令牌刷新生成一个新的令牌。
     * 刷新令牌的流程包括验证当前令牌的有效性，解析令牌中的用户信息，
     * 然后基于这些信息生成一个新的令牌。
     * </p>
     *
     * @param token 当前的令牌字符串。
     * @return 新生成的令牌字符串。
     * @throws CoderitlException 如果当前令牌无效，则抛出异常，提示用户重新登录。
     */
    @Override
    public String refreshToken(String token) {
        // 记录进入刷新令牌方法的日志
        log.info("into TokenServiceImpl refreshToken..................................");
        // 验证令牌是否有效
        if (this.verifiyToken(token)) {
            // 解析令牌，获取其中的声明信息
            Jws<Claims> claimsJws = this.parseToken(token);
            // 根据解析出的用户ID和主题信息，生成新的令牌
            return this.createToken(Integer.parseInt(claimsJws.getBody().getId()), JSON.parseObject(claimsJws.getBody().getSubject(), Map.class));
        }
        // 如果令牌无效，抛出异常，提示用户重新登录
        throw new CoderitlException("操作失败,请重新登录");
    }

    /**
     * 从HTTP请求中获取用户ID。
     * 该方法首先尝试从请求的头信息中获取JWT令牌，如果令牌不存在，则尝试从请求参数中获取。
     * 如果令牌存在且验证有效，则从令牌中解析用户ID并返回；如果令牌无效或不存在，则抛出异常，
     * 要求用户重新登录。
     *
     * @param request HTTP请求对象，用于获取请求头和参数。
     * @return 解析出的用户ID。
     * @throws CoderitlException 如果令牌无效或不存在，抛出此异常。
     */
    @Override
    public Integer fromTokenGetUserId(HttpServletRequest request) {
        String token = getTokenInfo(request);
        // 验证令牌的有效性
        if (this.verifiyToken(token)) {
            // 如果令牌有效，解析令牌并返回用户ID
            return Integer.parseInt(this.parseToken(token).getBody().getId());
        }
        // 如果令牌无效，抛出异常，要求用户重新登录
        throw new CoderitlException("操作失败,请重新登录");
    }

    private String getTokenInfo(HttpServletRequest request) {
        // 尝试从请求头中获取JWT令牌
        String token = request.getHeader(jwtConfigProperties.getHeader());
        // 如果请求头中没有JWT令牌，则尝试从请求参数中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(jwtConfigProperties.getHeader());
        }
        return token;
    }

    /**
     * 从HttpServletRequest中获取有效的JWT令牌。
     *
     * @param request HTTP请求对象，从中尝试获取JWT令牌。
     * @return 有效JWT令牌的字符串表示。
     * @throws CoderitlException 如果令牌无效或不存在，抛出此异常，提示用户重新登录。
     */
    @Override
    public String getToken(HttpServletRequest request) throws CoderitlException {
        // 尝试从请求头中获取JWT令牌信息
        // 尝试从请求头中获取JWT令牌
        String token = getTokenInfo(request);
        // 验证获取到的令牌是否有效
        // 验证令牌的有效性
        if (this.verifiyToken(token)) {
            return token;
        }
        // 如果令牌无效，抛出异常，要求用户重新登录
        // 如果令牌无效，抛出异常，要求用户重新登录
        throw new CoderitlException("操作失败,请重新登录");
    }

    @Override
    public String createToken(LoginUser loginUser) {
        String token = UUID.randomUUID().toString();
        loginUser.setToken(token);
        // TODO: 刷新Token
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_TOKEN_KEY, token);
        return createToken(loginUser.getSysUser().getId(), claims);
    }
}
