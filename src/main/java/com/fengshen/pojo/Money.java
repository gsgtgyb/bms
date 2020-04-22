package com.fengshen.pojo;

import java.util.Date;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Money {
    private String moneyId;
    private String userId;
    private Date moneyDate;
    private String moneyComment;
    private String moneyManipulate;
    private String inUserID;
    private String outUserID;
    private boolean ence;

    public Money(String moneyId, String userId, Date moneyDate, String moneyComment, String moneyManipulate, String inUserID, String outUserID, boolean ence) {
        this.moneyId = moneyId;
        this.userId = userId;
        this.moneyDate = moneyDate;
        this.moneyComment = moneyComment;
        this.moneyManipulate = moneyManipulate;
        this.inUserID = inUserID;
        this.outUserID = outUserID;
        this.ence = ence;
    }

    public Money() {
    }

    public String getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(String moneyId) {
        this.moneyId = moneyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getMoneyDate() {
        return moneyDate;
    }

    public void setMoneyDate(Date moneyDate) {
        this.moneyDate = moneyDate;
    }

    public String getMoneyComment() {
        return moneyComment;
    }

    public void setMoneyComment(String moneyComment) {
        this.moneyComment = moneyComment;
    }

    public String getMoneyManipulate() {
        return moneyManipulate;
    }

    public void setMoneyManipulate(String moneyManipulate) {
        this.moneyManipulate = moneyManipulate;
    }

    public String getInUserID() {
        return inUserID;
    }

    public void setInUserID(String inUserID) {
        this.inUserID = inUserID;
    }

    public String getOutUserID() {
        return outUserID;
    }

    public void setOutUserID(String outUserID) {
        this.outUserID = outUserID;
    }

    public boolean isEnce() {
        return ence;
    }

    public void setEnce(boolean ence) {
        this.ence = ence;
    }
}