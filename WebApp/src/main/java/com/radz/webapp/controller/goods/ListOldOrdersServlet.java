package com.radz.webapp.controller.goods;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.db.entity.Order;
import com.radz.webapp.db.entity.Status;
import com.radz.webapp.service.GoodsService;
import com.radz.webapp.service.OrderService;
import com.radz.webapp.service.StatusService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listOldOrders")
public class ListOldOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Status status = null;
        List<Order> ordersList = null;
        Map<Order, List<Goods>> ordersUser = new HashMap();
        List<Goods> goodsList = null;
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute("login");

        ordersList = OrderService.getInstance().getAllOrderByLogin(userLogin);
        for (Order order : ordersList) {
            status = StatusService.getInstance().getStatusById(order.getStatusId());
            order.setStatusName(status.getName());
            goodsList = GoodsService.getInstance().getAllGoodsByOrderId(order.getId());
            ordersUser.put(order, goodsList);
        }
        request.setAttribute("ordersUser", ordersUser);
        request.setAttribute("ordersList", ordersList);

        request.getRequestDispatcher(Paths.LIST_OLD_ORDERS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
