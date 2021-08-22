package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
    以下程序的耦合度过高，解耦的思路如下
        1.使用反射来创建对象，而避免使用new关键字
        2.通过读取配置文件来获取要创建的对象的全限定类名
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws Exception{
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "193196");
        PreparedStatement pstm = connection.prepareStatement("select * from user where  username like ?");
        pstm.setString(1, "%龚%");
        ResultSet resultSet = pstm.executeQuery();
        while(resultSet.next()) {
            System.out.println(resultSet.getString("username"));
        }
        // 释放资源的顺序和使用的顺序相反
        resultSet.close();
        pstm.close();
        connection.close();
    }
}
