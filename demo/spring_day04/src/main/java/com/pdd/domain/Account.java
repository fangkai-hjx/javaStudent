package com.pdd.domain;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Float money;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Float getMoney() {
        return money;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUid() {
        return uid;
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
