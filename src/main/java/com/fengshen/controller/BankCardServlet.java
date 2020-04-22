package com.fengshen.controller;

import com.fengshen.pojo.Bank;
import com.fengshen.service.BindBankCard;
import com.fengshen.service.BindBankCardImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BankCardServlet" ,urlPatterns="/BankCardServlet")
public class BankCardServlet extends HttpServlet {
    BindBankCard bindBankCard =new BindBankCardImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String userId = session.getAttribute("user").toString();  //通过会话获得登录页面储存的用户名
        String userId = "xiaowang";
        //String user = request.getParameter("user");
        //System.out.println(user);
        List<Bank> list = bindBankCard.quareBankCardByUser(userId);//查询用户绑定的所有银行卡
//        //将获取的银行卡信息传给前端
//        request.setAttribute("list",list);
//        //检查是否绑定银行卡
//        if(bindBankCard.isBindBankCard(userId)){
//            request.getRequestDispatcher("/recharge.jsp").forward(request,response);
//        }else {
//            request.getRequestDispatcher("/bank.jsp").forward(request,response);
//        }
    }
}
