package com.pdd.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {
    private String username;
    private String password;
    private Double money;

    private List<User> list;
    private Map<String ,User> map;

//    private User user;

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Double getMoney() {
        return money;
    }

    public String getUsername() {
        return username;
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }
   public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public Map<String, User> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
