package com.example.ecommerceproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Header extends Node {
    public AnchorPane root;
    Header() throws IOException {
        root= FXMLLoader.load(getClass().getResource("Header.fxml"));
    }
}
