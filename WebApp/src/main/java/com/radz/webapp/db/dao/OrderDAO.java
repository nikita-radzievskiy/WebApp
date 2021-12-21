package com.radz.webapp.db.dao;

import com.radz.webapp.db.entity.Order;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static OrderDAO instance;

    public static synchronized OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public boolean editStatusByOrderId(Connection con, String orderId, long orderStatusId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        int count = 0;
        Connection connection = null;
        connection = con;

        try {
            pstmt = connection.prepareStatement(DBConstants.SQL_EDIT_ORDER_STATUS_BY_ID,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(k++, orderStatusId);
            pstmt.setString(k++, orderId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return count > 0;
    }

    public List<Order> findAllOrder(Connection con) {

        List<Order> listOfOrders = new ArrayList<>();

        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(DBConstants.SQL_FIND_ALL_ORDER);
            while (rs.next()) {
                listOfOrders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(statement);
        }

        return listOfOrders;
    }

    public long createOrder(Connection con, Order order) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long orderId = 0;
        String generatedColumns[] = {"id"};
        int k = 1;
        connection = con;
        try {
            pstmt = connection.prepareStatement(DBConstants.SQL_CREATE_ORDER,
                    generatedColumns);
            pstmt.setLong(k++, order.getUserId());
            pstmt.setLong(k++, order.getStatusId());
            if (pstmt.executeUpdate() > 0) {

                java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return orderId;
    }

    public void addGoodsToOrder(Connection con, String goodsId, long orderId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int k = 1;
        connection = con;
        try {
            pstmt = connection.prepareStatement(DBConstants.SQL_ADD_GOODS_TO_ORDER,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(k++, orderId);
            pstmt.setLong(k++, Long.parseLong(goodsId));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
    }


    public List<Order> findAllOrdersByLogin(Connection con, String userLogin) {
        List<Order> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_ORDER_BY_LOGIN,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, userLogin);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;

    }

    public static Order extractOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt(DBConstants.ORDER_ID));
        order.setUserId(rs.getLong("user_id"));
        order.setStatusId(rs.getLong("status_id"));
        order.setCreatedAt(rs.getDate("created_at"));
        return order;
    }
}

