package com.zlc.BootStudying.po;

/**
 * @author : ZLC
 * @create : 2019-02-14 17:13
 * @desc :
 **/
public class User {

    private String userName;
    private String email;
    private String telNum;
    private String address;
    private String password;

    public User(String userName, String email, String telNum, String address, String password) {
        this.userName = userName;
        this.email = email;
        this.telNum = telNum;
        this.address = address;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", telNum='" + telNum + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
