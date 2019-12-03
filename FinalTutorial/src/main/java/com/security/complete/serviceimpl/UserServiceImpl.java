package com.security.complete.serviceimpl;

import com.security.complete.dto.UserDto;
import com.security.complete.mapper.UserToUserDto;
import com.security.complete.mapping.User;
import com.security.complete.repository.UserRepository;
import com.security.complete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:21 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserToUserDto userToUserDto;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if ( user == null ) {
            throw new UsernameNotFoundException("User not found with the username : " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),user.isEnabled(),true, true,true, new ArrayList<>());
    }

    public UserDto insert(User user) {
        User rUser = null;
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            rUser = userRepository.save(user);
        } catch (Exception e) {
            throw e;
        }
        return userToUserDto.userToUserDto(rUser);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> userToUserDto.userToUserDto(user)).collect(Collectors.toList());
    }
//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        return getGrantedAuthories(getPrivileges(roles));
//    }

//    private List<String> getPrivileges(Collection<Role> roles) {
//
//        List<String> privileges = new ArrayList<>();
//        List<Privilege> collection = new ArrayList<>();
//        for (Role role : roles) {
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privilege item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }

//    private List<GrantedAuthority> getGrantedAuthories(List<String> priviledges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : priviledges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
}
