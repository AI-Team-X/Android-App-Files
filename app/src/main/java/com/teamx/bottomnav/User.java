package com.teamx.bottomnav;

import java.io.Serializable;

public class User implements Serializable {
    public String user_type;
    public String user_nin;
    public String user_email;
    public String user_password;
    public String user_phone_number;
    public String user_full_name;
    public String profilePic;
    public String userId;
    public String location;

    public User(String user_type, String user_email, String profilePic, String location) {
        this.user_type = user_type;
        this.user_email = user_email;
        this.profilePic = profilePic;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User(String user_email, String profilePic, String userId) {
        this.user_email = user_email;
        this.profilePic = profilePic;
        this.userId = userId;
    }


    public User(String user_type, String user_nin, String user_email, String user_password, String user_phone_number, String user_full_name, String profilePic, String userId) {
        this.user_type = user_type;
        this.user_nin = user_nin;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone_number = user_phone_number;
        this.user_full_name = user_full_name;
        this.profilePic = profilePic;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public User() {
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_nin() {
        return user_nin;
    }

    public void setUser_nin(String user_nin) {
        this.user_nin = user_nin;
    }



    public User(String user_type, String user_nin, String user_email, String user_password, String user_phone_number, String user_full_name, String profilePic) {
        this.user_type = user_type;
        this.user_nin = user_nin;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_phone_number = user_phone_number;
        this.user_full_name = user_full_name;
        this.profilePic = profilePic;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_phone_number() {
        return user_phone_number;
    }

    public void setUser_phone_number(String user_phone_number) {
        this.user_phone_number = user_phone_number;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }
}
