package com.fengshen.dao;

import com.fengshen.util.DButil;
import com.fengshen.util.DateUtil;


import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransferMoneyDaoImpl implements TransferMoneyDao {
    /**
     * 检查收款方用户是否存在
     */
    public boolean checkRecUser(String recUserId, String recUserName) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;

        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //sql语句
            String sql = "select * from user where userID=? and userName=?";
            //获得数据库语句执行对象
            pstmt = con.prepareStatement(sql);
            //参数列表
            Object[] params = new Object[]{recUserId,recUserName};
            //通过DbUtil工具类获得查询结果集
            set = DButil.excuteQuery(pstmt,params);
            if(set.next()){
                flag = true;
            }
        } catch (SQLException e) {
            System.out.println("数据库异常："+e.getMessage());
        }finally {
            DButil.releaseSet(set);
            DButil.releasePstmt(pstmt);
            DButil.releaseCon(con);
        }

        return flag;
    }

    /**
     * 检查输入支付密码是否正确
     */
    public boolean checkDefrayPasswd(String userId,String inputPwd) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //sql语句
            String sql = "select defrayPasswd from user where userID=?";
            //获得数据库语句执行对象
            pstmt = con.prepareStatement(sql);
            //参数列表
            Object[] params = new Object[]{userId};
            //获得查询结果
            set = DButil.excuteQuery(pstmt,params);
                if(set.next()){
                    //得到用户支付密码
                    String pwd = set.getString("defrayPasswd");
                    if(pwd.equals(inputPwd)){
                        flag = true;
                    }
                }else{
                    System.out.println("用户支付密码异常");
                }
        } catch (SQLException e) {
            System.out.println("数据库异常："+e.getMessage());
        }finally {
            DButil.releaseSet(set);
            DButil.releasePstmt(pstmt);
            DButil.releaseCon(con);
        }

        return flag;
    }
    /**
     * 检查余额是否充足
     */
    public boolean checkBalance(String userId, Float tradeMoney) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet set = null;
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //sql语句
            String sql = "select balance from user where userID=?";
            //获得数据库语句执行对象
            pstmt = con.prepareStatement(sql);
            //参数列表
            Object[] params = new Object[]{userId};
            //获得查询结果
            set = DButil.excuteQuery(pstmt,params);
            if(set.next()){
                //获得账户余额
                float balance = set.getFloat("balance");
                if(balance-tradeMoney.floatValue()>0){
                    flag = true;
                }
            }else{
                System.out.println("用户余额异常");
            }
        } catch (SQLException e) {
            System.out.println("数据库异常："+e.getMessage());
        }finally {
            DButil.releaseSet(set);
            DButil.releasePstmt(pstmt);
            DButil.releaseCon(con);
        }

        return flag;
    }

    /**
     *转账操作执行
     */
    public void transferMoney(String userId, String recUserId, Float tradeMoney) {
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //开始事务，不自动提交
            con.setAutoCommit(false);
            //付款方余额减少
            String sql = "update user set balance=balance+? where userId=?";
            //获得数据库语句执行对象
            pstmt1 = con.prepareStatement(sql);
            Object[] params = new Object[]{new Float(-tradeMoney.floatValue()),userId};
            DButil.excuteUpdate(pstmt1,params);
//            if (true){
//                throw new RuntimeException("不好意思，转账失败");
//            }
            //收款方余额增加
            String sql2 = "update user set balance=balance+? where userId=?";
            //获得数据库语句执行对象
            pstmt2 = con.prepareStatement(sql);
            Object[] params2 = new Object[]{tradeMoney,recUserId};
            DButil.excuteUpdate(pstmt2,params2);
            //提交事务
            con.commit();
            //转账成功时记录流水
            recordBill(userId,recUserId,tradeMoney,new Integer(1));
        } catch (Exception e) {
            try {
                //数据库回滚
                con.rollback();
                //转账失败时记录流水
                recordBill(userId,recUserId,tradeMoney,new Integer(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException(e);
        }finally {
            DButil.releasePstmt(pstmt2);
            DButil.releasePstmt(pstmt1);
            DButil.releaseCon(con);
        }
    }

    /**
     * 记录转账的流水
     */
    public void recordBill(String userId, String recUserId, Float tradeMoney,Integer ence) {
        Connection con = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //开始事务，不自动提交
            con.setAutoCommit(false);
            //付款方流水记录
            String sql = "insert into money (moneyId,userId,moneyDate,moneyManipulate,moneyComment,outUserId,ence) values(?,?,?,?,?,?,?)";
            //获得数据库语句执行对象
            pstmt1 = con.prepareStatement(sql);
            //获得参数列表
            String moneyId1 = DateUtil.getDateTime()+0003+DateUtil.getNumber();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String moneyDate = df.format(new Date());
            String moneyManipulate1 = "-"+tradeMoney;
            String moneyComment = "转账";
            Object[] params = new Object[]{moneyId1,userId,moneyDate,moneyManipulate1,moneyComment,recUserId,ence};
            DButil.excuteUpdate(pstmt1,params);
            //收款方流水记录
            String sql2 = "insert into money (moneyId,userId,moneyDate,moneyManipulate,moneyComment,inUserId,ence) values(?,?,?,?,?,?,?)";
            //获得数据库语句执行对象
            pstmt2 = con.prepareStatement(sql);
            //参数列表
            String moneyId2 = DateUtil.getDateTime()+0004+DateUtil.getNumber();
            String moneyManipulate2 = ""+tradeMoney;
            Object[] params2 = new Object[]{moneyId2,recUserId,moneyDate,moneyManipulate2,moneyComment,userId,ence};
            DButil.excuteUpdate(pstmt2,params2);
            //提交事务
            con.commit();
        } catch (SQLException e) {
            System.out.println("数据库异常："+e.getMessage());
        }finally {
            DButil.releasePstmt(pstmt2);
            DButil.releasePstmt(pstmt1);
            DButil.releaseCon(con);
        }
    }
//    /**
//     * 修改指定用户的余额
//     */
//    public void updateBalance(Connection con,String userId,double tradeMoney){
//        try {
//            //sql语句
//            String sql = "update user set balance=balance+? where userId=?";
//            //执行数据库语句
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setDouble(1,tradeMoney);
//            pstmt.setString(2,userId);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("数据库语句执行异常："+e.getMessage());
//        }
//
//    }


}
