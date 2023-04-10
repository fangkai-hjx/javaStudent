package com.pdd.jdbctemplate;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JDBC最基本用法
 */
public class JdbcTemplateDemo01 {
    public static void main(String[] args) {
//        //准备数据源
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClass("com.mysql.jdbc.Driver");
//        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
//        ds.setUser("root");
//        ds.setPassword("root");
//        JdbcTemplate jt = new JdbcTemplate(ds);
//        jt.execute("insert into account (id,uid,money) values (11,44,1000)");

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        jt.execute("insert into account (id,uid,money) values (11,44,1000)");
    }
}

// 3200
// 2400 6400