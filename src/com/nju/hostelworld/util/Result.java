package com.nju.hostelworld.util;

/**
 * Created by dongyibo on 2017/1/11.
 */
public enum Result {
    Expired("Expired") ,NotYet("NotYet"), InsufficientBalance("InsufficientBalance") ,True("True"), Conflict("Conflict");

    private String result;

    private Result(String result){
        this.result = result;
    }

    @Override
    public String toString() {
        return this.result;
    }
}
