package com.pdd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接的工具类：从数据源中获取一个连接，并且实现和线程的绑定
 */

@Component("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    @Autowired
    @Qualifier("datasource")
    private DataSource dataSource;

    public Connection getThreadConnection() {
        Connection connection = tl.get();
        if (connection == null) {
            // 从数据源中获取一个连接，并且存入ThreadLocal中
            try {
                connection = dataSource.getConnection();
                tl.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void removeConnection() {
        tl.remove();
    }
}
