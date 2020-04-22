package com.fengshen.dao;

import com.fengshen.pojo.User;
import com.fengshen.util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    public void addUsers(User User) {
        //获取数据库的连接(这里应用了jdk7以上版本的特性，会自动关闭资源，不用手动关闭!)
        Connection conn = null;
        PreparedStatement ps = null;

//        DButil db = new DButil();
        try {
            conn = DButil.getConnection();

            String sql =
                    "insert into user(userId, userName , userPasswd, defrayPasswd, email, idCode, location , phone, balance) values (?,?,?,?,?,?,?,?,?)";

            ps.setString(1, User.getUserId());
            ps.setString(2, User.getUserName());
            ps.setString(3, User.getUserPasswd());
            ps.setString(4, User.getDefrayPasswd());
            ps.setString(5, User.getEmail());
            ps.setString(6, User.getIdCode());
            ps.setString(7, User.getLocation());
            ps.setString(8, User.getPhone());
            ps.setFloat(9, User.getBalance());

            ps = conn.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delUsers(String userId) {

    }

    public int upUsers(User user) {

        Connection connection=null;
        int rs =-1;
        try {
            connection=DButil.getConnection();
            connection.setAutoCommit(false);
            String sql = "update user set userName=?,userPasswd=?,defrayPasswd=?,email=?,idCode=?,location=?,phone=?,balance=? where userId=?";
            Object[] params = {user.getUserName(), user.getUserPasswd(), user.getDefrayPasswd(), user.getEmail(), user.getIdCode(), user.getLocation(), user.getPhone(), user.getBalance(), user.getUserId()};
            rs = DButil.excuteUpdate(sql,params);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DButil.closerResource(connection,null,null);
        }
        return rs;
    }

    public User quaryUsersById(String userId) {
        String sql="select * from USER where userId=?";
        ResultSet rs = DButil.excuteQuery(sql,userId);
        User user = null;
        try {
            while (rs.next()){
                user = new User(rs.getString("userId"),
                        rs.getString("userId"),
                        rs.getString("userPasswd"),
                        rs.getString("defrayPasswd"),
                        rs.getString("email"),
                        rs.getString("idCode"),
                        rs.getString("location"),
                        rs.getString("phone"),
                        rs.getFloat("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void quaryAllUsers() {

    }

    public User findUserByuserName(User User) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//        DButil db = new DButil();
        User u = null;
        try {
            DButil.getConnection();
            String sql = "select * from user where userName=?";
            ps.setString(1, User.getUserName());
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            //将查询出的结果数据封装到Users对象中
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getString("userId"));
                u.setUserName(rs.getString("userName"));
                u.setUserPasswd(rs.getString("userPasswd"));
                u.setDefrayPasswd(rs.getString("defrayPasswd"));
                u.setEmail(rs.getString("email"));
                u.setIdCode(rs.getString("idCode"));
                u.setLocation(rs.getString("location"));
                u.setPhone(rs.getString("phone"));
                u.setBalance(Float.parseFloat(rs.getString("balance")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User findUserByphone(User Users) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User u = null;
        try {
            DButil.getConnection();
            String sql = "select * from user where phone=?";
            ps.setString(1,Users.getPhone());
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            //将查询出的结果数据封装到Users对象中,然后返回
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getString("userId"));
                u.setUserName(rs.getString("userName"));
                u.setUserPasswd(rs.getString("userPasswd"));
                u.setDefrayPasswd(rs.getString("defrayPasswd"));
                u.setEmail(rs.getString("email"));
                u.setIdCode(rs.getString("idCode"));
                u.setLocation(rs.getString("location"));
                u.setPhone(rs.getString("phone"));
                u.setBalance(rs.getFloat("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User findUserByuserIdAnduserPasswd(User User) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

//        DButil db = new DButil();
        User u = null;
        try {
            DButil.getConnection();
            String sql = "select * from user where userId=? and userPasswd=?";
            ps.setString(1, User.getUserId());
            ps.setString(2, User.getUserPasswd());
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            //将查询出的结果数据封装到Users对象中
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getString("userId"));
                u.setUserName(rs.getString("userName"));
                u.setUserPasswd(rs.getString("userPasswd"));
                u.setDefrayPasswd(rs.getString("defrayPasswd"));
                u.setEmail(rs.getString("email"));
                u.setIdCode(rs.getString("idCode"));
                u.setLocation(rs.getString("location"));
                u.setPhone(rs.getString("phone"));
                u.setBalance(rs.getFloat("balance"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public User findUserByphoneAnduserPasswd(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User u = null;
        try {
            conn= DButil.getConnection();
            String sql = "select * from user where phone=? and userPasswd=?";
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getUserPasswd());
            ps = conn.prepareStatement(sql);

            rs=ps.executeQuery(sql);
            //将查询出的结果数据封装到Users对象中,然后返回
            if (rs.next()) {
                u = new User();
                u.setUserId(rs.getString("userId"));
                u.setUserName(rs.getString("userName"));
                u.setUserPasswd(rs.getString("userPasswd"));
                u.setDefrayPasswd(rs.getString("defrayPasswd"));
                u.setEmail(rs.getString("email"));
                u.setIdCode(rs.getString("idCode"));
                u.setLocation(rs.getString("location"));
                u.setPhone(rs.getString("phone"));
                u.setBalance(Float.parseFloat(rs.getString("balance")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
