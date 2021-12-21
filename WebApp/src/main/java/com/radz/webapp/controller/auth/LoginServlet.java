package com.radz.webapp.controller.auth;

import com.radz.webapp.controller.Paths;
import com.radz.webapp.db.entity.User;
import com.radz.webapp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address;

        User user = null;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        user = UserService.getInstance().getUsersByLoginAndPassword(login, password);

        if (user == null) {
            req.setAttribute("error", true);
            req.getRequestDispatcher(Paths.LOGIN_PAGE).forward(req, resp);
            return;

        }

        HttpSession session = req.getSession();
        String role = user.getRole();

        session.setAttribute("user_id", user.getId());
        session.setAttribute("role", role);
        session.setAttribute("login", login);

        session.setMaxInactiveInterval(15 * 60);
        address = Paths.LIST_GOODS;

        resp.sendRedirect(getServletContext().getContextPath() + address);
    }
}




