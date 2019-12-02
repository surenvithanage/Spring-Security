package com.jwt.jwtauth.jwt;

/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 1:24 PM
 */
public class AuthToken {
    private String token;
    private String username;

    public AuthToken(){

    }

    public AuthToken(String token, String username){
        this.token = token;
        this.username = username;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
