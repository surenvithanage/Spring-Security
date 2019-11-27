package com.example.security.model;

import java.io.Serializable;

/**
 * Project security
 * User : suren_v
 * Date : 11/27/2019
 * Time : 10:26 AM
 */
public class JwtResponse implements Serializable {
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getJwttoken() {
        return jwttoken;
    }
}
