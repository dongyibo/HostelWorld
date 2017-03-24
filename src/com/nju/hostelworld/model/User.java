package com.nju.hostelworld.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dongyibo on 2017/1/4.
 */
public class User implements Serializable{

    private int uid;

    private String uname;

    private String gender;

    private String idCardNumber;

    private String isVIP;

    private Set<Trade> trades;

    public User() {
    }

    public User(int uid, String uname, String gender, String idCardNumber) {
        this.uid = uid;
        this.uname = uname;
        this.gender = gender;
        this.idCardNumber = idCardNumber;
    }

    public User(String uname, String gender, String idCardNumber) {
        this.uname = uname;
        this.gender = gender;
        this.idCardNumber = idCardNumber;
    }

    public String getIsVIP() {
        return isVIP;
    }

    public void setIsVIP(String isVIP) {
        this.isVIP = isVIP;
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Set<Trade> trades) {
        this.trades = trades;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}';
    }
}
