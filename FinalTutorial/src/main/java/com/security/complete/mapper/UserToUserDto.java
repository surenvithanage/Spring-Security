package com.security.complete.mapper;

import com.security.complete.dto.RoleDto;
import com.security.complete.dto.UserDto;
import com.security.complete.mapping.Role;
import com.security.complete.mapping.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:47 PM
 */
@Component
public class UserToUserDto {

    private RoleDto roleToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleId(role.getRoleId());
        roleDto.setName(role.getName());
        roleDto.setUsers(null);
        return roleDto;
    }

    public UserDto userToUserDto(User user ) {
        UserDto userDto = new UserDto();
        userDto.setChannel(user.getChannel());
        userDto.setCreatedtime(user.getCreatedtime());
        userDto.setUsername(user.getUsername());
        userDto.setEnabled(user.isEnabled());
        userDto.setLoginId(user.getLoginId());
        userDto.setLoginIdType(user.getLoginIdType());
        userDto.setMobile(user.getMobile());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setPushId(user.getPushId());
        userDto.setRoles(responseToRoleDto(user.getRoles()));
        userDto.setUserId(user.getChannel());
        return userDto;
    }

    private List<RoleDto> responseToRoleDto(List<Role> rolelist){
        List<RoleDto> roleDtoList = new ArrayList<>();
        try{
            if(rolelist != null && rolelist.size() > 0){
                roleDtoList = rolelist.stream().map(this::roleToRoleDto).collect(Collectors.toList());
            }
        }catch(Exception e){
            throw e;
        }
        return roleDtoList;
    }
}
