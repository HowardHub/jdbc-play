package com.ln.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description JDBC-Druid工具类
 * @Author HeZhipeng
 * @Date 2022/1/2 1:39
 **/
public class JDBCByDruidUtils {

    private static  DataSource ds;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/druid.properties"));
            //properties.list(System.out);
            //1.创建一个指定参数的数据库连接池
            ds = DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * 获取连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(){
        try {
            return ds.getConnection();
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
