package com.jwt.jwtauth.model;

/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 10:21 AM
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
