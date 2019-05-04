package com.example.simpleforumchatapplication;

import com.google.firebase.database.Exclude;

public class MessageModel {
    public String getTextId() {
        return TextId;
    }

    public void setTextId(String textId) {
        TextId = textId;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public boolean isCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        CurrentUser = currentUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Exclude
    private String TextId;
    private String Text;
    private String UserName;
    private String UserId;
    private boolean CurrentUser;




}
