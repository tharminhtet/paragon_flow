package com.tharminhtet.paragonflow;

/**
 * Created by Thar Min Htet on 8/5/2017.
 */

public class Input {
    private String mStaff;
    private String mService;
    private int mPrice;

    public Input(String staff, String service, int price){
        mStaff = staff;
        mService = service;
        mPrice = price;
    }

    public String getStaff() {
        return mStaff;
    }
    public String getService() {
        return mService;
    }
    public int getPrice() {
        return mPrice;
    }
}
