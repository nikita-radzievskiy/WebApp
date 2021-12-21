package com.radz.webapp.controller.goods;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Category;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.service.CategoryService;
import com.radz.webapp.service.GoodsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/editGoods")
public class EditGoodsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsId = request.getParameter("id");
        Goods goods;
        goods = GoodsService.getInstance().getGoodsById(goodsId);
        request.setAttribute("goods", goods);

        request.getRequestDispatcher(Paths.EDIT_GOODS_PAGE).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Goods goods = new Goods();
        String goodsName = request.getParameter("goodsName");
        String goodsId = request.getParameter("goodsId");
        String price = request.getParameter("price");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        String available = request.getParameter("available");
        String categoryName = request.getParameter("categoryName");
        Category category = CategoryService.getInstance().getCategoryByName(categoryName);

        goods.setId(Long.parseLong(goodsId));
        goods.setName(goodsName);
        goods.setPrice(Double.parseDouble(price));
        goods.setColor(color);
        goods.setSize(Long.parseLong(size));
        goods.setCategory(String.valueOf(category.getId()));
        goods.setAvailable(Long.parseLong(available));
        GoodsService.getInstance().editGoods(goods);
        String address = Paths.LIST_GOODS;
        response.sendRedirect(getServletContext().getContextPath() + address);
    }

}
