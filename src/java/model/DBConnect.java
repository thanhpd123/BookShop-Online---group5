/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dung Dinh
 */
public class DBConnect {
    Connection con = null;

    public DBConnect(String url, String userName, String password) {
        //DriverManager: quan li drivers
        //Statement: quan li cac cau lenh
        //ResultSet: tap ket qua tra ve
        
        try {
            //Driver: ket noi voi database server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection: quan li cac ket noi
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=BookShop", "sa", "123456");
    }
    public Connection getConnection() {
        return con;
    }
    public ResultSet getData(String sql){
        ResultSet rs=null;
        Statement state;
        try {
            state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
           rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public static void main(String[] args) {
        new DBConnect();
    }
}
