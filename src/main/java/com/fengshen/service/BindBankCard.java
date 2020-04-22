package com.fengshen.service;

import com.fengshen.pojo.Bank;

import java.util.List;

/**
 * 绑定银行卡
 */
public interface BindBankCard {
    //通过用户名检查该用户是否已经绑定银行卡
    boolean isBindBankCard(String userId);
    //绑定银行卡
    int bindBankCard(Bank bank);
    //查找当前用户绑定的所有银行卡
    List quareBankCardByUser(String userId);
    //通过银行卡id查找对应的银行卡
    Bank quarybankById(int bankId);
    //更改银行卡信息（可应用于更改银行卡余额）
    int upbank(Bank bank);
    //通过用户名和银行卡号查找对应的银行卡
    Bank quaryBankByUserAndCode(String userId, String bankCode);
}
