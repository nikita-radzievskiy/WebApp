package com.radz.webapp.controller;

import com.radz.webapp.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/setStatusByOrderId")
public class SetStatusByOrderIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(Paths.SET_STATUS_ORDER).forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;
        String orderId = request.getParameter("orderId");
        String orderStatus = request.getParameter("orderStatus");

        boolean hasChange = OrderService.getInstance().setStatusByOrderId(orderId, orderStatus);

        if (!hasChange) {
            request.setAttribute("error", true);
            request.getRequestDispatcher(Paths.SET_STATUS_ORDER).forward(request, response);
            return;
        }
        address = Paths.LIST_GOODS;
        response.sendRedirect(getServletContext().getContextPath() + address);
    }
}
