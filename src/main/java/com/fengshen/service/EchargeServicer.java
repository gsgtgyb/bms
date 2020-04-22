package com.fengshen.service;

import com.fengshen.pojo.Money;
import com.fengshen.pojo.User;

public interface EchargeServicer {
    //通过用户名查找用户(用于检查用户输入的支付密码是否正确)
    User quaryUsersById(String userId);
    //改变用信息(用于增加用户虚拟账户余额)
    int upUsers(User User);
    //增加用户流水信息
    int addMoney(Money Money);
}
