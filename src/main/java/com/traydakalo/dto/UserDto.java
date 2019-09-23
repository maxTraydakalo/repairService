package com.traydakalo.dto;

import com.traydakalo.entity.Role;
import com.traydakalo.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDto {
    long id;
    String login;
    String password;
    List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(String role){
        roles.add(new Role(role));
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto(String userName, String password, Role... roles) {
        this.login = userName;
        this.password = password;
        this.roles = new ArrayList<>();
        //this.roles.add("MANAGER");
        if (roles != null) {
            Collections.addAll(this.roles, roles);
        }
    }

    public UserDto(String userName, String password, String... roles) {
        this.login = userName;
        this.password = password;
        this.roles = new ArrayList<>();

        if (roles != null) {
            for (String role : roles) {
                this.roles.add(new Role(role));
            }
        }
    }

    public UserDto(User ua){
        this.id=ua.getId();
        this.login=ua.getLogin();
        this.password=ua.getPassword();
        this.roles = ua.getRoles();
    }
}