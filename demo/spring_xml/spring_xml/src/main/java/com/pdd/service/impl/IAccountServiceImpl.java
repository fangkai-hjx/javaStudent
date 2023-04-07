package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.dao.impl.IAccountDaoImpl;
import com.pdd.service.IAccountService;

import java.util.*;

public class IAccountServiceImpl implements IAccountService {
    // 经常变化的数据，不适合注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    // 复杂类型
    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String,String> myMap;
    private Properties myProps;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    private IAccountDao accountDao = new IAccountDaoImpl();

//    public IAccountServiceImpl(String name, Integer age, Date birthday) {
//        System.out.println("被初始化IAccountServiceImpl");
//        this.name = name;
//        this.age = age;
//        this.birthday = birthday;
//    }


    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public void saveAccount() {
        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(myMap);
        System.out.println(mySet);
        System.out.println(myProps);
    }

    public void init() {
        System.out.println("init");
    }

    public void destory() {
        System.out.println("destory");
    }

    @Override
    public String toString() {
        return "IAccountServiceImpl{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", accountDao=" + accountDao +
                '}';
    }
}
