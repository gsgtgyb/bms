package com.fengshen.service;

import com.fengshen.dao.BankDao;
import com.fengshen.dao.BankDaoImpl;
import com.fengshen.pojo.Bank;

import java.util.List;

public class BindBankCardImpl implements BindBankCard {
    BankDao bankDao = new BankDaoImpl();
    boolean bool = false;
    public boolean isBindBankCard(String userId) {
        if(bankDao.querybankByUser(userId).size() !=0){
            bool=true;
        }
        return bool;
    }

    public int bindBankCard(Bank bank) {
        return bankDao.addbank(bank);
    }

    public List quareBankCardByUser(String userId) {
        return bankDao.querybankByUser(userId);
    }

    public Bank quarybankById(int bankId) {
        return bankDao.quarybankById(bankId);
    }

    public int upbank(Bank bank) {
        return bankDao.upbank(bank);
    }

    public Bank quaryBankByUserAndCode(String userId, String bankCode) {
        return bankDao.quaryBankByUserAndCode(userId,bankCode);
    }
}
