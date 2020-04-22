package com.fengshen.dao;

import com.fengshen.pojo.User;

public interface UserDao {
    //增加用户
    void addUsers(User user);
    //删除用户
    void delUsers(String userId);
    //修改用户
    int upUsers(User user);
    //查询一个用户
    User quaryUsersById(String userId);
    //查询全部用户
    void quaryAllUsers();
    //根据姓名查询(注册时对姓名进行校验)
    User findUserByuserName(User user);
    //根据手机号查询(注册时对手机号进行校验)
    User findUserByphone(User Users);
    //登录时查询用户名和密码
    User findUserByuserIdAnduserPasswd(User user);
    //登录时查询用户名和密码
    User findUserByphoneAnduserPasswd(User user);

}
