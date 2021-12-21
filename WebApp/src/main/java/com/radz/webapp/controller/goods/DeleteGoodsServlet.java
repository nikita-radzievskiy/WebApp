package com.radz.webapp.controller.goods;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.service.GoodsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/deleteGoods")
public class DeleteGoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        GoodsService.getInstance().removeGoodsById(id);
        response.sendRedirect(getServletContext().getContextPath() + Paths.LIST_GOODS);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
