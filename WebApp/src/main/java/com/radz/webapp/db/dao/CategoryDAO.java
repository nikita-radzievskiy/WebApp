package com.radz.webapp.db.dao;

import com.radz.webapp.db.entity.Category;
import com.radz.webapp.db.utils.DBConstants;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDAO {

    private static CategoryDAO instance;

    public static synchronized CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }

    public Category findCategoryByName(Connection con, String categoryName) {
        PreparedStatement pstmt = null;
        Category category = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = con;
            pstmt = connection.prepareStatement(DBConstants.SQL_FIND_CATEGORY_BY_NAME);
            pstmt.setString(1, categoryName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                category = extractCategory(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return category;
    }

    public static Category extractCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt(DBConstants.CATEGORY_ID));
        category.setName(rs.getString(DBConstants.CATEGORY_NAME));
        return category;
    }
}
