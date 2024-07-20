package com.example.whb.common.utils.cache;

import com.example.whb.common.constants.CacheConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CacheKeyUtils {
    private final RedisTemplate<String, String> redisTemplate;

    public void cacheToken(Integer userId, String token) {
        log.info("into CacheKeyUtils cacheToken............");
        redisTemplate.opsForValue().set(CacheConstant.LOGIN_TOKEN_KEY + userId, token);
    }
}
