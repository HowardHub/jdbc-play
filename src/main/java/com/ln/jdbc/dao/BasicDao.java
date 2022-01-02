package com.ln.jdbc.dao;

import com.ln.jdbc.utils.JDBCByDruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @Description
 * 优点：使用了泛型
 * 缺点：模板代码比较多，spring对其进行了改造
 * @Author HeZhipeng
 * @Date 2022/1/2 13:02
 **/
public class BasicDao <T> {

    /**
     * 测试增删改
     * @throws Exception
     */
    @Test
    public int update(String sql, Object... param) throws Exception {
        //1.获取连接
        Connection connection = null;
        try {
            connection = JDBCByDruidUtils.getConnection();
            //2.执行操作
            QueryRunner qr = new QueryRunner();
            int update = qr.update(connection, sql, param);
            System.out.println(update > 0 ? "success" : "failure");
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //3.关闭连接
            JDBCByDruidUtils.close(null, null, connection);
        }



    }

    /**
     * 测试查询单条记录
     * @throws Exception
     */
    @Test
    public T selectOne(String sql,Class<T> clazz, Object... param)  {
        //1.获取连接
        Connection connection = null;
        try {
            //1.获取连接
            connection = JDBCByDruidUtils.getConnection();
            //2.执行操作
            QueryRunner qr = new QueryRunner();
            return qr.query(connection, sql, new BeanHandler<>(clazz), param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //3.关闭连接
            JDBCByDruidUtils.close(null, null, connection);
        }


    }

    /**
     * 测试查询多个
     * @throws Exception
     */
    public List<T> selectList(String sql,Class<T> clazz, Object... param) {
        //1.获取连接
        Connection connection = null;
        try {
            connection = JDBCByDruidUtils.getConnection();
            QueryRunner qr = new QueryRunner();
            return qr.query(connection, sql, new BeanListHandler<>(clazz), param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //3.关闭连接
            JDBCByDruidUtils.close(null, null, connection);
        }
    }


    /**
     * 测试查询单个值
     * @throws Exception
     */
    @Test
    public Object scalar(String sql,Class<T> clazz, Object... param)  {

        //1.获取连接
        Connection connection = null;
        try {
            connection = JDBCByDruidUtils.getConnection();
            QueryRunner qr = new QueryRunner();
            return qr.query(connection, sql, new ScalarHandler<>(), param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //3.关闭连接
            JDBCByDruidUtils.close(null, null, connection);
        }

    }

}
