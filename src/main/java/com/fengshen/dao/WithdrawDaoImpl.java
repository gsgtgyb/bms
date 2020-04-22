package com.fengshen.dao;

import com.fengshen.pojo.User;
import com.fengshen.util.DButil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WithdrawDaoImpl implements WithdrawDao {
    public boolean matchingBankCode(String userId, String inputBankCode) {
        boolean flag=false;
        Connection connection=null;
        ResultSet rs=null;
        try {
            connection= DButil.getConnection();
            String sql="select * from bank where userId=?";
            Object[] params={userId};
            rs= DButil.excuteQuery(sql,params);
            if(rs.next()){
                if(inputBankCode.equals(rs.getString("bankCode"))){
                    flag=true;
                }else {
                    System.out.println("卡号错误");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(connection,null,rs);
        }
        return flag;
    }

    public boolean checkBindBank(String userId) {
        boolean flag=false;
        Connection connection=null;
        ResultSet rs=null;
        try {
            connection= DButil.getConnection();
            String sql="select * from bank where userId=?";
            Object[] params={userId};
            rs= DButil.excuteQuery(sql,params);
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(connection,null,rs);
        }
        return flag;
    }

    public boolean checkDefrayPasswd(String userId, String inputPwd) {
        boolean flag=false;
        Connection connection=null;
        ResultSet rs=null;
        try {
            connection= DButil.getConnection();
            String sql="select * from user where userId=?";
            Object[] params={userId};
            rs= DButil.excuteQuery(sql,params);
            if(rs.next()){
                String pwd=rs.getString("defrayPasswd");
                if(pwd.equals(inputPwd)){
                    flag=true;
                }else {
                    System.out.println("WithdrawDaoImpl:"+"支付密码错误");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(connection,null,rs);
        }
        return flag;
    }

    public boolean checkMoney(String userId, float num) {
        boolean flag=false;
        Connection connection=null;
        ResultSet rs=null;
        try {
            connection= DButil.getConnection();
            String sql="select * from user where userId=?";
            Object[] params={userId};
            rs= DButil.excuteQuery(sql,params);
            if(rs.next()){
                float money=rs.getFloat("balance");
                if(money-num>=0){
                    flag= true;
                }else {
                    System.out.println("余额不足!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(connection,null,rs);
        }
        return flag;
    }

    //提现
    public boolean CashWithDraw(User user, float num, String BankCode){
        boolean flag=false;
        Connection connection=null;
        try {
            connection= DButil.getConnection();
            connection.setAutoCommit(false);
            String usersql="update USER set balance=balance-? where userId=?";
            Object[] userParams={num,user.getUserId()};
            DButil.excuteUpdate(usersql,userParams);
            String banksql="update bank set bankmoney=bankmoney+? where bankCode=?";
            Object[] bankParams={num,BankCode};
            DButil.excuteUpdate(banksql,bankParams);
            connection.commit();
            flag=true;
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
        return flag;
    }

}
