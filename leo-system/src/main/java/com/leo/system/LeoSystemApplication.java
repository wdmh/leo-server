package com.leo.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.leo")
@MapperScan("com.leo.system.mapper")
public class LeoSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeoSystemApplication.class, args);
    }

}
