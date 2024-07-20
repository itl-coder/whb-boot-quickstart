package com.example.whb.admin;

import com.example.whb.common.security.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhbAdminApplicationTests {
    @Autowired
    private SecurityUtil securityUtil;

    @Test
    void contextLoads() {
        String encode = securityUtil.passwordEncoder().encode("1234");
        System.out.println("encode = " + encode);
    }

}
