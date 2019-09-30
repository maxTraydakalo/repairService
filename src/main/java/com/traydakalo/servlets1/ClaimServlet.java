package com.traydakalo.servlets1;

import com.traydakalo.dto.UserDto;
import com.traydakalo.services.ClaimService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/claim")
public class ClaimServlet extends javax.servlet.http.HttpServlet {
    private ClaimService claimService = ClaimService.getClaimService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String claim = request.getParameter("claim");
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        claimService.saveClaim(name, claim, userDto);
        response.sendRedirect(request.getContextPath() + "/claim");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("claims", ClaimService.getClaimService().getAllClaims());
        getServletContext().getRequestDispatcher("/WEB-INF/views/claim.jsp").forward(request, response);
    }
}
