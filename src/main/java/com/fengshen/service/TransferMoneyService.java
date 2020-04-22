package com.fengshen.service;
//转账
public interface TransferMoneyService {
    public boolean checkRecUser(String recUserId, String recUserName);
    public boolean checkDefrayPasswd(String userId, String inputPwd);
    public boolean checkMoney(String userId, float tradeMoney);
    public void transferMoney(String userId, String recUserId, float tradeMoney);
}
