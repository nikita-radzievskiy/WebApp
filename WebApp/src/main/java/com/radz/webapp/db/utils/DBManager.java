package com.radz.webapp.db.utils;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class DBManager {


    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/task4db");
        } catch (NamingException ex) {
        }
    }

    private DataSource ds;


    public Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {

        }
        return con;
    }

    public static void close(AutoCloseable quality) {
        try {
            if (quality != null) {
                quality.close();
            }
        } catch (Exception e) {
        }
    }


}
