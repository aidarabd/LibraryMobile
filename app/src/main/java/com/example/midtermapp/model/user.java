package com.example.midtermapp.model;

public class user {
    private String name;
    private String surname;
    private String password;
    private int NumPurchase=5;
    private String address;
    private String role;

    public user(String name, String surname, int numPurchase, String address, String password, String role) {
        this.name = name;
        this.surname = surname;
        NumPurchase = numPurchase;
        this.address = address;
        this.password = password;
        this.role = role;
    }
    public user(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumPurchase() {
        return NumPurchase;
    }

    public void setNumPurchase(int numPurchase) {
        NumPurchase = numPurchase;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
