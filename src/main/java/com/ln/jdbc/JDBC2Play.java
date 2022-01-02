package com.ln.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description 步骤2：获取连接的演示
 *  获取连接有三种写法，建议使用配置文件的方法
 * @Author HeZhipeng
 * @Date 2022/1/1 15:28
 **/
public class JDBC2Play {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Properties info = new Properties();
        info.load(new FileInputStream("src/main/resources/jdbc.properties"));
        //info.list(System.out);
        String user = info.getProperty("user");
        String password = info.getProperty("password");
        String url = info.getProperty("url");
        String driver = info.getProperty("driver");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("连接成功");


    } 

}

