package com.traydakalo.servlets1;

import com.traydakalo.dao.queries.ClaimDao;
import com.traydakalo.dao.queries.ClaimDaoInterface;
import com.traydakalo.entity.Claim;
import com.traydakalo.services.ClaimService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/claim")
public class ClaimServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.sendRedirect(request.getContextPath()+"/claim");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("claims", ClaimService.getClaimService().getAllClaims());
        getServletContext().getRequestDispatcher("/WEB-INF/views/claim.jsp").forward(request, response);
    }
}
