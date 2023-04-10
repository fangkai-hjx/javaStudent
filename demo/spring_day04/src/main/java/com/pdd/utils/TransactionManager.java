package com.pdd.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务管理器
 */
@Component("transactionManager")
@Aspect
public class TransactionManager {


    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* com.pdd.service.impl.*.*(..))")
    public void pt1(){}

    public void startTransaction() {
        try {
            System.out.println("开启事务");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void commitTransaction() {
        try {
            System.out.println("提交事务");
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollBackTransaction() {
        try {
            System.out.println("回滚事务");
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void releaseTransaction() {
        try {
            System.out.println("释放事务");
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Around("pt1()")
    public Object arroundAdvice(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            // 1 获取参数
            Object[] args = pjp.getArgs();
            this.startTransaction();
            rtValue =  pjp.proceed(args);
            this.commitTransaction();
            return rtValue;
        }catch (Throwable e){
            this.rollBackTransaction();
            throw new RuntimeException(e);
        }finally {
            this.releaseTransaction();
        }
    }
}
