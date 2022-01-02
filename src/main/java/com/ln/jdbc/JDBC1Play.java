package com.ln.jdbc;

import java.sql.SQLException;

/**
 * @Description 步骤1：加载驱动演示
 * 类加载时机
 * 1.new 对象
 *      缺点：属于编译期间加载，如果找不到类，直接报编译错误，依赖性太强。
 *           查看源码发现new Driver()还会导致driver对象创建两次。
 * 2.加载子类
 *      属于运行期加载，降低了类的依赖性。
 *      Driver对象只创建了一遍，效率较高。
 * 3.调用类中的静态成员
 * 4.通过反射
 *
 * @Author HeZhipeng
 * @Date 2022/1/1 14:55
 **/
public class JDBC1Play {



    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // 通过new对象的方式加载，不推荐。
        //DriverManager.registerDriver(new Driver());


        // 通过反射加载，直接走Driver类的static代码块创建driver对象
        Class.forName("com.mysql.jdbc.Driver");










    }




}
