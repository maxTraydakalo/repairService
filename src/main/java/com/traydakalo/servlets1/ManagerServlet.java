package com.traydakalo.servlets1;


import com.traydakalo.dto.ClaimDto;
import com.traydakalo.services.ClaimService;

import java.io.IOException;
import java.util.List;

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
        int recordsPerPage = claimService
                .getRecordsPerPage(request.getParameter("recordsPerPage"));
        int numberOfRows = claimService.getNumberOfUnmanagedClaims();
        int numberOfPages = claimService.getNumberOfPages(numberOfRows,recordsPerPage);
        int currentPage = claimService
                .getCurrentPage(request.getParameter("currentPage"), numberOfPages);

        List<ClaimDto> claimDtoList = claimService
                .getUnmanagedClaims(recordsPerPage, currentPage);



        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("unmanagedClaims", claimDtoList);

        getServletContext().getRequestDispatcher("/WEB-INF/views/managerView.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
