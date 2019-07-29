package com.yuhao.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.yuhao")
@EnableConfigurationProperties
public class BusApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BusApplication.class, args);
    }

}
