package com.fengshen.controller;

import com.fengshen.pojo.Bank;
import com.fengshen.service.BindBankCard;
import com.fengshen.service.BindBankCardImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BindBankCardServlet",urlPatterns="/BindBankCardServlet")
public class BindBankCardServlet extends HttpServlet {
    BindBankCard bindBankCard = new BindBankCardImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String bankCode = request.getParameter("bankCode");//前端输入的银行卡号
        //String userId = session.getAttribute("user").toString();//从会话中获取用户名
        String userId = "xiaowang";
        String bankPasswd = request.getParameter("bankPasswd");//前端输入的银行卡密码
        if(bindBankCard.quaryBankByUserAndCode(userId,bankCode) == null){
            //绑定银行卡
            Bank bank = new Bank(null,userId,bankCode,5000f,bankPasswd);//假定银行卡余额为5000元
            bindBankCard.bindBankCard(bank);
            request.setAttribute("msg","绑定银行卡成功");
            //重新请求转发至充值页面
            request.getRequestDispatcher("/recharge.jsp").forward(request,response);
        }else {
            request.setAttribute("msg","该银行卡已被绑定，请您换一张银行卡");
            //重新请求转发至充值页面
            request.getRequestDispatcher("/bank.jsp").forward(request,response);
        }
    }
}

