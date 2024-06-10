/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Book;
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
public class DAOBook extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOBook.class.getName());

    public Vector<Book> getAllBook(String sql) {
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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
            while (rs.next()) {
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

    public Vector<Book> getBookCat(String catID) {
        String sql = "SELECT Book.BookID, Book.BookImg, Book.Name, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                + "FROM Book \n"
                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                + "WHERE Book.CategoryID ='" + catID + "' ;";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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

    public Vector<Book> getBookCate(String catID, String sql) {
//        String sql = "SELECT Book.BookID, Book.BookImg, Book.Name, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
//                + "FROM Book \n"
//                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
//                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
//                + "WHERE Book.CategoryID ='" + catID + "' ;";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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

    public Vector<Book> getCat(String BookID, String sql) {
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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

    public Vector<Book> searchName(String bName) {
        Vector<Book> vector = new Vector<Book>();
        String sql = "select * from Book where Name like '%" + bName + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
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
}
