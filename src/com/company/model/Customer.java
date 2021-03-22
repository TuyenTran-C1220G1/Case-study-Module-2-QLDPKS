package com.company.model;

public class Customer {

    //region PROPERTIES
    private String name;
    private String address;
    private String phoneNumber;
    private String idCard;
    //endregion

    // region CONSTRUCTOR
    public Customer() {
    }

    public Customer(String name, String address, String phoneNumber, String idCard) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.idCard = idCard;
    }
    //endregion

    //region SETTER AND GETTER

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    //endregion

    //region TOSTRING
    @Override
    public String toString() {
        return name + "," + address + "," + phoneNumber + "," + idCard;
    }
    //endregion
}
