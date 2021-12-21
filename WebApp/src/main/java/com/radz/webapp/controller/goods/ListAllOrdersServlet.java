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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/listAllOrders")
public class ListAllOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Status status = null;
        Map<Order, List<Goods>> orders = new LinkedHashMap();
        List<Order> listAllOrders = null;
        List<Goods> goodsList = null;

        listAllOrders = OrderService.getInstance().getAllOrder();

        for (Order order : listAllOrders) {
            status = StatusService.getInstance().getStatusById(order.getStatusId());
            order.setStatusName(status.getName());
            goodsList = GoodsService.getInstance().getAllGoodsByOrderId(order.getId());
            orders.put(order, goodsList);

        }
        request.setAttribute("orders", orders);
        request.setAttribute("goodsList", goodsList);

        request.getRequestDispatcher(Paths.LIST_ALL_ORDERS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
