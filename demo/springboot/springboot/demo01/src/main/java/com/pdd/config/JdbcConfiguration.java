package com.pdd.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
//@PropertySource("classpath:jdbc.properties") // 读取配置文件
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration {


    // 方式1：自动注入
//    @Autowired
    private JdbcProperties jdbcProperties;

    // 方式2：构造函数注入
//    public JdbcConfiguration(JdbcProperties jdbcProperties){
//        this.jdbcProperties = jdbcProperties;
//    }

    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    @Bean
    public DataSource createDataSource(JdbcProperties jdbcProperties){// 方法3：方法的形参
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcProperties.getDriver());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        return dataSource;
    }
}
