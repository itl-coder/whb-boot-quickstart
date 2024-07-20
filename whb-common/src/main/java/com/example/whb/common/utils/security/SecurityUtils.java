package com.example.whb.common.utils.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityUtils {
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("into SecurityUtils passwordEncoder.....................");
        return new BCryptPasswordEncoder();
    }

    public  String encodePassword(String password) {
        return passwordEncoder().encode(password);
    }

    public boolean matchesPassword(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder().matches(rawPassword, encodedPassword);
    }

}
