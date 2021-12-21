package com.radz.webapp.controller.goods;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.Goods;
import com.radz.webapp.service.GoodsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/createGoods")
public class CreateGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Paths.CREATE_GOODS_PAGE).forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Goods goods = new Goods();
        goods.setName(request.getParameter("name"));
        goods.setPrice(Double.parseDouble(request.getParameter("price")));
        goods.setColor(request.getParameter("color"));
        goods.setSize(Long.parseLong(request.getParameter("size")));
        goods.setAvailable(Long.parseLong(request.getParameter("available")));
        goods.setCategory(request.getParameter("categoryName"));

        GoodsService.getInstance().createGoods(goods);

        response.sendRedirect(getServletContext().getContextPath() + Paths.LIST_GOODS);


    }
}
