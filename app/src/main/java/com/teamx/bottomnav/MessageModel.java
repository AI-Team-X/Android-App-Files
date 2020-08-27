package com.teamx.bottomnav;

import java.util.Date;

public class MessageModel {

    public String username;

    public MessageModel(String username, String usertype, String email, String userloaction) {
        this.username = username;
        this.usertype = usertype;
        this.email = email;
        this.userloaction = userloaction;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserloaction() {
        return userloaction;
    }

    public void setUserloaction(String userloaction) {
        this.userloaction = userloaction;
    }

    public String usertype;
    public String email;
    public String userloaction;
    public String message;
    public int messageType;
    public Date messageTime = new Date();
    // Constructor
    public MessageModel(String message, int messageType) {
        this.message = message;
        this.messageType = messageType;
    }
}