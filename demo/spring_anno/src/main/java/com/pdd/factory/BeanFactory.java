package com.pdd.factory;

import com.pdd.domain.Account;
import com.pdd.service.IAccountService;
import com.pdd.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component("beanFactory")
public class BeanFactory {
    @Autowired
    private IAccountService accountService;

//    @Autowired
    private TransactionManager transactionManager;

    /**
     * 获取service代理对象
     * @return
     */
    @Bean("proxyAccountService")
    public IAccountService getAccountService(){
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try{
                            //1 开启事务
                            transactionManager.beginTransaction();
                            //2 执行操作
                            System.out.println("==================");
                            rtValue = method.invoke(accountService,args);
                            //3 提交事务
                            transactionManager.commit();
                            return rtValue;
                        }catch (Exception e){
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            transactionManager.release();
                        }
                    }
                });
    }

}
