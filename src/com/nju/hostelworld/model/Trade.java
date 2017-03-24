package com.nju.hostelworld.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Trade implements Serializable{

    private int tid;

    private double fee;

    private String way;

    private String addition;

    private String state;

    private int days;

    private Date date;

    private User user;

    private Plan plan;

    public Trade() {
    }

    public Trade(int tid, double fee, String way, String addition, User user, Plan plan) {
        this.tid = tid;
        this.fee = fee;
        this.way = way;
        this.addition = addition;
        this.user = user;
        this.plan = plan;
    }

    public Trade(String way, String addition, String state, int days, User user, Plan plan) {
        this.way = way;
        this.addition = addition;
        this.state = state;
        this.days = days;
        this.user = user;
        this.plan = plan;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tid=" + tid +
                ", fee=" + fee +
                ", way='" + way + '\'' +
                ", addition='" + addition + '\'' +
                ", user=" + user +
                ", plan=" + plan +
                '}';
    }
}
