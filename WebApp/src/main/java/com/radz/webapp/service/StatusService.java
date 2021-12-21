package com.radz.webapp.service;

import com.radz.webapp.db.dao.StatusDAO;
import com.radz.webapp.db.entity.Status;
import com.radz.webapp.db.utils.DBManager;

import java.sql.Connection;

public class StatusService {

    private static StatusService instance;
    private StatusDAO statusDAO;


    public static synchronized StatusService getInstance() {
        if (instance == null) {
            instance = new StatusService();
        }
        return instance;
    }

    private StatusService() {
        statusDAO = StatusDAO.getInstance();
    }


    public Status getStatusByName(String statusName) {
        Connection con = null;
        Status status = null;
        try {
            con = DBManager.getInstance().getConnection();

            status = statusDAO.findStatusByName(con, statusName);
        } finally {
            DBManager.close(con);
        }
        return status;
    }


    public Status getStatusById(long statusId) {
        Connection con = null;
        Status status = null;
        try {
            con = DBManager.getInstance().getConnection();

            status = statusDAO.findStatusById(con, statusId);
        } finally {
            DBManager.close(con);
        }
        return status;
    }
}
