package com.radz.webapp.db.dao;

import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {
    private static GoodsDAO instance;

    public static synchronized GoodsDAO getInstance() {
        if (instance == null) {
            instance = new GoodsDAO();
        }
        return instance;
    }


    public List<Goods> findAllGoods(Connection con) {
        List<Goods> listOfGoods = new ArrayList<>();

        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = con.createStatement();
            rs = statement.executeQuery(DBConstants.SQL_FIND_ALL_GOODS);
            while (rs.next()) {
                listOfGoods.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(statement);
        }

        return listOfGoods;
    }

    public boolean deleteGoodsById(Connection con, String id) {
        Goods goods = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(DBConstants.SQL_DELETE_GOODS_BY_ID);
            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return false;
    }


    public boolean createGoods(Connection con, Goods goods) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;

        try {
            pstmt = con.prepareStatement(DBConstants.SQL_CREATE_GOODS,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, goods.getName());
            pstmt.setString(k++, goods.getPrice());
            pstmt.setString(k++, goods.getColor());
            pstmt.setLong(k++, goods.getSize());
            pstmt.setLong(k++, goods.getAvailable());
            pstmt.setString(k++, goods.getCategory());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return false;
    }

    public void editGoods(Connection con, Goods goods) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        Connection connection = null;
        connection = con;

        try {
            pstmt = connection.prepareStatement(DBConstants.SQL_EDIT_GOODS,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, goods.getName());
            pstmt.setString(k++, goods.getPrice());
            pstmt.setString(k++, goods.getColor());
            pstmt.setLong(k++, goods.getSize());
            pstmt.setLong(k++, goods.getAvailable());
            pstmt.setString(k++, goods.getCategory());
            pstmt.setInt(k++, Math.toIntExact(goods.getId()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
    }

    public Goods findGoodsById(Connection con, String goodsId) {
        Goods goods = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = con;
            pstmt = connection.prepareStatement(DBConstants.SQL_FIND_GOODS_BY_ID);
            pstmt.setString(1, goodsId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                goods = extractGoods(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return goods;
    }

    public List<Goods> findAllGoodsByOrderId(Connection con, long id) {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_ALL_GOODS_BY_ORDER_ID,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(k++, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;

    }


    public List<Goods> findAllGoodsByNameAZ(Connection con) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_NAME_AZ);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsByNameZA(Connection con) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_NAME_ZA);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsByPriceLowToHigh(Connection con) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_PRICE_LOW_TO_HIGH);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsByPriceHighToLow(Connection con) {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_PRICE_HIGH_TO_LOW);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsByDateNewToOld(Connection con) {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_DATE_NEW_TO_OLD);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsByDateOldToNew(Connection con) {
        List<Goods> goodsList = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(DBConstants.SQL_FIND_ALL_GOODS_BY_DATE_OLD_TO_NEW);
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(stmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsSortByRangePrice(Connection con, String varPrice1, String varPrice2) {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_ALL_GOODS_BY_RANGE_PRICE,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, varPrice1);
            pstmt.setString(k++, varPrice2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsSortByCategory(Connection con, String varCategory) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_ALL_GOODS_BY_CATEGORY,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, varCategory);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsSortByColor(Connection con, String varColor) throws SQLException {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            try {
                pstmt = con.prepareStatement(DBConstants.SQL_FIND_ALL_GOODS_BY_COLOR,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstmt.setString(k++, varColor);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;
    }

    public List<Goods> findAllGoodsSortByRangeSize(Connection con, String varSize1, String varSize2) {
        List<Goods> goodsList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int k = 1;
        try {
            try {
                pstmt = con.prepareStatement(DBConstants.SQL_FIND_ALL_GOODS_BY_RANGE_SIZE,
                        Statement.RETURN_GENERATED_KEYS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstmt.setString(k++, varSize1);
            pstmt.setString(k++, varSize2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                goodsList.add(extractGoods(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return goodsList;
    }

    private Goods extractGoods(ResultSet rs) throws SQLException {
        Goods goods = new Goods();
        goods.setId(rs.getInt(DBConstants.GOODS_ID));
        goods.setName(rs.getString(DBConstants.GOODS_NAME));
        goods.setPrice(rs.getDouble((DBConstants.GOODS_PRICE)));
        goods.setCreatedAt(rs.getDate(DBConstants.GOODS_DATE));
        goods.setColor(rs.getString(DBConstants.GOODS_COLOR));
        goods.setSize(rs.getLong(DBConstants.GOODS_SIZE));
        goods.setAvailable(rs.getLong(DBConstants.GOODS_AVAILABLE));
        goods.setCategory(rs.getString(DBConstants.GOODS_CATEGORY));

        return goods;
    }
}

