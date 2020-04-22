package com.fengshen.pojo;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    private String userId;
    private String userName;
    private String userPasswd;
    private String defrayPasswd;
    private String email;
    private String idCode;
    private String location;
    private String phone;
    private float balance;

    public User(String userId, String userName, String userPasswd, String defrayPasswd, String email, String idCode, String location, String phone, float balance) {
        this.userId = userId;
        this.userName = userName;
        this.userPasswd = userPasswd;
        this.defrayPasswd = defrayPasswd;
        this.email = email;
        this.idCode = idCode;
        this.location = location;
        this.phone = phone;
        this.balance = balance;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", defrayPasswd='" + defrayPasswd + '\'' +
                ", email='" + email + '\'' +
                ", idCode='" + idCode + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getDefrayPasswd() {
        return defrayPasswd;
    }

    public void setDefrayPasswd(String defrayPasswd) {
        this.defrayPasswd = defrayPasswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}