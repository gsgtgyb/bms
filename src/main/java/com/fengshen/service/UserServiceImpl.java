package com.fengshen.service;

import com.fengshen.dao.UserDao;
import com.fengshen.dao.UserDaoImpl;
import com.fengshen.pojo.User;

public class UserServiceImpl implements UserService {

   UserDao ud=new UserDaoImpl();
   //调用userDaoImpl中相应的实现方法
    public void addUsers(User user) {
        ud.addUsers(user);
    }

    public User findUserByphone(User user) {
        return ud.findUserByphone(user);
    }

    public User findUserByphoneAnduserPasswd(User user) {
        return ud.findUserByphoneAnduserPasswd(user);
    }

    public User findUserByuserName(User user) {

        return ud.findUserByuserName(user);
    }

    public User findUserByuserIdAnduserPasswd(User user) {

        return ud.findUserByuserIdAnduserPasswd(user);
    }
}
