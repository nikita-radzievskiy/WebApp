package com.radz.webapp.service;

import com.radz.webapp.db.dao.CategoryDAO;
import com.radz.webapp.db.entity.Category;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;

public class CategoryService {

    private static CategoryService instance;
    private static CategoryDAO categoryDAO;


    public static synchronized CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }


    private CategoryService() {
        categoryDAO = CategoryDAO.getInstance();
    }


    public Category getCategoryByName(String categoryName) {
        Connection con = null;
        Category category = null;
        try {
            con = DBManager.getInstance().getConnection();
            category = categoryDAO.findCategoryByName(con, categoryName);
        } finally {
            DBManager.close(con);
        }
        return category;
    }


}

