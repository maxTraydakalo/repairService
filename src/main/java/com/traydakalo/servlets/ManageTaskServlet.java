package com.traydakalo.servlets;

import com.traydakalo.dto.ClaimDto;
import com.traydakalo.dto.UserDto;
import com.traydakalo.services.ClaimService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manageTask")
public class ManageTaskServlet extends HttpServlet {
    private ClaimService claimService = ClaimService.getClaimService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto manager = (UserDto)(request.getSession().getAttribute("user"));
        long claimId = Long.valueOf(request.getParameter("id"));
        ClaimDto claimDto = claimService.findClaim(claimId);
        long price = Long.parseLong(request.getParameter("price"));
        long masterId= Long.parseLong(request.getParameter("masterId"));
        String rejection = request.getParameter("rejection");
        claimService.updateClaimByManager(manager, claimDto, price, masterId, rejection);
        response.sendRedirect(request.getContextPath()+"/manageTask?id="+claimId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long claimId = Long.valueOf(request.getParameter("id"));
        ClaimDto claimDto = claimService.findClaim(claimId);

        request.setAttribute("claim", claimDto);

        getServletContext().getRequestDispatcher("/WEB-INF/views/manageTaskView.jsp")
                .forward(request, response);
    }
}
