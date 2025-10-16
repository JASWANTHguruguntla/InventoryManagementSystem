package ui;

import db.DBConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class InventoryUI extends Application {
    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Product Name");
        TextField qtyField = new TextField();
        qtyField.setPromptText("Quantity");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        Button addButton = new Button("Add Product");
        addButton.setOnAction(e -> addProduct(nameField.getText(), qtyField.getText(), priceField.getText()));

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameCol, qtyCol, priceCol);
        table.setItems(data);
        loadProducts();

        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);

        root.add(nameField, 0, 0);
        root.add(qtyField, 1, 0);
        root.add(priceField, 2, 0);
        root.add(addButton, 3, 0);
        root.add(table, 0, 1, 4, 1);

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Inventory Dashboard");
        stage.show();
    }

    private void addProduct(String name, String qty, String price) {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                System.err.println("⚠️ Database connection not available!");
                return;
            }

            String sql = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(qty));
            ps.setDouble(3, Double.parseDouble(price));
            ps.executeUpdate();
            ps.close(); // only close statement
            loadProducts(); // refresh table
            System.out.println("✅ Product added successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProducts() {
        try {
            Connection conn = DBConnection.getConnection();
            if (conn == null || conn.isClosed()) {
                System.err.println("⚠️ Database connection not available!");
                return;
            }

            String sql = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            table.getItems().clear();
            while (rs.next()) {
                Product product = new Product(
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );
                table.getItems().add(product);
            }

            rs.close();
            stmt.close(); // only close statement and result set
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Product {
        private String name;
        private int quantity;
        private double price;

        public Product(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public String getName() { return name; }
        public int getQuantity() { return quantity; }
        public double getPrice() { return price; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
