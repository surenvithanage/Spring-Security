package com.securityjwt.models;

/**
 * Project securityjwt
 * User : suren_v
 * Date : 11/29/2019
 * Time : 3:37 PM
 */
public class LoginUser {
    private String username;
    private String password;

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
}
