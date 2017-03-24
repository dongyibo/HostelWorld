package com.nju.hostelworld.model;

import java.io.Serializable;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Hostel implements Serializable {

    private String hid;

    private String hname;

    private String address;

    private String phone;

    private String password;

    private String state;

    private double income;

    public Hostel() {
    }

    public Hostel(String hid, String hname, String address, String phone, String password) {
        this.hid = hid;
        this.hname = hname;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hostel{" +
                "hid='" + hid + '\'' +
                ", hname='" + hname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
