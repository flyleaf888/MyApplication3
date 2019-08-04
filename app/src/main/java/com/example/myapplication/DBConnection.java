package com.example.myapplication;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String driver = "com.mysql.jdbc.Driver";
    //    private static final String url = "jdbc:mysql://192.168.43.173:3306/test?useSSL=true&serverTimezone=GMT";
    private static final String url = "jdbc:mysql://cdb-cx4tzwpu.bj.tencentcdb.com:10013";
    private static final String user = "root";
    private static final String pwd = "czlwcdfly8201";
    static Statement st;
    public static Connection getConn() {
        Connection conn = null;
        try
        {            Class.forName(driver);//获取MYSQL驱动
            conn = (Connection) DriverManager.getConnection(url, user, pwd);//获取连接

        }
        catch (ClassNotFoundException e)
        {            e.printStackTrace();        }
        catch (SQLException e) {            e.printStackTrace();
        }
        return conn;
    }
    public static void qMysql() {
        Connection conn = null;
        // PreparedStatement stmt=null;

        conn = DBConnection.getConn();
        try {
            String sql = "select * from sys.usert";
            st = (Statement) conn.createStatement();
            // PreparedStatement ps = conn1.prepareStateme
            //st.executeQuery(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("bank_no");
                String name = rs.getString("bank_name");
                //String phone=rs.getString("phone");
                System.out.println(id + "\t" + name);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("查询数据失败");
        }

    }
    public static void insertmydb(String bn,String bname) {
        String sql = "insert into sys.usert(bank_no,bank_name) values (?,?);";
        Connection conn = null;
        PreparedStatement pstm=null;
        String bankid="17";
        String bankname="nyyh1";


        conn = DBConnection.getConn();
        try {


            pstm = conn.prepareStatement(sql);
            //通过setString给4个问好赋值，下面的course_id，user_id，course_time，us_job_id都是已有值的变量，不要误会了
            pstm.setString(1,bn);
            pstm.setString(2, bname);
            System.out.println(bname);

            //执行更新数据库
            pstm.executeUpdate();

            //关闭链接
            conn.close();
            //关闭访问
            pstm.close();
        } catch (SQLException e) {
            System.out.println("插入数据失败" + e.getMessage());
        }
    }

    public static void insertdb() {
        Connection conn = null;
        conn = DBConnection.getConn();   // 首先要获取连接，即连接到数据库
        try {
            String sql = "INSERT INTO sys.usert(bank_no,bank_name)"+ " VALUES ('13','gsyh')";    // 插入数据的sql语
            st = (Statement) conn.createStatement();    // 创建用于执行静态sql语句的Statement对象
            int count = st.executeUpdate(sql);    // 执行插入操作的sql语句，并返回插入数据的个
            System.out.println("向tb_user表中插入 " + count + " 条数据");    //输出插入操作的处理结果
            conn.close();    //关闭数据库连接
        } catch (SQLException e) {
            System.out.println("插入数据失败" + e.getMessage());
        }
    }
    public static void linkMysql() {
        Connection conn=null;
        PreparedStatement stmt=null;
        try {
            Class.forName(driver).newInstance();
            System.out.println("驱动加载成功！！！！！");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url,user,pwd);
            System.out.println("连接数据库成功！！！！！！");
            String sql = "select * from sys.usert";
            stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String id=rs.getString("bank_no");
                String name=rs.getString("bank_name");
                //String phone=rs.getString("phone");
                System.out.println(id+"\t"+name);
            }



        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("debug",Log.getStackTraceString(e));
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
