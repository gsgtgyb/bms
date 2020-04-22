package com.fengshen.dao;

import com.fengshen.pojo.Money;
import com.fengshen.pojo.MoneyVO;
import com.fengshen.util.DButil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoneyDaoImpl implements MoneyDao {
    public int addMoney(Money money) {
        String sql = "INSERT INTO money(moneyId,userId,moneyDate,moneyManipulate,moneyComment,inUserID,outUserID,ence) VALUES(?,?,?,?,?,?,?,?)";
        Object[] params = {money.getMoneyId(),money.getUserId(),money.getMoneyDate(),money.getMoneyManipulate(),money.getMoneyComment(),money.getInUserID(),money.getOutUserID(),money.isEnce()};
        int rs = DButil.excuteUpdate(sql,params);
        return rs;
    }

    public void delMoney(int moneyId) {

    }

    public void upMoney(Money Money) {

    }

    public void quaryMoneyById(int moneyId) {

    }

    public void quaryAllMoney() {

    }

    //查询转账流水
    public List<MoneyVO> quaryTransferAccounts(String userId,String startDate,String endDate,String selectComment,String cusName,float minAmount,float maxAmount){
        Connection con = null;
        java.sql.ResultSet set = null;
        List<MoneyVO> l = new ArrayList<MoneyVO>();
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //sql语句
            String sql="select m.moneyId,m.moneyDate,m.moneyManipulate,moneyComment,u.userName from money m,user u where m.userId=?  and m.moneyDate>=? and m.moneyDate<=? " ;
            //参数列表
            //Object[] params = new Object[]{recUserId,recUserName};
            //通过DbUtil工具类获得查询结果集
            //set = DButil.excuteQuery(sql,params);

            sql+="and ABS(m.moneyManipulate)>=? ";
            sql+="and ABS(m.moneyManipulate)<=? ";
            sql=sql+"and m.moneyComment=? ";

            if(!"null".equals(cusName))
                sql+="and (m.inUserId=? or m.outUserId=?) " ;
            sql+="and (u.userId=m.inUserId or u.userId=m.outUserId) ";
            Object[] params=new Object[]{userId,startDate,endDate,minAmount,maxAmount,selectComment,cusName,cusName};
            set=DButil.excuteQuery(sql,params);

            while(set.next()) {
                System.out.println(set.getString(1));
                System.out.println(set.getString(2));
                System.out.println(set.getString(3));
                System.out.println(set.getString(4));
                System.out.println(set.getString(5));
                MoneyVO mvo=new MoneyVO(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),true);
                l.add(mvo);
            }
        } catch (SQLException e) {
            System.out.println("数据库连接异常："+e.getMessage());
        }finally {
            try {
                //释放set对象
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                //释放con对象
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }
    //查询充值提现流水
    public List<MoneyVO> quaryBank(String userId,String startDate,String endDate,String selectComment,String cusName,float minAmount,float maxAmount){
        Connection con = null;
        java.sql.ResultSet set = null;
        List<MoneyVO> l=new ArrayList<MoneyVO>();
        try {
            //获得数据库连接对象
            con = DButil.getConnection();
            //sql语句
            String sql;

            if("充值".equals(selectComment))
                sql="select m.moneyId,m.moneyDate,m.moneyManipulate,moneyComment,m.inUserId as card from money m where m.userId=?  and m.moneyDate>=? and m.moneyDate<=? " ;
            else
                sql="select m.moneyId,m.moneyDate,m.moneyManipulate,moneyComment,m.outUserId as card from money m where m.userId=?  and m.moneyDate>=? and m.moneyDate<=? " ;
            sql+="and ABS(m.moneyManipulate)>=? ";
            sql+="and ABS(m.moneyManipulate)<=? ";
            sql=sql+"and m.moneyComment=? ";
            Object[] params=new Object[]{userId,startDate,endDate,minAmount,maxAmount,selectComment};
            set=DButil.excuteQuery(sql,params);
            while(set.next()) {
                System.out.println(set.getString(1));
                System.out.println(set.getString(2));
                System.out.println(set.getString(3));
                System.out.println(set.getString(4));
                System.out.println(set.getString(5));
                MoneyVO mvo=new MoneyVO(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),true);
                l.add(mvo);
            }

        } catch (SQLException e) {
            System.out.println("数据库连接异常："+e.getMessage());
        }finally {
            try {
                //释放set对象
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                //释放con对象
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return l;
    }
    public List<MoneyVO> quaryMoney(String userId,String startDate,String endDate,String selectComment,String cusName,float minAmount,float maxAmount) {
        Connection con = null;
        java.sql.ResultSet set = null;
        List<MoneyVO> l=new ArrayList<MoneyVO>();

        if("转入".equals(selectComment)||"转出".equals(selectComment)){
            l.addAll(new MoneyDaoImpl().quaryTransferAccounts(userId, startDate, endDate, selectComment, cusName, minAmount, maxAmount));
        }else if("所有交易类型".equals(selectComment)){
            l.addAll(new MoneyDaoImpl().quaryBank(userId, startDate, endDate, selectComment, cusName, minAmount, maxAmount));
            l.addAll(new MoneyDaoImpl().quaryTransferAccounts(userId, startDate, endDate, selectComment, cusName, minAmount, maxAmount));
        }else{
            l.addAll(new MoneyDaoImpl().quaryBank(userId, startDate, endDate, selectComment, cusName, minAmount, maxAmount));
        }

        return l;
    }

}
