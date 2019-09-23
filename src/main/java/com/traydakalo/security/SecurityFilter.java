package com.traydakalo.security;


import com.traydakalo.dto.UserDto;
import com.traydakalo.utils.SecurityUtils;

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
        if (SecurityUtils.isSecurityPage(servletPath)) {
            UserDto user = (UserDto) (request.getSession().getAttribute("user"));
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
            } else if (SecurityUtils.hasPermission(servletPath, user.getRoles())) {
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
/*

package com.traydakalo.security;


        import com.traydakalo.entity.Role;
        import com.traydakalo.entity.User;
        import com.traydakalo.utils.AppUtils;
        import com.traydakalo.utils.SecurityUtils;

        import java.io.IOException;
        import java.util.List;

        import javax.servlet.Filter;
        import javax.servlet.FilterChain;
        import javax.servlet.FilterConfig;
        import javax.servlet.RequestDispatcher;
        import javax.servlet.ServletException;
        import javax.servlet.ServletRequest;
        import javax.servlet.ServletResponse;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
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

        // Информация пользователя сохранена в Session
        // (После успешного входа в систему).
        User loginedUser = AppUtils.getLoginedUser(request.getSession());

        if (!servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest wrapRequest = request;

        if (loginedUser != null) {
            // User Name
            String login = loginedUser.getLogin();

            // Роли (Role).
            List<Role> roles = loginedUser.getRoles();

            // Старый пакет request с помощью нового Claim с информацией userName и Roles.
            wrapRequest = new UserRoleRequestWrapper(login, roles, request);
        }

        // Страницы требующие входа в систему.
        if (SecurityUtils.isSecurityPage(request)) {

            // Если пользователь еще не вошел в систему,
            // Redirect (перенаправить) к странице логина.
            if (loginedUser == null) {

                String requestUri = request.getRequestURI();

                // Сохранить текущую страницу для перенаправления (redirect) после успешного входа в систему.
//                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);

//                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                return;
            }

            // Проверить пользователь имеет действительную роль или нет?
            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {

                RequestDispatcher dispatcher //
                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");

                dispatcher.forward(request, response);
                return;
            }
        }

        chain.doFilter(wrapRequest, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
*/
