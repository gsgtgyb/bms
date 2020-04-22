package com.fengshen.controller;

import com.fengshen.pojo.User;
import com.fengshen.service.UserService;
import com.fengshen.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registServlet", urlPatterns = "/registServlet")

public class RegistServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码集，防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取用户提交的表单数据，并封装到Users中
        User u=new User();
       //  u.setUserId(request.getParameter("userId"));
        u.setUserName(request.getParameter("username"));
        u.setUserPasswd(request.getParameter("pass"));
        u.setDefrayPasswd(request.getParameter("dpass"));
        u.setEmail(request.getParameter("email"));
        // u.setIdCode(request.getParameter("idCode"));
        u.setLocation(request.getParameter("location"));
        u.setPhone(request.getParameter("phone"));
       //  u.setBalance(request.getParameter("balance"));
        //调用业务逻辑
        UserService us=new UserServiceImpl();

        User rs=us.findUserByphone(u);
        //根据手机号做判断，如果rs中有值不等于null，说明手机号重复
        //（这种判断不建议，因为要实现前后台分离，在前台登录页面应该做出相关的唯一性校验，然后后台直接实现提交过来的数据进行添加操作）
        if(rs!=null){
            //给出提示(前端jsp页面中添加EL表达式${msg}显示提示信息)，然后分发转向到用户注册页面
            request.setAttribute("msg","手机号已经存在!");
            request.getRequestDispatcher("/admin-add.jsp").forward(request,response);
        }else{
            //反则手机号不重复，执行添加操作
            us.addUsers(u);
            //打印提示，然后分发转向到用户登录页面
            response.getWriter().write("注册开户成功，将返回登录页面");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
