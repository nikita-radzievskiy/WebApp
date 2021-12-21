package com.radz.webapp.service;


import com.radz.webapp.db.dao.GoodsDAO;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.db.utils.DBManager;
import com.radz.webapp.db.utils.ServiceConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GoodsService {

    private static GoodsService instance;

    public static synchronized GoodsService getInstance() {
        if (instance == null) {
            instance = new GoodsService();
        }
        return instance;
    }

    private GoodsService() {
        goodsDao = GoodsDAO.getInstance();
    }


    private GoodsDAO goodsDao;


    public List<Goods> getAllGoods() {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            return goodsDao.findAllGoods(con);
        } finally {
            DBManager.close(con);
        }
    }


    public boolean removeGoodsById(String id) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            return goodsDao.deleteGoodsById(con, id);
        } finally {
            DBManager.close(con);
        }
    }


    public boolean createGoods(Goods goods) {
        Connection con = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            return goodsDAO.createGoods(con, goods);
        } finally {
            DBManager.close(con);
        }
    }

    public void editGoods(Goods goods) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();

            goodsDao.editGoods(con, goods);
        } finally {
            DBManager.close(con);
        }
    }

    public Goods getGoodsById(String goodsId) {
        Connection con = null;
        Goods goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            goods = goodsDAO.findGoodsById(con, goodsId);
        } finally {
            DBManager.close(con);
        }
        return goods;
    }

    public List<Goods> getAllGoodsByOrderId(long id) {
        Connection con = null;
        Goods goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            return goodsDAO.findAllGoodsByOrderId(con, id);
        } finally {
            DBManager.close(con);
        }
    }


    public List<Goods> getAllGoodsSortByType(String sortType) {
        List<Goods> goods = null;
        Connection con = null;
        con = DBManager.getInstance().getConnection();
        try {
            switch (sortType) {
                case ServiceConstants.SORT_BY_NAME_AZ:
                    goods = goodsDao.findAllGoodsByNameAZ(con);
                    break;
                case ServiceConstants.SORT_BY_NAME_ZA:
                    goods = goodsDao.findAllGoodsByNameZA(con);
                    break;
                case ServiceConstants.SORT_BY_PRICE_LOW_TO_HIGH:
                    goods = goodsDao.findAllGoodsByPriceLowToHigh(con);
                    break;
                case ServiceConstants.SORT_BY_PRICE_HIGH_TO_LOW:
                    goods = goodsDao.findAllGoodsByPriceHighToLow(con);
                    break;
                case ServiceConstants.SORT_BY_DATE_NEW_TO_OLD:
                    goods = goodsDao.findAllGoodsByDateNewToOld(con);
                    break;
                case ServiceConstants.SORT_BY_DATE_OLD_TO_NEW:
                    goods = goodsDao.findAllGoodsByDateOldToNew(con);
                    break;
                default:
                    goods = goodsDao.findAllGoodsByNameAZ(con);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con);
        }
        return goods;
    }

    public List<Goods> getAllGoodsSortByRangePrice(String varPrice1, String varPrice2) {
        Connection con = null;
        List<Goods> goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            goods = goodsDAO.findAllGoodsSortByRangePrice(con, varPrice1, varPrice2);
        } finally {
            DBManager.close(con);
        }
        return goods;
    }

    public List<Goods> getAllGoodsSortByCategory(String varCategory) {
        Connection con = null;
        List<Goods> goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            goods = goodsDAO.findAllGoodsSortByCategory(con, varCategory);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con);
        }
        return goods;
    }

    public List<Goods> getAllGoodsSortByColor(String varColor) {
        Connection con = null;
        List<Goods> goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            goods = goodsDAO.findAllGoodsSortByColor(con, varColor);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con);
        }
        return goods;
    }

    public List<Goods> getAllGoodsSortByRangeSize(String varSize1, String varSize2) {
        Connection con = null;
        List<Goods> goods = null;
        GoodsDAO goodsDAO = GoodsDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();

            goods = goodsDAO.findAllGoodsSortByRangeSize(con, varSize1, varSize2);
        } finally {
            DBManager.close(con);
        }
        return goods;
    }
}