package com.nju.hostelworld.model;

import java.io.Serializable;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class BankCard implements Serializable {

    private int bid;

    private double deposit;

    private VIP vip;

    public BankCard() {
    }

    public BankCard(int bid, double deposit, VIP vip) {
        this.bid = bid;
        this.deposit = deposit;
        this.vip = vip;
    }

    public BankCard(double deposit, VIP vip) {
        this.deposit = deposit;
        this.vip = vip;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "bid=" + bid +
                ", deposit=" + deposit +
                ", vip=" + vip +
                '}';
    }
}
