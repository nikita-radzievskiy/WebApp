package com.radz.webapp.service;

import com.radz.webapp.db.dao.OrderDAO;
import com.radz.webapp.db.dao.UserDAO;
import com.radz.webapp.db.entity.Order;
import com.radz.webapp.db.entity.Status;
import com.radz.webapp.db.entity.User;
import com.radz.webapp.db.utils.DBManager;
import com.radz.webapp.db.utils.ServiceConstants;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class OrderService {
    private static OrderService instance;
    private OrderDAO orderDAO;


    public static synchronized OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    private OrderService() {
        orderDAO = OrderDAO.getInstance();
    }


    public boolean setStatusByOrderId(String orderId, String orderStatus) {
        Connection con = null;
        boolean hasBaned;
        try {
            con = DBManager.getInstance().getConnection();
            Status status = StatusService.getInstance().getStatusByName(orderStatus);
            long orderStatusId = status.getId();
            hasBaned = orderDAO.editStatusByOrderId(con, orderId, orderStatusId);
        } finally {
            DBManager.close(con);
        }
        return hasBaned;
    }

    public List<Order> getAllOrder() {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            return orderDAO.findAllOrder(con);
        } finally {
            DBManager.close(con);
        }
    }

    public void createOrder(String userLogin, Set<String> orderKeys) {
        Connection con = null;
        long orderId;
        try {
            con = DBManager.getInstance().getConnection();
            User user = UserService.getInstance().getUsersByLogin(userLogin);
            Status status = StatusService.getInstance().getStatusByName(ServiceConstants.STATUS_BOOK);
            Order order = new Order(status.getId());
            order.setUserId(user.getId());
            orderId = OrderDAO.getInstance().createOrder(con, order);
            for (String goodsId : orderKeys) {
                OrderDAO.getInstance().addGoodsToOrder(con, goodsId, orderId);
            }

        } finally {
            DBManager.close(con);
        }
    }

    public List<Order> getAllOrderByLogin(String userLogin) {
        List<Order> orders = null;
        Connection con = null;
        User user = null;
        Order order = null;
        OrderDAO orderDAO = OrderDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();
            user = UserDAO.getInstance().findUserByLogin(con, userLogin);
            long userLoginId = user.getId();
            orders = orderDAO.findAllOrdersByLogin(con, String.valueOf(userLoginId));
        } finally {
            DBManager.close(con);
        }
        return orders;
    }
}
