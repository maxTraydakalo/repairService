package com.traydakalo.servlets1;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.traydakalo.dto.LoginAndPasswordDto;
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
        LoginAndPasswordDto lpdto = new LoginAndPasswordDto(
                request.getParameter("login"), request.getParameter("password"));
        //if (valid(lpdto)){
        UserDto userDto = userService.getUser(lpdto.getLogin());
        if (userService.isCorrect
                (userDto, lpdto.getLogin(), lpdto.getPassword())) {
            //getServletContext().setAttribute("user", userDto);
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
