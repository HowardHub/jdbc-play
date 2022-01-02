package com.ln.jdbc.batchs;

import com.ln.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/1/1 23:38
 **/
public class BatchPlay {


    @Test
    public void testNoBatch() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.执行插入
        PreparedStatement statement = connection.prepareStatement("insert into user values(null, ?, ?)");
        for (int i = 0; i < 50000; i++) {
            statement.setString(1, "mark" + i);
            statement.setString(2, "000000");
            statement.executeUpdate();//执行
        }
        //3.释放资源
        JDBCUtils.close(null, statement, connection);
    }


    /**
     * 使用批处理，很快就能处理完
     * @throws SQLException
     */
    @Test
    public void testBatch() throws SQLException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.执行插入
        PreparedStatement statement = connection.prepareStatement("insert into user values(null, ?, ?)");
        for (int i = 0; i < 50000; i++) {
            statement.setString(1, "mark" + i);
            statement.setString(2, "000000");
            statement.addBatch(); // 添加sql语句到批处理包中
            if (i % 1000 == 0) {
               statement.executeBatch(); // 执行批处理包中的SQL语句
               statement.clearBatch(); // 清空批处理包中的SQL语句
            }
        }
        //3.释放资源
        JDBCUtils.close(null, statement, connection);

    }


}
