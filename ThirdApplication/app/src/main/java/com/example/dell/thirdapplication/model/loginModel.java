package com.example.dell.thirdapplication.model;

public class loginModel {
    public loginModel(String mName, int mAge, String mPhone){
        this.UserName = mName;
        this.Age = mAge;
        this.PhoneNumber = mPhone;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    private String UserName;
    private int Age;
    private String PhoneNumber;

    @Override
    public String toString() {
        return this.getUserName() + " - " + this.Age + " - " + this.PhoneNumber;
    }
}
