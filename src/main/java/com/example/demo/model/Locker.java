package com.example.demo.model;

public class Locker {

    String lockerCode;
    String lockerStatus;
    String userAssociated;
    String password;

    Double lockerPrice;

    public Double getLockerPrice() {
        return lockerPrice;
    }

    public void setLockerPrice(Double lockerPrice) {
        this.lockerPrice = lockerPrice;
    }

    public String getLockerCode() {
        return lockerCode;
    }

    public void setLockerCode(String lockerCode) {
        this.lockerCode = lockerCode;
    }

    public String getLockerStatus() {
        return lockerStatus;
    }

    public void setLockerStatus(String lockerStatus) {
        this.lockerStatus = lockerStatus;
    }

    public String getUserAssociated() {
        return userAssociated;
    }

    public void setUserAssociated(String userAssociated) {
        this.userAssociated = userAssociated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
