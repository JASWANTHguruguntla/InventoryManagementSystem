package dao;

import db.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO {

    public static List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products");

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean addProduct(String name, int quantity, double price) {
        String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, quantity);
            ps.setDouble(3, price);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}