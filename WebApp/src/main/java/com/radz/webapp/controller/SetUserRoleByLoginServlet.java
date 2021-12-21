package com.radz.webapp.controller;

import com.radz.webapp.db.entity.User;
import com.radz.webapp.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/setUserRoleByLogin")
public class SetUserRoleByLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User user;
        user = UserService.getInstance().getUsersByLogin(login);
        request.setAttribute("login", user);

        request.getRequestDispatcher(Paths.SET_USER_ROLE_PAGE).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String address;
        boolean hasChange;
        String login = request.getParameter("login");
        String role = request.getParameter("role");

        hasChange = UserService.getInstance().bannedAndUnbannedByLogin(login, role);
        if (!hasChange) {
            request.setAttribute("error", true);
            request.getRequestDispatcher(Paths.SET_USER_ROLE_PAGE).forward(request, response);
            return;
        }
        address = Paths.LIST_GOODS;
        response.sendRedirect(getServletContext().getContextPath() + address);
    }
}
