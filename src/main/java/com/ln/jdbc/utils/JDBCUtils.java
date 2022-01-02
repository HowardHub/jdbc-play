package com.ln.jdbc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Description
 * 1.获取连接的封装
 * 2.释放连接的封装
 * @Author HeZhipeng
 * @Date 2022/1/1 21:49
 **/
public class JDBCUtils {

    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    static {
        try {
            Properties info = new Properties();
            info.load(new FileInputStream("src/main/resources/jdbc.properties"));
            //info.list(System.out);
            user = info.getProperty("user");
            password = info.getProperty("password");
            url = info.getProperty("url");
            driver = info.getProperty("driver");
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取连接
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            throw new RuntimeException(e); // 将编译异常转为运行异常，使得调用方不用try catch
        }
    }


    /**
     * 释放资源
     * @param set
     * @param statement
     * @param connection
     * @throws SQLException
     */
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e); // 将编译异常转为运行异常，使得调用方不用try catch
        }
    }
}
