package com.radz.webapp.controller.cart;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Goods;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/deleteFromCart")
public class DeleteFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String goodsId = request.getParameter("id");
        HttpSession session = request.getSession();
        Map<String, Goods> cartList = (Map<String, Goods>) session.getAttribute("cartList");
        if (cartList == null) {
            cartList = new HashMap<>();
        }
        cartList.remove(goodsId);
        session.setAttribute("cartList", cartList);
        String address = Paths.LIST_CART;
        response.sendRedirect(getServletContext().getContextPath() + address);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
