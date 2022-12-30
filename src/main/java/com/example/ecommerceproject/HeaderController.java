package com.example.ecommerceproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    public void initialize(){
        if(!HelloApplication.emailId.equals("")){
            Loginbutton.setOpacity(0);

            email.setText(HelloApplication.emailId);
        }
    }
    @FXML
    Button Loginbutton,Logoutbutton;
    @FXML
    Label email;
    @FXML
    TextField searchText;
    @FXML
    public void login(MouseEvent e)throws IOException{
        AnchorPane loginPage= FXMLLoader.load((getClass().getResource("loginPage.fxml")));
        HelloApplication.root.getChildren().add(loginPage);
    }

    @FXML
    public void search(MouseEvent e) throws IOException, SQLException {
        ProductPage productPage=new ProductPage();

        if(searchText.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Search");
            alert.setContentText("Please Enter product");
            alert.showAndWait();
        }
        Header header=new Header();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.productsbySearch(searchText.getText()));
        productPane.setLayoutX(125);
        productPane.setLayoutY(65);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }

    @FXML
    public void logoutappear(MouseEvent e){
        if(Loginbutton.getOpacity()==0){
            Logoutbutton.setOpacity(1);
        }
        else
        {
            Logoutbutton.setOpacity(0);
        }
    }
    @FXML

    public void logout(MouseEvent e) throws IOException {
        if(Logoutbutton.getOpacity()==1) {
            HelloApplication.emailId = "";
            Logoutbutton.setOpacity(0);
            Header header = new Header();
            HelloApplication.root.getChildren().add(header.root);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("logout");
            alert.setContentText("Logged out successfully");
            alert.showAndWait();
        }
    }
}
