package com.radz.webapp.db.dao;


import com.radz.webapp.db.entity.User;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAO {

    private static UserDAO instance;

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public UserDAO() {
    }


    public User findUserByLoginAndPassword(Connection connection, String login, String password) {
        User user = null;
        Connection con = connection;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_USER_BY_LOGIN_AND_PASSWORD);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return user;
    }


    public User findUserByLogin(Connection connection, String login) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = connection;


        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return user;
    }

    public boolean bannedAndUnbannedByLogin(Connection con, String login, int role) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        connection = con;
        int k = 1;
        int count = 0;


        try {
            pstmt = connection.prepareStatement(DBConstants.SQL_EDIT_USER_ROLE,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(k++, role);
            pstmt.setString(k++, login);
            count = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return count > 0;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(DBConstants.USER_ID));
        user.setLogin(rs.getString(DBConstants.USER_LOGIN));
        user.setRole(rs.getString(DBConstants.ROLE));
        return user;
    }
}
