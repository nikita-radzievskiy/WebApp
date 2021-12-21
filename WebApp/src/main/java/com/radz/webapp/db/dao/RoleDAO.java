package com.radz.webapp.db.dao;

import com.radz.webapp.db.entity.Role;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {

    private static RoleDAO instance;

    public static synchronized RoleDAO getInstance() {
        if (instance == null) {
            instance = new RoleDAO();
        }
        return instance;
    }

    public RoleDAO() {
    }

    private static Role extractRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong(DBConstants.ROLE_ID));
        role.setName(rs.getString(DBConstants.ROLE_NAME));
        return role;
    }

    public Role findRoleByName(Connection connection, String roleName) {
        Role role = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = connection;


        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_ROLE_BY_NAME);
            pstmt.setString(1, roleName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return role;
    }
}
