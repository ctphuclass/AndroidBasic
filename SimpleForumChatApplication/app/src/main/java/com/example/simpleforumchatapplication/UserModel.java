package com.example.simpleforumchatapplication;

import com.google.firebase.database.Exclude;

public class UserModel {
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    @Exclude
    private String UserId;
    private String Email;
}
