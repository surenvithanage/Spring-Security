package com.jwt.jwtauth.service;

import com.jwt.jwtauth.dto.UserDto;
import com.jwt.jwtauth.model.User;

import java.util.List;

/**
 * Project jwt-auth
 * User : suren_v
 * Date : 12/2/2019
 * Time : 11:28 AM
 */
public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String username);

    User findById(int id);

    UserDto update(UserDto userDto);
}
