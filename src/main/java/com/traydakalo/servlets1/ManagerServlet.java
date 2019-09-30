package com.traydakalo.servlets1;


import com.traydakalo.services.ClaimService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    private ClaimService claimService = ClaimService.getClaimService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int rows = claimService.getNumberOfUnmanagedClaims();

        int nOfPages = rows / recordsPerPage;
        if (rows % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("unmanagedClaims",
                claimService.getUnmanagedClaims(recordsPerPage,currentPage));

     getServletContext().getRequestDispatcher("/WEB-INF/views/managerView.jsp")
             .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
