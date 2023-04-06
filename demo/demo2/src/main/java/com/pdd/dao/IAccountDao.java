package com.pdd.dao;

import com.pdd.domain.Account;
import com.pdd.domain.AccountUser;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户，同时获取账户的所属用户名称以及他的地址信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(column = "uid",property = "user",one = @One(
                    select = "com.pdd.dao.IUserDao.findById",fetchType = FetchType.LAZY
            ))
    })
    List<Account> findAll();

    List<AccountUser> findAllAccount();


    @Select("select * from account where id = #{id}")
    Account findByUid(int uid);
}
