package com.radz.webapp.controller.goods;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.service.GoodsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listAllGoods")
public class ListAllGoodsServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ListAllGoodsServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Goods> goodsList = null;
        goodsList = GoodsService.getInstance().getAllGoods();

        req.getServletContext().setAttribute("goodsList", goodsList);

        String sortType = req.getParameter("sortType");
        String varPrice1 = req.getParameter("varPrice1");
        String varPrice2 = req.getParameter("varPrice2");
        String varCategory = req.getParameter("varCategory");
        String varColor = req.getParameter("varColor");
        String varSize1 = req.getParameter("varSize1");
        String varSize2 = req.getParameter("varSize2");
        goodsList = GoodsService.getInstance().getAllGoods();
        if (sortType != null) {
            goodsList = GoodsService.getInstance().getAllGoodsSortByType(sortType);
            LOG.info("sort by type:" + sortType);
        } else if (varPrice1 != null && varPrice2 != null) {
            goodsList = GoodsService.getInstance().getAllGoodsSortByRangePrice(varPrice1, varPrice2);
            LOG.info("sort by price:" + varPrice1 + "-" + varPrice2);
        } else if (varCategory != null) {
            goodsList = GoodsService.getInstance().getAllGoodsSortByCategory(varCategory);
            LOG.info("sort by category:" + varCategory);
        } else if (varColor != null) {
            goodsList = GoodsService.getInstance().getAllGoodsSortByColor(varColor);
            LOG.info("sort by color:" + varColor);
        } else if (varSize1 != null && varSize2 != null) {
            goodsList = GoodsService.getInstance().getAllGoodsSortByRangeSize(varSize1, varSize2);
            LOG.info("sort by size:" + varSize1 + "-" + varSize2);
        }
        req.getServletContext().setAttribute("goodsList", goodsList);
        req.getRequestDispatcher(Paths.LIST_ALL_GOODS_PAGE).forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
