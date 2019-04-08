package com.example.fourthapplication.model;

public class UserModel {
    public UserModel(){}
    public UserModel(String mName, int mAge, String mAddress){
        this.UserName = mName;
        this.Age = mAge;
        this.Address = mAddress;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    private String UserName;
    private int Age;
    private String Address;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
