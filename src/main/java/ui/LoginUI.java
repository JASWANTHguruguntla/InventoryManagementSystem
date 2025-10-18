package ui;

import db.DBUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class LoginUI extends Application {

    @Override
    public void start(Stage stage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            if (validateLogin(user, pass)) {
                messageLabel.setText("Login successful!");
                stage.close();
                InventoryUI inventoryUI = new InventoryUI();
                inventoryUI.showInventory(new Stage());
            } else {
                messageLabel.setText("Invalid credentials!");
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        Scene scene = new Scene(grid, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Login - Inventory System");
        stage.show();
    }

    private boolean validateLogin(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = DBUtil.getConnection();
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) DBUtil.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}