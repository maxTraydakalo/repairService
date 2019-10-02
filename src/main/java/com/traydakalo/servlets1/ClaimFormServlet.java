package com.traydakalo.servlets1;

import com.traydakalo.dto.UserDto;
import com.traydakalo.services.ClaimService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/claimForm")
public class ClaimFormServlet extends javax.servlet.http.HttpServlet {
    private ClaimService claimService = ClaimService.getClaimService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String name = request.getParameter("nameOfClaim");
        String claim = request.getParameter("claim");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        claimService.saveClaim(name, claim, userDto);
        response.sendRedirect(request.getContextPath() + "/claimForm");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/claimFormView.jsp").forward(request, response);
    }
}
