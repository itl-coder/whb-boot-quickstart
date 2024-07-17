package com.example.whb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WhbApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhbApplication.class, args);
        // 输出美化信息
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application is running! Access URLs:\n\t" +
                        "Documentation: \t{}\n\t" +
                        "Database: \t{}\n\t" +
                        "InitUserName: {}\n\t" +
                        "InitPassword: {}\n\t" +
                        "----------------------------------------------------------",
                "http://localhost:8081/doc.html", "http://localhost:3306/school_driv","user","1234");

    }
}
