package com.fengshen.service;

import com.fengshen.pojo.User;

public interface UserService {
    //用户注册接口
    void addUsers(User user);
    //注册时判断登录名是否存在的查询方法接口
    User findUserByuserName(User user);
    //用户登录时，判断用户名与登录密码是否存在的查询方法接口
    User findUserByuserIdAnduserPasswd(User user);
    //注册时判断手机号是否存在的查询方法接口
    User findUserByphone(User user);
    //用户登录时，判断用户名与登录密码是否存在的查询方法接口
    User findUserByphoneAnduserPasswd(User user);
}
