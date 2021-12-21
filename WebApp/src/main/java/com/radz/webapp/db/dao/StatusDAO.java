package com.radz.webapp.db.dao;

import com.radz.webapp.db.entity.Status;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusDAO {
    private static StatusDAO instance;

    public static synchronized StatusDAO getInstance() {
        if (instance == null) {
            instance = new StatusDAO();
        }
        return instance;
    }

    private Status extractStatus(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getInt(DBConstants.GOODS_ID));
        status.setName(rs.getString(DBConstants.GOODS_NAME));

        return status;
    }

    public Status findStatusByName(Connection connection, String name) {
        Status status = null;
        Connection con = connection;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_STATUS_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                status = extractStatus(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return status;

    }

    public Status findStatusById(Connection connection, long id) {

        Status status = null;
        Connection con = connection;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(DBConstants.SQL_FIND_STATUS_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                status = extractStatus(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }

        return status;
    }
}
