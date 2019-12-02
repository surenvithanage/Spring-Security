package com.security.complete.service;

import com.security.complete.dto.UserDto;
import com.security.complete.mapping.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 3:40 PM
 */
public interface UserService extends UserDetailsService {

    UserDto insert(User user);
    List<UserDto> getAll();
}
