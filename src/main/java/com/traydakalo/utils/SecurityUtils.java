package com.traydakalo.utils;


import com.traydakalo.entity.Role;
import com.traydakalo.security.SecurityConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;


public class SecurityUtils {


    public static boolean isSecurityPage(String servletPath) {
        return SecurityConfig.getMapConfig().values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .contains(servletPath);
    }

    public static boolean hasPermission(String urlPattern, List<Role> roles) {
        return roles.stream()
                .anyMatch(x -> SecurityConfig.getMapConfig()
                        .get(x.getRole())
                        .contains(urlPattern));

    }
}