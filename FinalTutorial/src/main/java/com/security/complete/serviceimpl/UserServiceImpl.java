package com.security.complete.serviceimpl;

import com.security.complete.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:21 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
