/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Book;
import entity.CartItem;
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
        String sql = "SELECT Book.BookID, Book.BookImg, Book.Name, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                + "FROM Book \n"
                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                + "WHERE BookID ='" + BookID + "' ;";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String bookID = rs.getString(1);
                String bookImg = rs.getString(2);
                String name = rs.getString(3);
                String publisherName = rs.getString(4);
                String authorID = rs.getString(5);
                String edition = rs.getString(6);
                String categoryID = rs.getString(7);
                String publicationDate = rs.getString(8);
                int quantity = rs.getInt(9);
                int price = rs.getInt(10);
                Book bk = new Book(bookID, bookImg, name, publisherName, authorID, edition, categoryID, publicationDate, quantity, price);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public Vector<CartItem> getAll(String sql) {
//        String sql = "SELECT Book.BookImg, CartItem.CartID, Book.Name, CartItem.Quantity, CartItem.Price\n"
//                    + "FROM CartItem\n"
//                    + "INNER JOIN Book ON CartItem.BookID = Book.BookID";
        Vector<CartItem> vector = new Vector<CartItem>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                String bookImg = rs.getString(1);
                int cartID = rs.getInt(2);
                String bookID = rs.getString(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                CartItem cart = new CartItem(bookImg, cartID, bookID, quantity, price);
                vector.add(cart);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int addToCart (CartItem cart) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CartItem]\n"
                + "           ([CartID]\n"
                + "           ,[BookID]\n"
                + "           ,[Quantity]\n"
                + "           ,[Price])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, cart.getCartID());
            pre.setString(2, cart.getBookID());
            pre.setInt(3, cart.getQuantity());
            pre.setInt(4, cart.getPrice());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public int removeCart(String cartID) {
        int n = 0;
        String sql = "delete from CartItem where CartID='" + cartID
                + "' AND ('" + cartID + "' not in ( select distinct CartID from Cart))";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }
}

