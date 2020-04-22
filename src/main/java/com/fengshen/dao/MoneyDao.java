package com.fengshen.dao;

import com.fengshen.pojo.Money;
import com.fengshen.pojo.MoneyVO;

import java.util.List;

public interface MoneyDao {
    //增加流水
    int addMoney(Money Money);

    //删除流水
    void delMoney(int moneyId);

    //修改流水
    void upMoney(Money Money);

    //查询一个用
    void quaryMoneyById(int moneyId);

    //查询全部流水
    void quaryAllMoney();

    //查询流水
    List<MoneyVO> quaryMoney(String userId, String startDate, String endDate, String selectComment, String cusName, float minAmount, float maxAmount);

    //查询转账流水
    List<MoneyVO> quaryTransferAccounts(String userId, String startDate, String endDate, String selectComment, String cusName, float minAmount, float maxAmount);

    //查询充值提现流水
    List<MoneyVO> quaryBank(String userId, String startDate, String endDate, String selectComment, String cusName, float minAmount, float maxAmount);
}