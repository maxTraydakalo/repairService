package com.traydakalo.entity;

import com.traydakalo.dto.UserDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Role> roles;

    public User() {
        roles = new ArrayList<>();
    }

    public User(UserDto userDto) {
        this.login = userDto.getLogin();
        this.password = userDto.getPassword();
        this.roles = userDto.getRoles();
    }

    public User(String userName, String password, Role... roles) {
        this.login = userName;
        this.password = password;
        this.roles = new ArrayList<>();
        //this.roles.add("MANAGER");
        if (roles != null) {
            Collections.addAll(this.roles, roles);
        }
    }

    public User(String userName, String password, String... roles) {
        this.login = userName;
        this.password = password;
        this.roles = new ArrayList<>();

        if (roles != null) {
            for (String role : roles) {
                this.roles.add(new Role(role));
            }
        }
    }

    public void addRole(String role) {
        roles.add(new Role(role));
    }

    public void addRole(Role role) {
        roles.add(role);
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

    public void setLogin(String userName) {
        this.login = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}