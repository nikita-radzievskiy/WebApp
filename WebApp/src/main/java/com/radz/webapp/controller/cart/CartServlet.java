package com.radz.webapp.controller.cart;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Paths.CART_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Map<String, Goods> cartList = (Map<String, Goods>) session.getAttribute("cartList");

        if (cartList == null) {
            request.setAttribute("error", true);
            request.getRequestDispatcher(Paths.CART_PAGE).forward(request, response);
            return;
        }
        String userLogin = (String) session.getAttribute("login");
        Set<String> orderKeys = cartList.keySet();
        OrderService.getInstance().createOrder(userLogin, orderKeys);
        cartList.clear();
        request.setAttribute("cartList", cartList);
        String address = Paths.LIST_GOODS;
        response.sendRedirect(getServletContext().getContextPath() + address);
    }
}
