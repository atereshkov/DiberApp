package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {

    @SerializedName("address_to")
    private long id;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("enabled")
    private boolean enabled;

    @SerializedName("fullname")
    private String fullname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
