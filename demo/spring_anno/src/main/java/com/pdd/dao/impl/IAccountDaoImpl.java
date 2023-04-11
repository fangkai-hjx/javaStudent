package com.pdd.dao.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import com.pdd.domain.User;
import com.pdd.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao1")
public class IAccountDaoImpl implements IAccountDao {

    @Autowired
    private QueryRunner runner;

//    @Autowired
    private ConnectionUtils connectionUtils;

    public IAccountDaoImpl(){
        System.out.println("===IAccountDaoImpl1被创建了==");
    }
    @Override
    public void saveAccount() {
        System.out.println("保存账户信息1");
    }

    @Override
    public List<Account> findAll() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Account account){
        try {
            runner.update(connectionUtils.getThreadConnection(),"update account set money = ? where id = ?", account.getMoney(),account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByUid(Integer uid) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),"select * from account where uid = ?",new BeanListHandler<Account>(Account.class),uid);
            if (accounts == null || accounts.size() == 0){
                return null;
            }
            if (accounts.size() > 1){
                throw new RuntimeException("结果集不唯一，数据异常");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
