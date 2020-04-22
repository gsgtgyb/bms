package com.fengshen.dao;

import com.fengshen.pojo.Bank;

import java.util.List;

public interface BankDao {
    //增加银行卡
    int addbank(Bank bank);
    //删除银行卡
    int delbank(int bankId);
    //修改银行卡
    int upbank(Bank bank);
    //查询一个银行卡
    Bank quarybankById(int bankId);
    //通过用户id查找银行卡信息；
    List querybankByUser(String userID);
    //查询全部银行卡
    List quaryAllbank();
    //通过用户名和银行卡号查找对应的银行卡
    Bank quaryBankByUserAndCode(String userId, String bankCode);
}
