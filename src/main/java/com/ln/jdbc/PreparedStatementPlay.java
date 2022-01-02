package com.ln.jdbc;

import com.ln.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @Description 演示PreparedStatement和Statement的区别
 * 登录验证
 * @Author HeZhipeng
 * @Date 2022/1/1 17:15
 **/
public class PreparedStatementPlay {


    // 使用Statement有防止SQL注入的风险
    @Test
    public void test01() throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("输入用户名");
        String username = input.next();
        System.out.println("输入密码");
        String pwd = input.next();

        //---------------以下为连接数据库的步骤---------------
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
        String sql = "select count(*) from user where name='%s' and password='%s'";
        String formatSQL = String.format(sql, username, pwd);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(formatSQL);

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            System.out.println(count > 0 ? "login success" : "login failed");
        }

        // 4.关闭连接
        resultSet.close();
        statement.close();
        connection.close();

    }


    /**
     * 使用PreparedStatement的好处
     * 1.不再使用+拼接SQL语句，减少语法错误，语义性强
     * 2.将模板sql和参数部分进行了分离，提高维护性
     * 3.有效解决了sql注入问题
     * 4.大大减少了编译次数，效率较高
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("输入用户名");
        String username = input.next();
        System.out.println("输入密码");
        String pwd = input.next();

        //---------------以下为连接数据库的步骤---------------
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

        //3.1编写SQL，问号表示占位符
        String sql = "select count(*) from user where name=? and password=? ";
        //3.2获取PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //3.3设置占位符的值
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, pwd);

        // 3.4执行SQL命令
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            System.out.println(count > 0 ? "login success" : "login failed");
        }

        // 4.关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }


    /**
     * 使用JDBCUtils
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select id, deptno, dname  from  dept where id = 10";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int deptno = resultSet.getInt(2);
                String dname = resultSet.getString(3);
                System.out.println("id: " + id + ", deptno: " + deptno + ", dname: " + dname);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet, statement, connection);
        }

    }


}
