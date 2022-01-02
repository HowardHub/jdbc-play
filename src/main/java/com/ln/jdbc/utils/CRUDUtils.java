package com.ln.jdbc.utils;

import com.ln.jdbc.entity.Dept;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用于封装通用的增删改查方法
 * 功能
 * 1.执行增删改
 * 2.执行查询
 * @Author HeZhipeng
 * @Date 2022/1/2 10:09
 **/
public class CRUDUtils {


    /**
     * 功能：增删改
     * 针对于任何表的任何增删改语句
     *
     * @return
     */
    public static int update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.获取连接
            connection = JDBCByDruidUtils.getConnection();

            //2.执行sql语句
            statement = connection.prepareStatement(sql);
            // 设置占位符的值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            int i = statement.executeUpdate();
            return i;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCByDruidUtils.close(null, statement, connection);
        }

    }


    @Test
    public void testUpdate() {
//        String sql = "update dept set dname=? where id = ?";
//        int hr = update(sql, "HR",11);
//        System.out.println(hr > 0 ? "update success," : "update failure");

        int update = update("delete from dept where id = ?", 5);
        System.out.println(update > 0 ? "delete success" : "delete failure");

    }


    /**
     * 查询一条记录
     *
     * @param sql
     * @param params
     * @return
     */
    public static Dept selectOne(String sql, Object... params) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCByDruidUtils.getConnection();

            //2.执行sql语句
            statement = connection.prepareStatement(sql);
            // 设置占位符的值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                int deptno = resultSet.getInt(2);
                String dname = resultSet.getString(3);
                String loc = resultSet.getString(4);
                return new Dept(id, deptno, dname, loc);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCByDruidUtils.close(resultSet, statement, connection);
        }

    }

    @Test
    public void testSelectOne() {
        Dept dept = selectOne("select * from dept where id = ?", 10);
        System.out.println(dept);
    }


    public static List<Dept> list(String sql, Object... params) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接
            connection = JDBCByDruidUtils.getConnection();

            //2.执行sql语句
            statement = connection.prepareStatement(sql);
            // 设置占位符的值
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }

            resultSet = statement.executeQuery();
            List<Dept> depts = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                int deptno = resultSet.getInt(2);
                String dname = resultSet.getString(3);
                String loc = resultSet.getString(4);
                depts.add(new Dept(id, deptno, dname, loc));
            }
            return depts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCByDruidUtils.close(resultSet, statement, connection);
        }

    }

    @Test
    public void testList() {
        List<Dept> depts = list("select * from dept where id > ?", 5);
        System.out.println(depts);
    }


}
