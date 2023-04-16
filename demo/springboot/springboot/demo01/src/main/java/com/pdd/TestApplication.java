package com.pdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 * 引导类：Spring boot应用的入口
 */

//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication // 组合注解
public class TestApplication {
    public static void main( String[] args ) {
        SpringApplication.run(TestApplication.class,args);
    }
}
