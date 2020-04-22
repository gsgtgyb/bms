package com.fengshen.service;

import com.fengshen.dao.TransferMoneyDao;
import com.fengshen.dao.TransferMoneyDaoImpl;

public class TransferMoneyServiceImpl implements TransferMoneyService {
    TransferMoneyDao transferMoneyDao = new TransferMoneyDaoImpl();

    public boolean checkRecUser(String recUserId, String recUserName) {
        return transferMoneyDao.checkRecUser(recUserId,recUserName);
    }

    public boolean checkDefrayPasswd(String userId, String inputPwd) {
        return transferMoneyDao.checkDefrayPasswd(userId,inputPwd);
    }

    public boolean checkMoney(String userId, float tradeMoney) {
        return transferMoneyDao.checkBalance(userId,tradeMoney);
    }

    public void transferMoney(String userId, String recUserId, float tradeMoney) {
        transferMoneyDao.transferMoney(userId,recUserId,tradeMoney);
    }
}
