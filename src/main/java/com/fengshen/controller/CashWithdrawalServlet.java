package com.fengshen.controller;

import com.fengshen.service.CashWithdrawalService;
import com.fengshen.service.CashWithdrawalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: gsgtgyb
 * @Date: 2020/4/21 16:35
 * @Description:
 */
@WebServlet(name = "cash-withdrawal", urlPatterns = "/cash-withdrawal")
public class CashWithdrawalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Type","text/html;charset=utf-8");
        String inputBankCode=req.getParameter("bankCode");
        float withdrawNum=Float.parseFloat(req.getParameter("withdrawNum"));
        String inputDePassword=req.getParameter("dePassword");
        HttpSession session = req.getSession();
        String userId=(String) session.getAttribute("userId");
        userId="admin";
        CashWithdrawalService cashWithdrawalService = new CashWithdrawalServiceImpl();
        if(cashWithdrawalService.checkBindBank(userId)){
            //绑定了银行卡
            if(cashWithdrawalService.matchingBankCode(userId,inputBankCode)){
                //输入账户匹配
                if(cashWithdrawalService.checkDefrayPasswd(userId,inputDePassword)){
                    //输入支付密码匹配
                    if(cashWithdrawalService.checkMoney(userId,withdrawNum)){
                        //余额充足
                        if(cashWithdrawalService.checkMoney(userId,withdrawNum)){
                            //提现
                            cashWithdrawalService.checkMoney(userId,withdrawNum);
                            req.getRequestDispatcher("index.jsp").forward(req,resp);
                            System.out.println("提现成功");
                        }else {
                            req.setAttribute("Error","提现失败");
                            req.getRequestDispatcher("cash-withdrawal.jsp").forward(req,resp);
                        }
                    }else{
                        req.setAttribute("numError","余额不足");
                        req.getRequestDispatcher("cash-withdrawal.jsp").forward(req,resp);
                    }
                }else {
                    //输入支付密码错误
                    req.setAttribute("pwdErroe","支付密码错误");
                    req.getRequestDispatcher("cash-withdrawal.jsp").forward(req,resp);
                }
            }else {
                //输入账户不匹配
                req.setAttribute("codeErroe","您输入的账户不匹配");
                req.getRequestDispatcher("cash-withdrawal.jsp").forward(req,resp);
            }
        }else {
            //没有绑定银行卡
            req.setAttribute("codeErroe","请先绑定银行卡");
            req.getRequestDispatcher("cash-withdrawal.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
