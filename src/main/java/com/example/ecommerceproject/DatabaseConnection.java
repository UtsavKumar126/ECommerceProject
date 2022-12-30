package com.example.ecommerceproject;
import java.sql.*;

public class DatabaseConnection {
    Connection con=null;
    String SQLURL ="jdbc:mysql://localhost:3306/ecommercePro?useSSL=false";
    String userName="root";
    String password="utsav123@#";
    DatabaseConnection() throws SQLException {

        con= DriverManager.getConnection(SQLURL,userName,password);
        if(con!=null){
            System.out.println("Yes");
        }
    }
    public ResultSet executeQuery(String query) throws SQLException {
        ResultSet result=null;
        try {
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            return result;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String query){

        int row=0;
        try {
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
      return row;
    }
}
