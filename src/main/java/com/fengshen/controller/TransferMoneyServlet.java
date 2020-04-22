package com.fengshen.controller;

import com.fengshen.service.TransferMoneyService;
import com.fengshen.service.TransferMoneyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "transferMoney", urlPatterns = "/transferMoney")
public class TransferMoneyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集，防止出现中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        //获得请求数据
        String recUserId = req.getParameter("recUserId");//转账对象的用户ID
        String recUserName = req.getParameter("recUserName");//转账对象用户名
        String inputPwd = req.getParameter("inputPwd");//输入的密码
        String strTradeMoney  = req.getParameter("tradeMoney");//支付金额
        float tradeMoney = Float.parseFloat(strTradeMoney);
        //通过session获得登陆用户的id
        HttpSession session = req.getSession();
        String userId = (String)session.getAttribute("userId");
        //处理请求数据
        TransferMoneyService service = new TransferMoneyServiceImpl();
        if(service.checkRecUser(recUserId,recUserName)){
            if(service.checkDefrayPasswd(userId,inputPwd)){
                if(service.checkMoney(userId,tradeMoney)){
                    //执行转账
                    service.transferMoney(userId,recUserId,tradeMoney);
                }else{
                    System.out.println("余额不足！");
                }
            }else{
                System.out.println("密码错误！");
            }
        }else{
            System.out.println("收款人信息有误！");
        }
    }
}
