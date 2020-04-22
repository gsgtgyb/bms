package com.fengshen.dao;

import com.fengshen.pojo.Bank;
import com.fengshen.util.DButil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankDaoImpl implements BankDao {

    public int addbank(Bank bank) {
        Connection conn =null;
        int rs =-1;
        try {
            conn=DButil.getConnection();
            String sql = "insert into bank(bankId,userId,bankCode,bankMoney,bankPasswd) VALUES(?,?,?,?,?)";
            Object[] params = {bank.getBankId(), bank.getUserId(), bank.getBankCode(), bank.getBankMoney(), bank.getBankPasswd()};
            rs = DButil.excuteUpdate(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(conn,null,null);
        }
        return rs;
    }

    public int delbank(int bankId) {
        Connection conn = null;
        int i = -1;
        try {
            conn = DButil.getConnection();
            String sql = "delete from bank where bankId=?";
            Object param = bankId;
            i = DButil.excuteUpdate(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DButil.closerResource(conn,null,null);
        }
        return i;
    }

    public int upbank(Bank bank) {
        Connection connection = null;
        int i = -1;
        try {
            connection = DButil.getConnection();
            connection.setAutoCommit(false);
            String sql = "update bank set userid=?,bankCode=?,bankMoney=?,bankPasswd=? where bankId=?";
            Object[] params = {bank.getUserId(), bank.getBankCode(), bank.getBankMoney(), bank.getBankPasswd(), bank.getBankId()};
            i = DButil.excuteUpdate(sql, params);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            DButil.closerResource(connection,null,null);
        }
        return i;
    }

    public List querybankByUser(String userId) {
        String sql = "select * from bank where userId=?";
        ResultSet rs = DButil.excuteQuery(sql,userId);
        List<Bank> banks = new ArrayList<Bank>();
        try {
            while (rs.next()) {
                banks.add(new Bank(
                                rs.getInt("bankId"),
                                rs.getString("userId"),
                                rs.getString("bankCode"),
                                rs.getFloat("bankMoney"),
                                rs.getString("bankPasswd")
                        )
                );
            }
            return banks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bank quarybankById(int BankId) {
        String sql = "select * from bank where bankId=?";
        ResultSet rs = DButil.excuteQuery(sql,BankId);
        Bank bank = null;
        try {
            while (rs.next()){
                bank = new Bank(rs.getInt("bankId"),
                        rs.getString("userId"),
                        rs.getString("bankCode"),
                        rs.getFloat("bankMoney"),
                        rs.getString("bankPasswd"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }

    public List quaryAllbank() {
        String sql = "select * from bank";
        ResultSet rs = DButil.excuteQuery(sql);
        List<Bank> banks = new ArrayList<Bank>();
        try {
            while (rs.next()) {
                banks.add(new Bank(
                                rs.getInt("bankId"),
                                rs.getString("userId"),
                                rs.getString("bankCode"),
                                rs.getFloat("bankMoney"),
                                rs.getString("bankPasswd")
                        )
                );
            }
            return banks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bank quaryBankByUserAndCode(String userId, String bankCode) {
        String sql="select * from bank where userId=? and bankCode=?";
        ResultSet rs = DButil.excuteQuery(sql,userId,bankCode);
        Bank bank = null;
        try {
            while (rs.next()){
                bank = new Bank(rs.getInt("bankId"),
                        rs.getString("userId"),
                        rs.getString("bankCode"),
                        rs.getFloat("bankMoney"),
                        rs.getString("bankPasswd"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }
}
