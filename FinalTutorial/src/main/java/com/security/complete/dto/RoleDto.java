package com.security.complete.dto;

import java.util.List;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:51 PM
 */
public class RoleDto {
    private Long roleId;
    private String name;
    private List<UserDto> users;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
