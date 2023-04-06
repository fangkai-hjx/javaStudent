package com.pdd.ui;

import com.pdd.factory.BeanFactory;
import com.pdd.service.IAccountService;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        as.saveAccount();
    }
}
