package com.fengshen.controller;

import com.fengshen.pojo.Bank;
import com.fengshen.pojo.Money;
import com.fengshen.pojo.User;
import com.fengshen.service.BindBankCard;
import com.fengshen.service.BindBankCardImpl;
import com.fengshen.service.EchargeServicer;
import com.fengshen.service.EchargeServicerImpl;
import com.fengshen.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "EchargeServlet",urlPatterns="/EchargeServlet")
public class EchargeServlet extends HttpServlet {
    BindBankCard bindBankCard = new BindBankCardImpl();
    EchargeServicer echargeServicer = new EchargeServicerImpl();
    DateUtil moneyIdUtil = new DateUtil();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type","text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //获得请求的前端数据
        //String userId = session.getAttribute("user").toString();  //通过会话获得登录页面储存的用户名
        String userId = "xiaowang";
        String defrayPasswd = request.getParameter("defrayPasswd");//支付密码
        String bankCode = request.getParameter("bankCode");//银行卡号
        Float amount = Float.valueOf(request.getParameter("amount"));//充值金额
        //检查用户是否已经绑定银行卡
        if(bindBankCard.isBindBankCard(userId)){
            //检查用户输入的支付密码是否正确
            User user = echargeServicer.quaryUsersById(userId);//通过用户Id查找用户信息
            Bank bank = bindBankCard.quaryBankByUserAndCode(userId,bankCode);//通过用户名和银行卡号查找银行卡信息
            Float bankMoney = bank.getBankMoney();//银行卡余额
            Float balance = user.getBalance();//用户的余额
            //System.out.println("用户支付密码:"+user.getDefrayPasswd());
            //System.out.println("输入的支付密码："+defrayPasswd);
            if((bankMoney-amount)>=0){//银行卡余额大于充值金额
                if(user.getDefrayPasswd().equals(defrayPasswd)){//如果支付密码正确扣减对应银行卡余额
                    bank.setBankMoney(bankMoney-amount);
                    bindBankCard.upbank(bank);
                    //增加用户余额
                    user.setBalance(balance+amount);
                    echargeServicer.upUsers(user);
                    //新增添加充值成功流水
                    Timestamp timestamp = new Timestamp(new Date().getTime());
                    String moneyId=moneyIdUtil.getDateTime()+"CZCG"+moneyIdUtil.getNumber();
                    Money money = new Money(moneyId,userId, timestamp,"充值"+amount+"元","充值",null,null,true);
                    echargeServicer.addMoney(money);
                    //充值成功
                    request.setAttribute("msg","充值成功");
                    //回到首页
                    request.getRequestDispatcher("/recharge.jsp").forward(request,response);
                }else {
                    //如果支付密码错误新增添加充值失败流水
                    Timestamp timestamp = new Timestamp(new Date().getTime());
                    String moneyId = moneyIdUtil.getDateTime()+"CZSB"+moneyIdUtil.getNumber();
                    Money money = new Money(moneyId,userId, timestamp,"充值"+amount+"元","充值",null,null,false);
                    echargeServicer.addMoney(money);
                    request.setAttribute("msg","支付密码错误，请重新输入");
                    request.getRequestDispatcher("/recharge.jsp").forward(request,response);
                }
            }else {
                //如果银行卡余额不足新增充值失败流水
                Timestamp timestamp = new Timestamp(new Date().getTime());
                String moneyId = moneyIdUtil.getDateTime()+"CZSB"+moneyIdUtil.getNumber();
                Money money = new Money(moneyId,userId, timestamp,"充值"+amount+"元","充值",null,null,false);
                echargeServicer.addMoney(money);
                request.setAttribute("msg","银行卡余额不足，请更换银行卡");
                request.getRequestDispatcher("/recharge.jsp").forward(request,response);
            }
        }else {
            //如果没有绑定银行卡
            request.setAttribute("msg","请先绑定银行卡");
            //请求转发至绑定银行卡页面
            request.getRequestDispatcher("/bank.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
