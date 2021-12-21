package com.radz.webapp.controller.auth;

import com.radz.webapp.controller.Paths;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.PROFILE).forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("user_id") != null) {

            response.sendRedirect("profile.jsp");
        } else {

            response.sendRedirect("login.jsp");
        }
    }
}
