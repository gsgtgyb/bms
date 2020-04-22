package com.fengshen.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DButil {

    private static Connection connection;
    private static ServletContext application;
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            //获取Properties对象
            Properties props = new Properties();
            InputStream inputStream = DButil.class.getClassLoader().getResourceAsStream("db.properties");
            // 加载输入流
            props.load(inputStream);
            // 获取相关参数的值
            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            System.out.println(driver);
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static int excuteUpdate(String sql, Object... params) {
        try {
            //从连接对象得到陈述对象
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            //循环设置参数
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            //执行并返回结果
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ResultSet excuteQuery(String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void contextInitialized(ServletContextEvent sce) {
        System.out.println("DBUtil初始化");
        application = sce.getServletContext();
    }

    public static boolean closerResource(Connection connection,PreparedStatement pstm,ResultSet rs){
        boolean flag=false;
        if(rs!=null){
            try {
                rs.close();
                rs=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(pstm!=null){
            try {
                pstm.close();
                pstm=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        if(connection!=null){
            try {
                connection.close();
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }

    /**
     * 执行更新操作
     */
    public static int excuteUpdate(PreparedStatement pstmt, Object[] params) throws SQLException {
        int result = -1;
        //占位符赋值
        for (int i = 0; i < params.length; i++) {
            if(params[i] instanceof Float){
                pstmt.setFloat(i+1, (Float) params[i]);
            }else {
                pstmt.setObject(i + 1, params[i]);
            }

        }
        //执行并返回结果
        result = pstmt.executeUpdate();
        return result;
    }

    /**
     *执行查询的操作
     */
    public static ResultSet excuteQuery(PreparedStatement pstmt, Object[] params) throws SQLException {
        ResultSet set = null;
        //占位符赋值
        for (int i = 0; i < params.length; i++) {
            if(params[i] instanceof Float){
                pstmt.setFloat(i+1, (Float) params[i]);
            }else {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        //执行查询语句并返回结果集
        set = pstmt.executeQuery();
        return set;
    }

    /**
     * 释放Connection对象
     */
    public static void releaseCon(Connection con) {
        if(con != null) {
            try {
                con.close();
                con = null;
            }catch(SQLException e) {
                System.out.println("Connection对象释放异常！");
            }
        }
    }
    /**
     * 释放ResultSet对象
     */
    public static void releaseSet(ResultSet set) {
        if(set != null) {
            try {
                set.close();
                set = null;
            }catch(SQLException e) {
                System.out.println("Resultset对象释放异常！");
            }
        }
    }
    /**
     * 释放PreparedStatement对象
     */
    public static void releasePstmt(PreparedStatement pstmt) {
        if(pstmt != null) {
            try {
                pstmt.close();
                pstmt = null;
            }catch(SQLException e) {
                System.out.println("PreparedStatement对象释放异常！");
            }
        }
    }
}
