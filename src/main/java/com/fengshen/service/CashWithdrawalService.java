package com.fengshen.service;

import com.fengshen.pojo.User;

public interface CashWithdrawalService {
    //提现
    public boolean matchingBankCode(String userId, String inputBankCode);
    public boolean checkBindBank(String userId);
    public boolean checkDefrayPasswd(String userId, String inputPwd);
    public boolean checkMoney(String userId, float num);
    public boolean CashWithDraw(User user, float num, String BankCode);
}
