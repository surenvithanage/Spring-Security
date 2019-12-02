package com.jwt.jwtauth.model;

/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 10:21 AM
 */
public class Constant {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
