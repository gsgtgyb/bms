package com.fengshen.pojo;

public class MoneyVO {
    private String moneyID;//流水单号
    private String moneyDate;//交易时间
    private String moneyManipulate;//交易金额
    private String moneyComment;//交易类型
    private String customer;//转账对象
    private boolean moneyState;//转账状态

    public MoneyVO() {
    }

    public MoneyVO(String moneyID, String moneyDate, String moneyManipulate, String moneyComment, String customer, boolean state) {
        this.moneyID=moneyID;
        this.moneyDate=moneyDate;
        this.moneyManipulate=moneyManipulate;
        this.moneyComment=moneyComment;
        this.customer=customer;
        this.moneyState=state;
    }

    public String getMoneyID() {
        return moneyID;
    }
    public void setMoneyID(String moneyID) {
        this.moneyID=moneyID;
    }

    public String getMoneyDate() {
        return moneyDate;
    }
    public void setMoneyDate(String moneyDate) {
        this.moneyDate=moneyDate;
    }

    public String getMoneyManipulate() {
        return moneyManipulate;
    }
    public void setMoneyManipulate(String moneyManipulate) {
        this.moneyManipulate=moneyManipulate;
    }

    public String getMoneyComment() {
        return moneyComment;
    }
    public void setMoneyComment(String moneyComment) {
        this.moneyComment=moneyComment;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer=customer;
    }

    public boolean getMoneyState() {
        return moneyState;
    }
    public void setMoneyStateState(boolean state) {
        this.moneyState=state;
    }
}