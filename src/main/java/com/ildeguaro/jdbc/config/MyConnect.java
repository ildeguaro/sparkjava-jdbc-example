package com.ildeguaro.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {
	private static String url = "jdbc:mysql://172.17.0.1:3306/ildeguaro";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "jcarlosmr";   
    private static String password = "tromandinna14";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }


}
