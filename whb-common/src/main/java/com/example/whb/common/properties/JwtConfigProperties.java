package com.example.whb.common.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * yml 中 jwt 属性读取
 */

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "token")
public class JwtConfigProperties implements InitializingBean {
    /**
     * 令牌自定义标识
     */
    private String header;

    /**
     * 令牌密钥
     */
    private String secret;

    /**
     * 令牌有效期（默认30分钟）
     */
    private Long expireTime;

    /**
     * 是否永不过期
     */
    private Boolean neverExpire;

    public static String HEADER;
    public static String SECRET;
    public static Long EXPIRETIME;
    public static Boolean NEVEREXPIRE;


    /**
     * TODO: InitializingBean 作用是什么,属性该如何使用？
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("into JwtConfigProperties afterPropertiesSet...................................");
        HEADER = header;
        SECRET = secret;
        EXPIRETIME = expireTime;
        NEVEREXPIRE = neverExpire;
    }
}
