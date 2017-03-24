package com.nju.hostelworld.model;

import java.io.Serializable;

/**
 * Created by dongyibo on 2017/1/7.
 */
public class Room implements Serializable {

    private int rid;

    private int roomNumber;


    public Room() {
    }

    public Room(int rid, int roomNumber) {
        this.rid = rid;
        this.roomNumber = roomNumber;
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rid=" + rid +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
