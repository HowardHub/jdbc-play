package com.ln.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * @Description jdbc连接测试
 * @Author HeZhipeng
 * @Date 2022/1/1 14:19
 **/
public class JDBCConnectionPlay {


    public static void main(String[] args) throws SQLException {
        // 1.加载驱动
        DriverManager.registerDriver(new Driver());
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://1.15.72.181:3306/bigData", "6623", "6623");
        System.out.println("连接成功");

        // 3.执行CRUD
        // 3-1编写SQL
//        String sql = "delete from dept where id = 11";
//        String sql = "update  dept set dname='IT' where id = 10";
//        String sql = "INSERT INTO `bigData`.`dept`(`id`, `deptno`, `dname`, `loc`) VALUES (11, 120, 'fdfsdf', 'sdfdf')";
        String sql = "select name, password from user where id = 1 ";
        // 3-2获取执行SQL语句的命令对象
        Statement statement = connection.createStatement();
        // 3-3使用命令对象执行SQL语句
        ResultSet resultSet = statement.executeQuery(sql);
        // 3-4处理执行结果
        while(resultSet.next()) {
            String username = resultSet.getString(1);
            String pwd = resultSet.getString(2);
            System.out.println("username: " + username + ", pwd: " + pwd);
        }
        // 4.关闭连接
        resultSet.close();
        statement.close();
        connection.close();




    }


}
