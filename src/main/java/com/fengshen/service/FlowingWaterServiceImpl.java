package com.fengshen.service;

import com.fengshen.dao.MoneyDao;
import com.fengshen.dao.MoneyDaoImpl;
import com.fengshen.pojo.MoneyVO;

import java.util.List;

public class FlowingWaterServiceImpl implements FlowingWaterService{
    MoneyDao moneyDaoImpl = new MoneyDaoImpl();

    public List<MoneyVO> queryPayBill(String userId, String startDate, String endDate, String selectComment, String cusName, float minAmount, float maxAmount){
        System.out.println(1);
        return moneyDaoImpl.quaryMoney(userId,startDate,endDate,selectComment,cusName,minAmount,maxAmount);
    }
}
