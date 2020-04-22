package com.fengshen.service;

import com.fengshen.dao.WithdrawDao;
import com.fengshen.dao.WithdrawDaoImpl;
import com.fengshen.pojo.User;

/**
 * @Auther: gsgtgyb
 * @Date: 2020/4/21 16:29
 * @Description:
 */
public class CashWithdrawalServiceImpl implements CashWithdrawalService {
    WithdrawDao withdrawDao=new WithdrawDaoImpl();

    public boolean matchingBankCode(String userId,String inputBankCode){
        return withdrawDao.matchingBankCode(userId, inputBankCode);
    }
    public boolean checkBindBank(String userId) {
        return withdrawDao.checkBindBank(userId);
    }

    public boolean checkDefrayPasswd(String userId, String inputPwd) {
        return withdrawDao.checkDefrayPasswd(userId, inputPwd);
    }

    public boolean checkMoney(String userId, float num) {
        return withdrawDao.checkMoney(userId, num);
    }

    public boolean CashWithDraw(User user, float num, String BankCode) {
        return withdrawDao.CashWithDraw(user, num, BankCode);
    }
}
