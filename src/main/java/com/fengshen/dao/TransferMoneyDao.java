package com.fengshen.dao;

public interface TransferMoneyDao {
    //检查转账对象用户
    public boolean checkRecUser(String recUserId, String recUserName);
    //检查支付密码
    public boolean checkDefrayPasswd(String userID, String inputPwd);
    //检查余额是否充足
    public boolean checkBalance(String userId, Float tradeMoney);
    //执行转账
    public void transferMoney(String userId, String recUserId, Float tradeMoney);
    //记录转账的流水
    public void recordBill(String userId, String recUserId, Float tradeMoney, Integer ence);
}
