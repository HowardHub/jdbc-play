package com.ln.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Description 步骤1：查询的演示
 * @Author HeZhipeng
 * @Date 2022/1/1 16:56
 **/
public class JDBC3Play {


    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties info = new Properties();
        info.load(new FileInputStream("src/main/resources/jdbc.properties"));
        //info.list(System.out);
        String user = info.getProperty("user");
        String password = info.getProperty("password");
        String url = info.getProperty("url");
        String driver = info.getProperty("driver");

        //1.注册驱动
        Class.forName(driver);
        //2.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3.执行查询
        String sql = "select id, deptno, dname  from  dept where id = 10";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            int deptno = resultSet.getInt(2);
            String dname = resultSet.getString(3);
            System.out.println("id: " + id + ", deptno: " + deptno + ", dname: " + dname);
        }

        // 关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }


}
