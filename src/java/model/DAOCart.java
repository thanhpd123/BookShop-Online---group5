/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Account;
import entity.Book;
import entity.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Dung Dinh
 */
public class DAOCart extends DBConnect {
    private static final Logger LOG = Logger.getLogger(DAOCart.class.getName());
    public Vector<Book> getBook(String BookID) {
        String sql = "SELECT Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                + "FROM Book \n"
                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                + "WHERE BookID ='" + BookID + "' ;";
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
    
    public Vector<Cart> getAllCart() {
        String sql = "SELECT *\n"
                    + "FROM Cart";
        Vector<Cart> vector = new Vector<Cart>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int cartID = rs.getInt(1);
                int userID = rs.getInt(2);
                String bookID = rs.getString(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Cart cart = new Cart(cartID, userID, bookID, quantity, price);
                vector.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Cart> getAllCart(String sql) {
        Vector<Cart> vector = new Vector<Cart>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int cartID = rs.getInt(1);
                int userID = rs.getInt(2);
                String bookID = rs.getString(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Cart cart = new Cart(cartID, userID, bookID, quantity, price);
                vector.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Cart> getAll(String sql) {
//        String sql = "SELECT Book.BookImg, CartItem.CartID, Book.Name, CartItem.Quantity, CartItem.Price\n"
//                    + "FROM CartItem\n"
//                    + "INNER JOIN Book ON CartItem.BookID = Book.BookID";
        Vector<Cart> vector = new Vector<Cart>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String bookImg = rs.getString(1);
                int cartID = rs.getInt(2);
                int userID = rs.getInt(3);
                String bookID = rs.getString(4);
                int quantity = rs.getInt(5);
                int price = rs.getInt(6);
                Cart cart = new Cart( bookImg, cartID, userID, bookID, quantity, price);
                vector.add(cart);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addToCart (Cart cart) {
        int n = 0;
        String sql = "INSERT INTO Cart (UserID, BookID, Quantity, Price)\n" 
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setInt(1, cart.getCartID());
            pre.setInt(1, cart.getUserID());
            pre.setString(2, cart.getBookID());
            pre.setInt(3, cart.getQuantity());
            pre.setInt(4, cart.getPrice());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int removeCart(String bookID, int userID) {
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
    
    public int removeAll(int userID) {
        int n = 0;
        String sql = "DELETE FROM Cart WHERE UserID = '" + userID + "';";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }
    
    public Vector<Account> getAll(int UserID) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser\n"
                + "	FROM Account\n"
                + "	INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "     WHERE Account.UserID = '" + UserID + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                String dob = rs.getString(10);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String imgUser = rs.getString(11);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int updateQuantity(int cartID, int userID, String bookID, int quantity, int price) {
        int n = 0;
        String sql = "Update Cart\n"
                + "SET Quantity = ?\n"
                + "   ,BookID = ?\n"
                + "   ,UserID = ?\n"
                + "   ,Price = ?\n"
                + "WHERE CartID = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, quantity);
            pre.setInt(3, userID);
            pre.setString(2, bookID);
            pre.setInt(5, cartID);
            pre.setInt(4, price);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Cart> getCart(String bkID, int uID) {
        Vector<Cart> vector = new Vector<Cart>();
        String sql = "Select * from Cart\n"
                + "WHERE BookID = '" + bkID + "' AND UserID = '" + uID + "'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int cartID = rs.getInt(1);
                int userID = rs.getInt(2);
                String bookID = rs.getString(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Cart cart = new Cart (cartID, userID, bookID, quantity, price);
                vector.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}

