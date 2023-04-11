package com.pdd.service.impl;

import com.pdd.dao.IAccountDao;
import com.pdd.domain.Account;
import com.pdd.service.IAccountService;
import com.pdd.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * 曾经XML的配置：<bean id="accountService" class="com.pdd.dao.impl.IAccountDaoImpl" s
 * cope="" init-method="" destroy-method="">
 * <property name="" value ="" / ref=""></property>
 * </bean>
 * <p>
 * 用于创建对象的：同bean标签
 *
     * @Component: value用于指定bean的id，不屑默认为首字母小写的同类名
     * @Controller：一般用于表现层
     * @Service：一般用于业务层
     * @Repository：一般用于持久层 以上三个注解的作用与Component是一摸一样的。是spring框架为我们明确的三层使用的疏解，使得分层更加清晰。
 *
 * 用于注入数据的：同property标签。
 *     @Autowired:
 *         - 作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配就可以注入。
 *         - 出现位置：可以是变量，可以方法
 *         - 细节：在使用注解的时候，可以不需要set方法。
 *     @Qualifier:
 *         - 作用：在按照类中注入的基础上在按照名称注入。它在给类成员注入时不能单独使用，但在给方法参数注入时可以。
 *         - 属性：value：用于注入bean的id
 *         - 出现位置，在变量上和@Autowired一起使用
 *     @Resource
 *          - 作用：按照bean但id注入。可以独立使用
 *          - 属性：name：bean id
 *     备注：以上三种注入都只是注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。
 *
 *      @Value
 *          - 作用：用于注入基本类型和String类型的数据
 *          - 属性：value用于指定数据的值，可以使用spring的spel（spring的el表达书 ${表达式}）
 *
 * 用于改变作用范围的：同scope标签
 *      @Scope
 *          - 作用：用于指定bean的作用范围
 *          - 属性：value指定范围的取值。常用取值：singleton，prototype
 * 生命周期相关的：同init-method destroy-method
 *      @PreDestory:
 *      @PostContruct:
 */
@Service("accountService2")
@Scope("prototype")
public class IAccountServiceImpl_OLD implements IAccountService {

    @Autowired
    @Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private IAccountDao accountDao;


    public IAccountServiceImpl_OLD() {
        System.out.println("==IAccountServiceImpl创建了==");
    }

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void transfer(Integer sourceUid,Integer targetUid,Float money){
        //2 执行操作
        Account sourceAccount = accountDao.findAccountByUid(sourceUid);
        Account targetAccount = accountDao.findAccountByUid(targetUid);
        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        targetAccount.setMoney(targetAccount.getMoney()+money);
        accountDao.update(sourceAccount);
        int i = 4/0;
        accountDao.update(targetAccount);
    }

    @Override
    public void updateAccount(int i) {

    }

    @Override
    public int deleteAccount() {
        return 0;
    }

    @PostConstruct
    public void init(){
        System.out.println("init=========");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy=========");
    }
    //--------------------------------------------------------

}
