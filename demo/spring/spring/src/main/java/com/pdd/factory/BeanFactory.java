package com.pdd.factory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建Bean对象的工厂
 * Bean：可重用组件的含义
 * JavaBean：用Java语言写的可重用组件。
 * javabean > 实体类
 * <p>
 * 它就是创建我们service和dao对象的。
 * 1.需要一个配置文件来配置我们的service和dao配置的内存：唯一标识：全类名
 * 2.通过读取配置文件中的信息，反射创建对象。
 */
public class BeanFactory {

    private static final Map<String,Object> beans;

    static {
        try {
            Properties props = new Properties();
            props.load(BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties"));
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration<Object> keys = props.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String path = props.getProperty(key);
                Object o = Class.forName(path).newInstance();
                beans.put(key,o);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化配置文件失败！");
        }
    }

    /**
     * 根据bean的名称获取bean对象
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        System.out.println(beans);
        return beans.get(beanName);
//        String path = props.getProperty(beanName);
//        Object bean = null;
//        try {
//            bean = Class.forName(path).newInstance();
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return bean;
    }
}
