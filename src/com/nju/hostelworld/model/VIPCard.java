package com.nju.hostelworld.model;

import java.io.Serializable;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class VIPCard implements Serializable {

    private int vcid;

    private double deposit;

    private String grade;

    private int point;

    private VIP vip;

    public VIPCard() {
    }

    public VIPCard(int vcid, double deposit, String grade, int point, VIP vip) {
        this.vcid = vcid;
        this.deposit = deposit;
        this.grade = grade;
        this.point = point;
        this.vip = vip;
    }

    public VIPCard(double deposit, String grade, int point, VIP vip) {
        this.deposit = deposit;
        this.grade = grade;
        this.point = point;
        this.vip = vip;
    }

    public int getVcid() {
        return vcid;
    }

    public void setVcid(int vcid) {
        this.vcid = vcid;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public VIP getVip() {
        return vip;
    }

    public void setVip(VIP vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "VIPCard{" +
                "vcid='" + vcid + '\'' +
                ", deposit=" + deposit +
                ", grade='" + grade + '\'' +
                ", point=" + point +
                ", vip=" + vip +
                '}';
    }
}
