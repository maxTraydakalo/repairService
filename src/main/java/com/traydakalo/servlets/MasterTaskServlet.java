package com.traydakalo.servlets;


import com.traydakalo.dto.ClaimDto;
import com.traydakalo.dto.UserDto;
import com.traydakalo.services.ClaimService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/masterTask")
public class MasterTaskServlet extends HttpServlet {
    private ClaimService claimService = ClaimService.getClaimService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDto master = (UserDto)(request.getSession().getAttribute("user"));
        int recordsPerPage = claimService.getRecordsPerPage(
                request.getParameter("recordsPerPage"));
        int rows = claimService.getNumberOfClaimsOfMaster(master.getId()) ;
        int numberOfPages = claimService.getNumberOfPages(rows,recordsPerPage);
        int currentPage = claimService.getCurrentPage(
                request.getParameter("currentPage"), numberOfPages);

        List<ClaimDto> claimDtoListOfMaster = claimService.getClaimsOfMaster(
                master.getId(), recordsPerPage, currentPage);

        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("claimsOfMaster", claimDtoListOfMaster);

        getServletContext().getRequestDispatcher("/WEB-INF/views/masterTaskView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long claimId = Long.parseLong(request.getParameter("id"));
        claimService.updateByMaster(claimId);
        response.sendRedirect(getServletContext().getContextPath()
                +"/masterTask?recordsPerPage=10&currentPage=1");
    }

}
