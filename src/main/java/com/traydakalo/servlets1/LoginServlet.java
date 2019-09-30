package com.traydakalo.servlets1;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.traydakalo.dto.UserCredentialsDto;
import com.traydakalo.dto.UserDto;
import com.traydakalo.services.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getUserService();

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        UserDto userDto = userService.getUser(login);

        //if (valid(lpdto)){
        if (userService.isCorrect(userDto, login, password)) {
            request.getSession().setAttribute("user", userDto);
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
        //}
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp")
                    .forward(request, response);
        }
    }

}
