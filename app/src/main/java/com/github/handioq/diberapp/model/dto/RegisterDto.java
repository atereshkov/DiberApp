package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class RegisterDto {

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("password")
    private String password;

    @SerializedName("customer")
    private boolean customer;

    public RegisterDto() {
    }

    public RegisterDto(String email, String username, boolean enabled, String fullname, String password, boolean customer) {
        this.email = email;
        this.username = username;
        this.enabled = enabled;
        this.fullname = fullname;
        this.password = password;
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }
}
