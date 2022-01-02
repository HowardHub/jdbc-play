package com.ln.jdbc.transaction;

import com.ln.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description JDBC事务演示
 * 模拟转账
 * @Author HeZhipeng
 * @Date 2022/1/1 22:14
 **/
public class TransactionPlay {


    /**
     * 不用事务，模拟转账
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.执行SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("update account set balance = ? where id = ?");
        // 操作1：张三丰的钱减少5000
        preparedStatement.setInt(1, 5000);
        preparedStatement.setString(2, "1");
        preparedStatement.executeUpdate();

        // 模拟异常
        int i = 10/0;

        // 操作2：灭绝师太的钱加5000
        preparedStatement.setInt(1, 15000);
        preparedStatement.setString(2, "2");
        preparedStatement.executeUpdate();

        //3.释放资源
        JDBCUtils.close(null, preparedStatement, connection);

    }


    /**
     * 使用事务
     * @throws Exception
     */
    @Test
    public void test2() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1.获取连接

            connection = JDBCUtils.getConnection();
            // 事务的使用步骤1：开启事务
            connection.setAutoCommit(false);

            //2.事务的使用步骤2：编写SQL并执行
            preparedStatement = connection.prepareStatement("update account set balance = ? where id = ?");
            // 操作1：张三丰的钱减少5000
            preparedStatement.setInt(1, 5000);
            preparedStatement.setString(2, "1");
            preparedStatement.executeUpdate();

            // 模拟异常
            int i = 10/0;

            // 操作2：灭绝师太的钱加5000
            preparedStatement.setInt(1, 15000);
            preparedStatement.setString(2, "2");
            preparedStatement.executeUpdate();
            // 事务的使用步骤3：提交事务
            connection.commit();
        }catch (SQLException e) {
            // 事务的使用步骤3：回滚事务
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            //3.释放资源
            JDBCUtils.close(null, preparedStatement, connection);
        }

    }

}
