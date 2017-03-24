package com.nju.hostelworld.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Plan implements Serializable {

    private int pid;

    private double price;

    private Hostel hostel;

    private Room room;

    private Set<Reservation> reservations;

    private Set<Trade> trades;

    public Plan() {
    }

    public Plan(int pid, double price, Hostel hostel, Room room) {
        this.pid = pid;
        this.price = price;
        this.hostel = hostel;
        this.room = room;
    }


    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Set<Trade> trades) {
        this.trades = trades;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "pid=" + pid +
                ", price=" + price +
                ", hostel=" + hostel +
                ", room=" + room +
                ", reservations=" + reservations +
                ", trades=" + trades +
                '}';
    }
}
