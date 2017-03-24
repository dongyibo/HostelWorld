package com.nju.hostelworld.util;

/**
 * Created by dongyibo on 2017/1/8.
 */
public enum Role {
    Manager("manager"), VIP("vip"), Hostel("hostel"), Fail("fail") ,User("User");

    private String role;

    private Role(String role){
        this.role = role;
    }


    @Override
    public String toString() {
        return this.role;
    }
}
