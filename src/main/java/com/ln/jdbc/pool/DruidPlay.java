package com.ln.jdbc.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Description 德鲁伊连接池的使用
 * @Author HeZhipeng
 * @Date 2022/1/2 1:18
 **/
public class DruidPlay {


    /**
     * 演示获取德鲁伊连接
     * @throws Exception
     */
    @Test
    public void testDruid() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/druid.properties"));
        properties.list(System.out);
        //1.创建一个指定参数的数据库连接池
        DataSource ds = DruidDataSourceFactory.createDataSource(properties);
        //2.从数据库连接池中获取连接对象
        Connection connection = ds.getConnection();
        System.out.println("连接成功");
        //3.关闭连接
        connection.close();
    }


}
