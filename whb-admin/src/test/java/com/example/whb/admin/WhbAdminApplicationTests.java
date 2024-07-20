package com.example.whb.admin;

import com.example.whb.common.utils.security.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhbAdminApplicationTests {
    @Autowired
    private SecurityUtils securityUtils;

    @Test
    void contextLoads() {
        String encode = securityUtils.passwordEncoder().encode("1234");
        System.out.println("encode = " + encode);
    }

}
