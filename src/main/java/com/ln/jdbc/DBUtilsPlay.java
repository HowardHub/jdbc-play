package com.ln.jdbc;

import com.ln.jdbc.entity.Dept;
import com.ln.jdbc.utils.JDBCByDruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @Description apache common-dbutils的使用演示
 * @Author HeZhipeng
 * @Date 2022/1/2 11:07
 **/
public class DBUtilsPlay {


    /**
     * 测试增删改
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        //1.获取连接
        Connection connection = JDBCByDruidUtils.getConnection();
        //2.执行操作
        QueryRunner qr = new QueryRunner();
        int update = qr.update(connection, "update dept set dname=? where id = 10", "IT PLUS");
        System.out.println(update > 0 ? "success" : "failure");
        //3.关闭连接
        JDBCByDruidUtils.close(null, null, connection);

    }

    /**
     * 测试查询单条记录
     * @throws Exception
     */
    @Test
    public void testQuerySingle() throws Exception {
        //1.获取连接
        Connection connection = JDBCByDruidUtils.getConnection();
        //2.执行操作
        QueryRunner qr = new QueryRunner();
        Dept dept = qr.query(connection, "select * from dept where id = ?", new BeanHandler<>(Dept.class), "10");
        System.out.println(dept);

        //3.关闭连接
        JDBCByDruidUtils.close(null, null, connection);

    }

    /**
     * 测试查询多个
     * @throws Exception
     */
    @Test
    public void testQueryMulti() throws Exception {
        //1.获取连接
        Connection connection = JDBCByDruidUtils.getConnection();
        //2.执行操作
        QueryRunner qr = new QueryRunner();
        List<Dept> depts = qr.query(connection, "select * from dept where id > ?", new BeanListHandler<>(Dept.class), "1");
        System.out.println(depts);

        //3.关闭连接
        JDBCByDruidUtils.close(null, null, connection);

    }


    /**
     * 测试查询单个值
     * @throws Exception
     */
    @Test
    public void testScalar() throws Exception {
        //1.获取连接
        Connection connection = JDBCByDruidUtils.getConnection();
        //2.执行操作
        QueryRunner qr = new QueryRunner();
        Long count = qr.query(connection, "select count(*) from dept", new ScalarHandler<>());
        System.out.println(count);
        //3.关闭连接
        JDBCByDruidUtils.close(null, null, connection);

    }

}
