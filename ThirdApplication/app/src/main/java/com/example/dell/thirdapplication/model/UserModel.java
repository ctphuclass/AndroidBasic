package com.example.dell.thirdapplication.model;

public class UserModel {
    public UserModel(String mName, int mAge){
        this.UserName = mName;
        this.Age = mAge;
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

    private String UserName;
    private int Age;

    @Override
    public String toString() {
        return this.getUserName() + " - " + this.getAge();
    }
}
