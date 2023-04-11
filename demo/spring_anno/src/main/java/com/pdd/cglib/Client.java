package com.pdd.cglib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟一个消费者
 */
public class Client {
    public static void main(String[] args) {
        Producer p = new Producer();
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
        IProducer proxyProducer = (IProducer)Proxy.newProxyInstance(p.getClass().getClassLoader(),
                p.getClass().getInterfaces(), new InvocationHandler() {

                    /**
                     * 执行被代理对象的任何接口方法都会经过该方法
                     * @param proxy 代理对象的引用
                     *
                     * @param method the {@code Method} instance corresponding to
                     * the interface method invoked on the proxy instance.  The declaring
                     * class of the {@code Method} object will be the interface that
                     * the method was declared in, which may be a superinterface of the
                     * proxy interface that the proxy class inherits the method through.
                     *
                     * @param args an array of objects containing the values of the
                     * arguments passed in the method invocation on the proxy instance,
                     * or {@code null} if interface method takes no arguments.
                     * Arguments of primitive types are wrapped in instances of the
                     * appropriate primitive wrapper class, such as
                     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
                     *
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("====");
                        // 获取方法的执行参数
                        Object returnValue = null;
                        Float money = (Float) args[0];
                        if("saleProduct".equals(method.getName())){
                            returnValue = method.invoke(p,money*0.8f);
                        }
                        return returnValue;
                    }
                });
        proxyProducer.saleProduct(1000f);
    }
}
