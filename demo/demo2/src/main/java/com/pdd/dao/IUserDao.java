package com.pdd.dao;

import com.pdd.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户，同时获取用户下所有账户信息
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "sex", property = "userSex"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.pdd.dao.IAccountDao.findByUid", fetchType = FetchType.LAZY
                    ))})
    List<User> findAll();


    @Select("select * from user where username like #{username}")
    List<User> findUserByName(String name);


    @Select("select * from user where id = #{id}")
    @ResultMap(value = "userMap")
    User findById(Integer id);

    @Insert("insert into user (username,sex,birthday,address) values (#{username},#{userSex},#{birthday},#{address})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false, resultType = Integer.class, statement = "select last_insert_id()")
    int saveUser(User user);

    @Delete("delete from user where id = #{uid}")
    int deleteUser(Integer uid);

    @Select("select count(*) from user")
    int findTotal();
}
