package com.pdd.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 *  <context:component-scan base-package="com.pdd"/>
 * @Configuration：指定当前类是一个配置类
 * @component：作用通过注解指定spring在创建容器时需要扫描的包
 *
 * @Bean：用于把当前方法的放回值作为bean对象存入spring的ioc容器。
 * 属性：name：指定bean的id，默认是当前方法的名称
 * 细节：当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象，查找方式和Autowired注解的作用一样。
 *
 * @Import：用于导入其他对配置类，当我们使用import注解之后，有import注解对类就父配置类
 *
 * @PropertySource:指定properties文件的位置，关键字 classpath表示类路径下
 */
//@Configuration
@ComponentScan(basePackages = {"com.pdd.dao.impl","com.pdd.service.impl","com.pdd.config"})
@Import({JDBConfig.class})
@PropertySource(value = "classpath:jdbcConfig.properties")
public class SpringConfiguration {


//    @Bean(name = "datasource2")
//    public ComboPooledDataSource  B(){
//        try{
//            ComboPooledDataSource ds = new ComboPooledDataSource();
//            ds.setDriverClass("com.mysql.jdbc.Driver");
//            ds.setJdbcUrl("dbc:mysql://localhost:3306/test");
//            ds.setUser("root");
//            ds.setPassword("root");
//            return ds;
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
}
