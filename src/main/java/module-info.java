module com.example.ecommerceproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ecommerceproject to javafx.fxml;
    exports com.example.ecommerceproject;
}