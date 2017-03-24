package com.nju.hostelworld.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Reservation implements Serializable {

    private int reid;

    private Date reserveTime;

    private int days;

    private String isUse;

    private VIP vip;

    private Plan plan;

    public Reservation() {
    }

    public Reservation(int reid, Date reserveTime, int days, VIP vip, Plan plan) {
        this.reid = reid;
        this.reserveTime = reserveTime;
        this.days = days;
        this.vip = vip;
        this.plan = plan;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public int getReid() {
        return reid;
    }

    public void setReid(int reid) {
        this.reid = reid;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reid=" + reid +
                ", reserveTime=" + reserveTime +
                ", days=" + days +
                ", vip=" + vip +
                ", plan=" + plan +
                '}';
    }
}
