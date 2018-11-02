package com.example.meditrackr.models;

import com.example.meditrackr.callbacks.PersonCallback;

import java.io.Serializable;

import io.searchbox.annotations.JestId;

public class Profile implements Serializable, PersonCallback {
    @JestId
    private String id;

    private String username;
    private String email;
    private String phone;

    public Profile(String id, String username, String email, String phone){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId(){return id;}

    public void setId(String id){
        this.id = id;
    }

}