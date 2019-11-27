package com.example.security.controller;

import com.example.security.config.JwtTokenUtil;
import com.example.security.model.JwtRequest;
import com.example.security.model.JwtResponse;
import com.example.security.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Project security
 * User : suren_v
 * Date : 11/27/2019
 * Time : 10:26 AM
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailService jwtUserDetailService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken( @RequestBody JwtRequest jwtRequest ) throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate( String username, String password ) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch ( DisabledException e) {
            throw new Exception("User disabled : " + e);
        } catch ( BadCredentialsException e ) {
            throw new Exception("Bad credentials : " + e);
        }
    }
}
