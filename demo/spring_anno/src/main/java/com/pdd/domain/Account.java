package com.pdd.domain;

import java.io.Serializable;

public class Account implements Serializable {
    Integer id;
    Integer uid;
    Float money;

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMoney() {
        return money;
    }

    public Integer getUid() {
        return uid;
    }

    public Integer getId() {
        return id;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                '}';
    }
}
