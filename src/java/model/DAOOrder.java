/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import entity.MyOrders;
import java.sql.Connection;
import entity.Account;
import entity.Book;
import entity.OrderDetail;
import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOOrder.class.getName());
    protected Connection connection;
    public Vector<Orders> getAll(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                //String ssn=rs.getString("SSN");
                String OrderDate = rs.getString(2);
                // String name=rs.getString(2);
                String OrderState = rs.getString(3);

                int UserID = rs.getInt(4);

                Orders ord = new Orders(orderID, OrderDate, OrderState,
                        UserID);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<OrderDetail> getAllOrder(String sql) {
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                //String ssn=rs.getString("SSN");
                String BookID = rs.getString(2);
                int Quantity = rs.getInt(3);
                int Price = rs.getInt(4);

                OrderDetail ord = new OrderDetail(orderID, BookID, Quantity,
                        Price);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Orders> searchId(String id) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            String sql = "select * from Orders where OrderId like '%" + id + "%'";
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                String OrderDate = rs.getString(2);
                String OrderState = rs.getString(3);
                int UserID = rs.getInt(4);
                Orders order = new Orders(OrderID, OrderDate, OrderState, UserID);
                vector.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vector;
    }

    public Vector<Book> getAllBook(String sql) {
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bookID = rs.getString(1);
                String bookImg = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                String publisherName = rs.getString(5);
                String authorID = rs.getString(6);
                String edition = rs.getString(7);
                String categoryID = rs.getString(8);
                String publicationDate = rs.getString(9);
                int quantity = rs.getInt(10);
                int price = rs.getInt(11);
                Book bk = new Book(bookID, bookImg, name, description, publisherName, authorID, edition, categoryID, publicationDate, quantity, price);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getAllAcc(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                String email = rs.getString(5);
                String pass = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                String dob = rs.getString(9);
                boolean gender = rs.getBoolean(10);
                String imgUser = rs.getString(11);
                Account acc = new Account(userID, roleID, fname, lname, email, pass, phoneNo, address, dob, gender, imgUser);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void updateState(String orderState, int id, String sql) {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, orderState);
        stmt.setInt(2, id);

        int rowsUpdated = stmt.executeUpdate();
        
        if (rowsUpdated > 0) {
            // Update successful
        }
    } catch (SQLException e) {
        // Handle exceptions
    }
    }
}
