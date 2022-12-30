package com.example.ecommerceproject;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {

    void placeOrder(String productId) throws SQLException {
        ResultSet res=HelloApplication.connection.executeQuery("Select max(orderId) as orderId from orders");
        int orderId=1;
        if(res.next())
        {
            orderId=res.getInt("orderId") +1;
        }

        Timestamp date=new Timestamp(Calendar.getInstance().getTime().getTime());

        String query=String.format("Insert Into orders values(%s,%s,'%s','%s')",orderId,productId,HelloApplication.emailId,date);

        int response=HelloApplication.connection.executeUpdate(query);

        if(response >0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order");

            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your Order is Placed ");
            dialog.showAndWait();

        }
        else{
            System.out.println("Product Not added");
        }
    }
}
