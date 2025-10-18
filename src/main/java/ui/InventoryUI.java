package ui;

import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import dao.ProductDAO;
import java.util.List;

public class InventoryUI {
    private TableView<Product> table = new TableView<>();
    private ObservableList<Product> data = FXCollections.observableArrayList();
    
    private TextField nameField;
    private TextField qtyField;
    private TextField priceField;

    public void showInventory(Stage stage) {
        nameField = new TextField();
        nameField.setPromptText("Product Name");
        qtyField = new TextField();
        qtyField.setPromptText("Quantity");
        priceField = new TextField();
        priceField.setPromptText("Price");

        Button addButton = new Button("Add Product");
        addButton.setOnAction(e -> addProduct());

        Button viewDBButton = new Button("View Database");
        viewDBButton.setOnAction(e -> viewDatabaseContents());

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> loadProducts());

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> deleteSelectedProduct());

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.getColumns().addAll(idCol, nameCol, qtyCol, priceCol);
        table.setItems(data);
        loadProducts();

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, viewDBButton, refreshButton, deleteButton);

        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);

        root.add(nameField, 0, 0);
        root.add(qtyField, 1, 0);
        root.add(priceField, 2, 0);
        root.add(buttonBox, 3, 0);
        root.add(table, 0, 1, 4, 1);

        stage.setScene(new Scene(root, 700, 500));
        stage.setTitle("Inventory Dashboard");
        stage.show();
    }

    private void addProduct() {
        try {
            String name = nameField.getText();
            String qty = qtyField.getText();
            String price = priceField.getText();
            
            if (name.isEmpty() || qty.isEmpty() || price.isEmpty()) {
                showAlert("Please fill all fields");
                return;
            }

            System.out.println("DEBUG - Name: '" + name + "', Qty: '" + qty + "', Price: '" + price + "'");

            name = name.trim();
            qty = qty.trim();
            price = price.trim();

            if (!qty.matches("\\d+")) {
                showAlert("Please enter a valid number for quantity (only digits allowed)");
                return;
            }
            if (!price.matches("\\d+(\\.\\d+)?")) {
                showAlert("Please enter a valid number for price (e.g., 10 or 10.50)");
                return;
            }

            int quantity = Integer.parseInt(qty);
            double priceValue = Double.parseDouble(price);

            boolean success = ProductDAO.addProduct(name, quantity, priceValue);

            if (success) {
                System.out.println("✅ Product added successfully");
                loadProducts();
                clearInputFields();
                showSuccessAlert("Product '" + name + "' added successfully!");
            } else {
                showAlert("Failed to add product");
            }

        } catch (NumberFormatException e) {
            System.out.println("DEBUG: Number format exception - " + e.getMessage());
            showAlert("Please enter valid numbers for quantity and price");
        } catch (Exception e) {
            System.out.println("DEBUG: General exception: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error adding product: " + e.getMessage());
        }
    }

    private void viewDatabaseContents() {
        try {
            List<Product> products = ProductDAO.getAllProducts();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Database Contents");
            alert.setHeaderText("Products in Database (Total: " + products.size() + ")");
            
            StringBuilder content = new StringBuilder();
            if (products.isEmpty()) {
                content.append("No products in database");
            } else {
                content.append("Current products in database:\n\n");
                for (Product product : products) {
                    content.append(String.format("ID: %-3d | Name: %-15s | Qty: %-4d | Price: $%-8.2f\n", 
                        product.getId(), 
                        product.getName().length() > 15 ? product.getName().substring(0, 12) + "..." : product.getName(),
                        product.getQuantity(), 
                        product.getPrice()));
                }
                content.append("\nTotal products: ").append(products.size());
            }
            
            TextArea textArea = new TextArea(content.toString());
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setPrefSize(500, 300);
            
            alert.getDialogPane().setContent(textArea);
            alert.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error reading database: " + e.getMessage());
        }
    }

    private void deleteSelectedProduct() {
        Product selectedProduct = table.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showAlert("Please select a product to delete");
            return;
        }
        
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Product");
        confirmAlert.setContentText("Are you sure you want to delete '" + selectedProduct.getName() + "'?");
        
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    ProductDAO.deleteProduct(selectedProduct.getId());
                    showSuccessAlert("Product '" + selectedProduct.getName() + "' deleted successfully!");
                    loadProducts();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error deleting product: " + e.getMessage());
                }
            }
        });
    }

    private void loadProducts() {
        try {
            List<Product> products = ProductDAO.getAllProducts();
            data.setAll(products);
            System.out.println("Loaded " + products.size() + " products");
            
            if (!products.isEmpty()) {
                showSuccessAlert("Refreshed! Loaded " + products.size() + " products from database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error loading products: " + e.getMessage());
        }
    }

    private void clearInputFields() {
        nameField.clear();
        qtyField.clear();
        priceField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}