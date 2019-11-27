package com.example.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Project security
 * User : suren_v
 * Date : 11/26/2019
 * Time : 2:53 PM
 */
@Component
public class JwtTokenUtil implements Serializable {

    // validation duration of the generated token
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    // retrieve the username from the token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve the expiration date from the token
    public Date getExpirationDateFromToken(String token ) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver ) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken( String token ) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for the user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // ------------------------------- STEPS -----------------------------------
    // Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    // Sign the JWT using the HS512 algorithm and secret key
    // According to JWS Compact Serialization
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    // token validation
    public Boolean validateToken( String token, UserDetails userDetails ) {
        final String username = getUsernameFromToken(token);
        return ((username.equals(userDetails.getUsername()) && !isTokenExpired(token)));
    }

}
