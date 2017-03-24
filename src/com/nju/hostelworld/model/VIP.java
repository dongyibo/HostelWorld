package com.nju.hostelworld.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class VIP implements Serializable {

    private String vid;

    private String password;

    private String state;

    private double consumption;

    private Date deadline;

    private String phone;

    private User user;

    private Set<Reservation> reservations;

    public VIP() {
    }

    public VIP(String vid, String password, String state, double consumption, Date deadline, String phone, User user) {
        this.vid = vid;
        this.password = password;
        this.state = state;
        this.consumption = consumption;
        this.deadline = deadline;
        this.phone = phone;
        this.user = user;
    }

    public VIP(String vid, String password, String state, Date deadline, User user) {
        this.vid = vid;
        this.password = password;
        this.state = state;
        this.deadline = deadline;
        this.user = user;
    }

    public VIP(String password, String phone, User user) {
        this.password = password;
        this.phone = phone;
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "VIP{" +
                "vid='" + vid + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", consumption=" + consumption +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }

    public void setUser(User user) {
        this.user = user;
    }

}
