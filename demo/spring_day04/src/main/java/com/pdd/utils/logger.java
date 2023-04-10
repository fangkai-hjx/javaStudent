package com.pdd.utils;

public class logger {
    /**
     * 前置通知
     */
    public void before(){
        System.out.println("前置通知。。。。。。。。。。。。。。");
    }

    /**
     * 后置通知
     */
    public void returnAdvice(){
        System.out.println("后置通知。。。。。。。。。。。。。。");
    }

    /**
     * 后置通知
     */
    public void after(){
        System.out.println("最终通知。。。。。。。。。。。。。。");
    }
}
