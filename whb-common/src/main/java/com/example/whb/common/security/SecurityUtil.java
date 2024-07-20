package com.example.whb.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityUtil {
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("into SecurityUtil passwordEncoder.....................");

        return new BCryptPasswordEncoder();
    }
}
