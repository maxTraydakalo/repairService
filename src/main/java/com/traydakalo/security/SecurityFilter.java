package com.traydakalo.security;


import com.traydakalo.dto.UserDto;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        if (SecurityConfig.isSecurityPage(servletPath)) {
            UserDto user = (UserDto) (request.getSession().getAttribute("user"));
            if (user != null && SecurityConfig.hasPermission(servletPath, user.getRoles())) {
                chain.doFilter(req, resp);
            }
            else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            chain.doFilter(req, resp);
        }
    }



}
