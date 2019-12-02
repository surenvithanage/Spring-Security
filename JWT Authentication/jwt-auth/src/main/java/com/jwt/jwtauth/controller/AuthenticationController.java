package com.jwt.jwtauth.controller;

import com.jwt.jwtauth.config.JwtTokenUtil;
import com.jwt.jwtauth.jwt.AuthToken;
import com.jwt.jwtauth.model.LoginUser;
import com.jwt.jwtauth.model.User;
import com.jwt.jwtauth.response.ApiResponse;
import com.jwt.jwtauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 1:26 PM
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user.getUsername());
        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
    }
}
