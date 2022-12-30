package com.example.ecommerceproject;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerPageController {
    @FXML
    TextField name,price,sellerID;

    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {

        int productID=1;
        ResultSet response1= HelloApplication.connection.executeQuery("Select max(productId) as productId from product;");
        if(response1.next()){
            productID =response1.getInt("productId")+1;
        }
        String query= String.format("Insert Into product values (%s,'%s',%s,'%s')",productID,name.getText(),price.getText(),sellerID.getText());
        int response = HelloApplication.connection.executeUpdate(query);
        if(response>0){
            System.out.println("New Product is Added");
        }
    }
}
