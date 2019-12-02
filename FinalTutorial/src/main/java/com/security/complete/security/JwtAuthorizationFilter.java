package com.security.complete.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static com.security.complete.utility.Constant.HEADER_STRING;
import static com.security.complete.utility.Constant.SECRET;
import static com.security.complete.utility.Constant.TOKEN_PREFIX;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:01 PM
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    // Check if the request contains authorization header
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if ( header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    // Verfying the token with the secret key
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER_STRING);
            if ( token != null ) {
                // parse the token
                String user = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
                        .verify(token.replace(TOKEN_PREFIX, "")).getSubject();
                if ( user != null ) {
                    return new UsernamePasswordAuthenticationToken(user , null, new ArrayList<>());
                }
                return null;
            }
        } catch (JWTVerificationException e) {
            throw new TokenExpiredException(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
