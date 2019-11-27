package com.example.security.model;

import java.io.Serializable;

/**
 * Project security
 * User : suren_v
 * Date : 11/27/2019
 * Time : 10:26 AM
 */
public class JwtRequest implements Serializable {

    private String username;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
