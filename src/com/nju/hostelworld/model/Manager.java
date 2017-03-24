package com.nju.hostelworld.model;

import java.io.Serializable;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Manager implements Serializable{

    private String mid;

    private String password;

    private double income;

    private User user;

    public Manager() {
    }

    public Manager(String mid, String password, User user) {
        this.mid = mid;
        this.password = password;
        this.user = user;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "mid='" + mid + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
