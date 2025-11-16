package com.smarthabitbuilder.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL = "jdbc:sqlite:smart_habits.db";

    // Load SQLite driver once
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.err.println("❌ Failed to load SQLite driver: " + e.getMessage());
        }
    }

    // Get connection safely
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.err.println("❌ Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
