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

@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码集，防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //创建对象获取前台提交过来的表单数据
        User user=new User();
        user.setPhone(request.getParameter("username"));
        user.setUserPasswd(request.getParameter("password"));
        //调用业务逻辑
        UserService us=new UserServiceImpl();
        User u= us.findUserByphoneAnduserPasswd(user);
        //根据是否登录成功来做分发转向
        if(u!=null){
            //输入了正确的用户名和密码，跳转到相应页面
            //将user数据放到session中，方便以后使用
            //分发转向
            request.getSession().setAttribute("user",u);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else{
            //反则数据库表中没有数据，给出提示(前端jsp页面中添加EL表达式${msg}显示提示信息)，然后分发转向到用户登录页面
            request.setAttribute("msg","用户名或密码错误!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
