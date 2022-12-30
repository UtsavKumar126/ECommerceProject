package com.example.ecommerceproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductPage {
    ListView<HBox> products;
    ListView<HBox> productsbySearch(String search) throws SQLException {

        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("Select * from product");
        while(res.next()) {
            if (res.getString("Productname").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productID = new Label();
                Label price = new Label();
                Button buy = new Button();

                name.setMinWidth(50);
                productID.setMinWidth(50);
                price.setMinWidth(50);
                buy.setText("Buy");
                HBox productDetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Login");
                            alert.setContentText("Please Login First");
                            alert.showAndWait();
                        } else {
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Login");
                            alert.setContentText("Welcome to Ecommerce"+HelloApplication.emailId);
                            alert.showAndWait();

                            Order order = new Order();
                            try {
                                order.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                name.setText(res.getString("Productname"));
                price.setText(res.getString("price"));
                productID.setText(res.getString("productId"));
                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }

        products.setItems(productList);
        return products;
    }

    ListView<HBox> products() throws SQLException {

        products=new ListView<>();
        ObservableList<HBox> productList= FXCollections.observableArrayList();
        ResultSet res= HelloApplication.connection.executeQuery("Select * from product");
        while(res.next()) {
            Label name = new Label();
            Label productID = new Label();
            Label price = new Label();
            Button buy = new Button();

            name.setMinWidth(50);
            productID.setMinWidth(50);
            price.setMinWidth(50);
            buy.setText("Buy");
            HBox productDetails = new HBox();

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailId.equals("")){
                        Alert alert2=new Alert(Alert.AlertType.ERROR);
                        alert2.setHeaderText("Login");
                        alert2.setContentText("Please Login First");
                        alert2.showAndWait();
                    }
                    else
                    {

                      Order order=new Order();
                        try {
                            order.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            name.setText(res.getString("Productname"));
            price.setText(res.getString("price"));
            productID.setText(res.getString("productId"));
            productDetails.getChildren().addAll(productID, name, price, buy);
            productList.add(productDetails);
        }

      products.setItems(productList);
        return products;
    }
}
