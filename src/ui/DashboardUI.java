package ui;
import dao.ProductDAO;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Product;

public class DashboardUI {
    public void start(Stage stage) {
        TableView<Product> table = new TableView<>();
        ObservableList<Product> data = FXCollections.observableArrayList(ProductDAO.getAllProducts());

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(p -> new javafx.beans.property.SimpleStringProperty(p.getValue().getName()));

        TableColumn<Product, Number> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(p -> new javafx.beans.property.SimpleIntegerProperty(p.getValue().getQuantity()));

        TableColumn<Product, Number> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(p -> new javafx.beans.property.SimpleDoubleProperty(p.getValue().getPrice()));

        table.getColumns().addAll(nameCol, qtyCol, priceCol);
        table.setItems(data);

        TextField nameField = new TextField(); nameField.setPromptText("Name");
        TextField qtyField = new TextField(); qtyField.setPromptText("Qty");
        TextField priceField = new TextField(); priceField.setPromptText("Price");
        Button addBtn = new Button("Add Product");

        addBtn.setOnAction(e -> {
            ProductDAO.addProduct(nameField.getText(),
                    Integer.parseInt(qtyField.getText()),
                    Double.parseDouble(priceField.getText()));
            table.setItems(FXCollections.observableArrayList(ProductDAO.getAllProducts()));
        });

        VBox root = new VBox(10, table, new HBox(10, nameField, qtyField, priceField, addBtn));
        root.setStyle("-fx-padding:15;");
        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("Inventory Dashboard");
        stage.show();
    }
}
