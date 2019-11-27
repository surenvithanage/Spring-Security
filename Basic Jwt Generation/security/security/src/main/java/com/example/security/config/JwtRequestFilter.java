package com.example.security.config;

import com.example.security.service.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project security
 * User : suren_v
 * Date : 11/26/2019
 * Time : 2:53 PM
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        String jwtToken = null;

        final String requestTokenHeader = request.getHeader("Authorization");

        // Removing the prefix 'Bearer' from the token for validation
        if ( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch ( IllegalArgumentException e ) {
                System.out.println("Unable to retrieve the token");
            } catch ( ExpiredJwtException e ) {
                System.out.println("JWT token has been expired");
            }
        } else {
            logger.warn(" JWT token doesn't begin with 'Bearer' ");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Get the username from the service
             UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set authentication
            if ( jwtTokenUtil.validateToken(jwtToken, userDetails) ) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                // After setting the Authentication in the context, we specify that the current user is Authenticated.
                // So it passes the Spring Security Configurations Successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
