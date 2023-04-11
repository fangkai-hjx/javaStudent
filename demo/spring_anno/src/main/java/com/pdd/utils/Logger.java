package com.pdd.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，它里面提供了公共的代码块
 */
@Component("logger")
@Aspect//表示当前类是一个切面
public class Logger {
    @Pointcut("execution(* com.pdd.service.impl.*.*(..))")
    private void pt1(){
        System.out.println("=========pt1=========");
    };

    /**
     * 前置通知
     */
    @Before("pt1()")
    public void beforePrintLog(){
        System.out.println("前置通知");
    }
    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public void returnPrintLog(){
        System.out.println("后置通知");
    }
    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public void afterThrowingPrintLog(){
        System.out.println("异常通知");
    }
    /**
     * 最终通知
     */
    @After("pt1()")
    public void afterPrintLog(){
        System.out.println("最终通知");
    }

    /**
     * 环绕通知：
     *      - 问题：当我们配置了环绕通知后，切入点方法没有执行，而通知方法执行了。
     *      - 分析：通过对比动态代理中的环绕通知，发现应该有明确的切入点的方法调用，而我们代码中没有。
     *      - 解决：
     *          spring框架提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed（），此方法
     *          相当于明确调用切入点方法，该接口可以作为环绕通知的方法参数。
     *      - 其实环绕通知是提供了一种手动通过代码的方式手动控制方法增强。
     */
    public Object aroundPrintLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();
            System.out.println("前置通知");
            rtValue = pjp.proceed(args);
            System.out.println("后置通知");
            return rtValue;
        }catch (Throwable e){
            System.out.println("异常通知");
            throw new RuntimeException(e);
        }finally {
            System.out.println("最终通知");
        }

    }
}
