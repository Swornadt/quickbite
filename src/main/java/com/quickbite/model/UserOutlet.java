package com.quickbite.model;

public class UserOutlet {
    private int userId;
    private int outletId;

    public UserOutlet(int userId, int outletId) {
        this.userId = userId;
        this.outletId = outletId;
    }

    public int getUserId() {
        return userId;
    }

    public int getOutletId() {
        return outletId;
    }
}