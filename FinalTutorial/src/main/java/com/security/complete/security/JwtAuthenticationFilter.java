package com.security.complete.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.complete.mapping.User;
import com.security.complete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.security.complete.utility.Constant.*;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 3:39 PM
 * Description: User Authentication
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Authentication user based on the users email and password
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void validateAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        if(Objects.isNull(userService)) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webApplicationContext.getBean(UserService.class);
        }
        String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
        String token = JWT.create().withSubject(username).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        response.setHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
