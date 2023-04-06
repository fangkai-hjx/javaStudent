package com.pdd.jdbc;

import java.sql.*;

/**
 * 程序的耦合
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
//        DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        PreparedStatement statement = conn.prepareStatement("select * from account");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+":"+resultSet.getString(2));
        }
        statement.close();
        conn.close();
    }
}
