/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import entity.OrderDetail;
import java.sql.PreparedStatement;
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

    private static final Logger LOG = Logger.getLogger(DAOOrders.class.getName());

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

    public Vector<OrderDetail> getAllOrderDetail() {
        String sql = "Select * From OrderDetail";
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String bookID = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                OrderDetail or = new OrderDetail(orderID, bookID, quantity, price);
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

    public Vector<Orders> getOrderByUser(int UserID) {
        String sql = "SELECT Orders.OrderDate , Orders.OrderState, Orders.UserID, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE Orders.OrderState = N'Đã giao hàng' AND Orders.UserID = '" + UserID + "'\n"
                + "GROUP By Orders.OrderDate, Orders.OrderState, Orders.UserID\n"
                + "ORDER BY Orders.OrderDate";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Orders or = new Orders(orderDate, orderState, userID, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrder(int UserID, String start, String end) {
        String sql = "SELECT Orders.OrderDate , Orders.OrderState, Orders.UserID, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE Orders.OrderDate BETWEEN '" + start + "' AND '" + end + "' AND Orders.OrderState = N'Đã giao hàng' AND Orders.UserID = '" + UserID + "'\n"
                + "GROUP By Orders.OrderDate, Orders.OrderState, Orders.UserID\n"
                + "ORDER BY Orders.OrderDate";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Orders or = new Orders(orderDate, orderState, userID, quantity, price);
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

    public int addOrder(Orders or) {
        int n = 0;
        String sql = "INSERT INTO Orders (OrderDate, OrderState, UserID)\n"
                + "VALUES(?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, or.getOrderDate());
            pre.setString(2, or.getOrderState());
            pre.setInt(3, or.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addOrderDetail(OrderDetail or) {
        int n = 0;
        String sql = "INSERT INTO OrderDetail (OrderID, BookID, Quantity, Price)\n"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, or.getOrderID());
            pre.setString(2, or.getBookID());
            pre.setInt(3, or.getQuantity());
            pre.setInt(4, or.getPrice());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteCart(String bookID, int userID) {
        int n = 0;
        String sql = "DELETE FROM Cart WHERE BookID = '" + bookID + "' AND UserID = '" + userID + "';";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
