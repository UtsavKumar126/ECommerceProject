package com.example.ecommerceproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPageController {

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
      String query = String.format("Select * from user where emailID = '%s' AND pass = '%s'", email.getText(),password.getText());
      ResultSet res=HelloApplication.connection.executeQuery(query);

      if(res.next()){
          HelloApplication.emailId= res.getString("emailID");
          String userType= res.getString("userType");
          if(userType.equals("seller"))
          {
              AnchorPane sellerpage= FXMLLoader.load((getClass().getResource("sellerPage.fxml")));
              HelloApplication.root.getChildren().add(sellerpage);
          }
          else
          {
              Alert alert=new Alert(Alert.AlertType.INFORMATION);
              alert.setHeaderText("Login");
              alert.setContentText("Logged in as Buyer "+HelloApplication.emailId);
              alert.showAndWait();
              ProductPage productPage=new ProductPage();

              Header header=new Header();
              AnchorPane productPane=new AnchorPane();
              productPane.getChildren().add(productPage.products());
              productPane.setLayoutX(125);
              productPane.setLayoutY(65);
              HelloApplication.root.getChildren().clear();
              HelloApplication.root.getChildren().addAll(header.root,productPane);

          }
      }
      else{
          Dialog<String> dialog=new Dialog<>();
          dialog.setTitle("Login");

          ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

          dialog.getDialogPane().getButtonTypes().add(type);
          dialog.setContentText("Login Failed, Please Check username/password and try again");
          dialog.showAndWait();
      }
    }
}
