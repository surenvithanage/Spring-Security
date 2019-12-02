package com.security.complete.controller;

import com.security.complete.mapping.User;
import com.security.complete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(userService.insert(user), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
        return responseEntity;
    }
}
