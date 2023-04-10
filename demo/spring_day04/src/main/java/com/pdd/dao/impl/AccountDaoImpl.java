package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import com.pdd.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

//    @Autowired
//    private QueryRunner runner;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConnectionUtils connectionUtils;

    public List<Account> findAll() {
//        try {
//            return runner.query(connectionUtils.getThreadConnection(), "select * from account", new BeanListHandler<Account>(Account.class));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
    }

    public Account findAccount(Integer uid) {
//        try {
//            return runner.query(connectionUtils.getThreadConnection(), "select * from account where  uid = ?", uid, new BeanHandler<Account>(Account.class));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        Account account = jdbcTemplate.queryForObject("select * from account where  uid = ?", new RowMapper<Account>() {
            public Account mapRow(ResultSet rs, int i) throws SQLException {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setUid(rs.getInt(2));
                a.setMoney(rs.getFloat(3));
                return a;
            }
        }, uid);
        return account;
    }

    public void updateAccount(Integer uid, Float money) {
        try {
            int update = jdbcTemplate.update("update account set money = ? where uid = ?", money, uid);
            System.out.println(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
