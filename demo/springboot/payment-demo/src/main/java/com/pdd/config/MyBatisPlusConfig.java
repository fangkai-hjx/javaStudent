package com.pdd.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.pdd.mapper")
@EnableTransactionManagement
public class MyBatisPlusConfig {
}
