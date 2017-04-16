package com.github.handioq.diberapp.model.dvo;

public class UserDvo {

    private String email;
    private String username;
    private boolean enabled;
    private String fullname;

    public UserDvo() {
    }

    public UserDvo(String email, String username, boolean enabled, String fullname) {
        this.email = email;
        this.username = username;
        this.enabled = enabled;
        this.fullname = fullname;
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
        return "UserDvo{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", enabled=" + enabled +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
