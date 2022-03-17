package com.example.projectmain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    @SerializedName("nik")
    private String nik;
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("phone")
    private String phone;

    public User(String nik, String username, String password, String phone) {
        this.nik = nik;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) { this.nik = nik; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
