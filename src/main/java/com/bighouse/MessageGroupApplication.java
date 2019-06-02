package com.bighouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.bighouse.mapper")
@ComponentScan(basePackages = {"com.bighouse", "org.n3r.idworker"})
public class MessageGroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageGroupApplication.class, args);
    }

}
