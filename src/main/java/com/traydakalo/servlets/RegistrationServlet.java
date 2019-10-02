package com.traydakalo.servlets;

import com.traydakalo.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserService.getUserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String login =request.getParameter("login");
        String password=request.getParameter("password");
        userService.register(login, password);
        response.sendRedirect(request.getContextPath() + "/login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/registrationView.jsp").forward(request, response);
    }
}
