package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully!");
            return conn;
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}