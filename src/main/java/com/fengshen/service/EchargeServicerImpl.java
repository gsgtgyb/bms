package com.fengshen.service;

import com.fengshen.dao.MoneyDao;
import com.fengshen.dao.MoneyDaoImpl;
import com.fengshen.dao.UserDao;
import com.fengshen.dao.UserDaoImpl;
import com.fengshen.pojo.Money;
import com.fengshen.pojo.User;

public class EchargeServicerImpl implements EchargeServicer {
    UserDao userDao = new UserDaoImpl();
    MoneyDao moneyDao = new MoneyDaoImpl();
    public User quaryUsersById(String userId) {
        return userDao.quaryUsersById(userId);
    }

    public int upUsers(User user) {
        return userDao.upUsers(user);
    }

    public int addMoney(Money money) {
        return moneyDao.addMoney(money);
    }
}
