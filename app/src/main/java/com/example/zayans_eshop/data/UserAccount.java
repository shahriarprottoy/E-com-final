package com.example.zayans_eshop.data;

public class UserAccount {
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userLocation;
    private String uniqId;


    public UserAccount(String userName, String userPhone, String userEmail, String userLocation, String uniqId) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userLocation = userLocation;
        this.uniqId = uniqId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public void logOut() {
        userName = null;
        userEmail = null;
        userLocation = null;
        userPhone = null;
        uniqId = null;
    }

    public boolean isEmpty() {
        return userName == null;
    }
}
