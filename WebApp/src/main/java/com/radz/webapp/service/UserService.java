package com.radz.webapp.service;


import com.radz.webapp.db.dao.RoleDAO;
import com.radz.webapp.db.dao.UserDAO;
import com.radz.webapp.db.entity.Role;
import com.radz.webapp.db.entity.User;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;

public class UserService {

    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getUsersByLoginAndPassword(String login, String password) {

        Connection con = null;
        UserDAO userDAO = UserDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();
            return userDAO.findUserByLoginAndPassword(con, login, password);
        } finally {
            DBManager.close(con);
        }
    }

    public User getUsersByLogin(String login) {
        Connection con = null;
        UserDAO userDAO = UserDAO.getInstance();
        try {
            con = DBManager.getInstance().getConnection();
            return userDAO.findUserByLogin(con, login);
        } finally {
            DBManager.close(con);
        }
    }

    public boolean bannedAndUnbannedByLogin(String login, String roleName) {
        Connection con = null;
        UserDAO userDAO = UserDAO.getInstance();
        RoleDAO roleDAO = new RoleDAO();
        Role role = null;
        boolean hasBaned;
        try {
            con = DBManager.getInstance().getConnection();
            role = roleDAO.findRoleByName(con, roleName);
            int roleId = (int) role.getId();
            hasBaned = userDAO.bannedAndUnbannedByLogin(con, login, roleId);
        } finally {
            DBManager.close(con);
        }
        return hasBaned;
    }
}
