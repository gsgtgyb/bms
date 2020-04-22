package com.fengshen.pojo;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Bank {
    private Integer bankId;
    private String userId;
    private String bankCode;
    private float bankMoney;
    private String bankPasswd;

    public Bank(Integer bankId, String userId, String bankCode, float bankMoney, String bankPasswd) {
        this.bankId = bankId;
        this.userId = userId;
        this.bankCode = bankCode;
        this.bankMoney = bankMoney;
        this.bankPasswd = bankPasswd;
    }

    public Bank() {
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public float getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(float bankMoney) {
        this.bankMoney = bankMoney;
    }

    public String getBankPasswd() {
        return bankPasswd;
    }

    public void setBankPasswd(String bankPasswd) {
        this.bankPasswd = bankPasswd;
    }
}
