package com.traydakalo.security;


import com.traydakalo.entity.Role;

import java.util.*;
import java.util.stream.Collectors;

public class SecurityConfig {

    private static final String ROLE_CUSTOMER = "CUSTOMER";
    private static final String ROLE_MANAGER = "MANAGER";
    private static final String ROLE_MASTER = "MASTER";

    private static final Map<String, List<String>> mapConfig = new HashMap<>();
    private static Set<String> protectedUrlPatterns;

    static {
        init();
    }

    private static void init() {
        // Конфигурация для роли "EMPLOYEE".
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/employeeTask");

        mapConfig.put(ROLE_CUSTOMER, urlPatterns1);

        // Конфигурация для роли "MANAGER".
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/manager");
        urlPatterns2.add("/manageTask");

        mapConfig.put(ROLE_MANAGER, urlPatterns2);

        List<String> urlPatterns3 = new ArrayList<>();

        urlPatterns3.add("/userInfo");
        urlPatterns3.add("/managerTask");
        urlPatterns3.add("/employeeTask");

        mapConfig.put(ROLE_MASTER, urlPatterns2);
        protectedUrlPatterns = mapConfig.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static boolean isSecurityPage(String servletPath) {
        return protectedUrlPatterns.contains(servletPath);
    }

    public static boolean hasPermission(String urlPattern, List<Role> roles) {
        return roles.stream()
                .anyMatch(x -> mapConfig
                        .get(x.getRole())
                        .contains(urlPattern));

    }
}
