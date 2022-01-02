package com.ln.jdbc.blob;

import com.ln.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * @Description TODO
 * @Author HeZhipeng
 * @Date 2022/1/2 0:32
 **/
public class BlobPlay {


    /**
     * 存图片
     */
    @Test
    public void testSave() throws SQLException, FileNotFoundException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.执行插入
        PreparedStatement statement = connection.prepareStatement("update user set photo = ? where id = ?");
        statement.setBlob(1, new FileInputStream("src/main/resources/liuyan.png"));
        statement.setInt(2, 1);
        statement.executeUpdate();
        JDBCUtils.close(null, statement, connection);
    }


    /**
     * 读取blob文件
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void testGet() throws SQLException, IOException {
        //1.获取连接
        Connection connection = JDBCUtils.getConnection();
        //2.执行插入
        PreparedStatement statement = connection.prepareStatement("select photo from user where id = ?");
        statement.setInt(1, 1);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Blob blob = resultSet.getBlob(1);
            InputStream inputStream = blob.getBinaryStream();
            FileOutputStream fos = new FileOutputStream("src/main/resources/liuyan_copy.png");
            int len;
            byte[] b = new byte[1024];
            while((len = inputStream.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.close();
            inputStream.close();
        }
        JDBCUtils.close(null, statement, connection);
    }


}
