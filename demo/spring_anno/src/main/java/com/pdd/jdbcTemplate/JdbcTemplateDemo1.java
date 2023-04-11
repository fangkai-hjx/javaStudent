package com.pdd.jdbcTemplate;

import com.pdd.domain.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/test");
        ds.setUsername("root");
        ds.setPassword("root");
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        jt.execute("select * from account");
    }
}
