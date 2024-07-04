/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dung Dinh
 */
public class DAOOrders extends DBConnect {

    public Vector<Orders> getAll() {
        String sql = "Select * From Orders";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                Orders or = new Orders(orderID, orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrder(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                Orders or = new Orders(orderDate, orderState, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    Vector<Orders> getSuccess() {
        String sql = "SELECT * FROM Orders\n"
                + "WHERE OrderState = N'Đã giao hàng'";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                Orders or = new Orders(orderID, orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
