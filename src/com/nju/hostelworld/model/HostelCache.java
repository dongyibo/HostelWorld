package com.nju.hostelworld.model;

/**
 * Created by dongyibo on 2017/1/16.
 */
public class HostelCache {

    private int hcid;

    private String phone;

    private Hostel hostel;

    public HostelCache(String phone, Hostel hostel) {
        this.phone = phone;
        this.hostel = hostel;
    }

    public HostelCache() {
    }

    public int getHcid() {
        return hcid;
    }

    public void setHcid(int hcid) {
        this.hcid = hcid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
