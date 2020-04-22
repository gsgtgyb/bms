package com.fengshen.service;

import com.fengshen.pojo.MoneyVO;

import java.util.List;

public interface FlowingWaterService {
    //查询流水
    public List<MoneyVO> queryPayBill(String userId, String startDate, String endDate, String selectComment, String cusName, float minAmount, float maxAmount);

}