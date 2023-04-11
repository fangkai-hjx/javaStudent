package com.pdd.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {
    public static void main(String[] args) {
        final Producer p = new Producer();
        // 动态代理：字节码随用随创建，随用随加载
        // 分类：基于接口/类
        // - 基于接口：
                // 涉及的类：Proxy
                // 提供者：JDK
            // 如何创建代理对象：使用newProxyInstance方法
            // 创建代理类至少实现一个接口，如果没有则不能使用。
            // newProxyInstance方法的参数：
            // ClassLoader loader:类加载器 用于加载代理对象字节码的，和被代理对象相同类加载器，固定写法
            // Class<>[]：用于让代理对象和被代理对象有相同的方法。
            // InvocationHandler：用于增强的代码，一般是该接口的实现类。
       // - 基于子类的代理对象
                // 涉及的类：Enhance
                // 提供者：第三方cglib库
            // 如何创建代理对象：使用enhance的create方法
            // 创建代理类不能是最终类。
            // Enhancer.create()的参数
                // Class：字节码 用于指定被代理对象的字节码
                // Callback：用于提供增强的代码，该接口的实现类.我们一般用该接口的子接口MethodInterceptor来实现。
        Producer producer = (Producer)Enhancer.create(p.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("====");
                // 获取方法的执行参数
                Object returnValue = null;
                Float money = (Float) args[0];
                if ("saleProduct".equals(method.getName())) {
                    returnValue = method.invoke(p, money * 0.8f);
                }
                return returnValue;
            }
        });
        producer.saleProduct(1000f);
    }
}
