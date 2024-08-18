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

    public Vector<Book> getPaginatedBook(int start, int end) {
        String sql = "select Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                + "FROM Book \n"
                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                + "WHERE Book.BookID BETWEEN " + start + " AND " + end + "";
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

    public Vector<Book> getPaginatedBookCAT(int start, int end, String cat) {
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY BookID) AS RowNum\n"
                + "    FROM Book WHERE CategoryID = '" + cat + "'\n"
                + ") AS SubQuery\n"
                + "WHERE RowNum BETWEEN " + start + " AND " + end + ";";
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

    public Vector<Book> sortPriceASC(int start, int end) {
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY Price ASC) AS RowNum\n"
                + "    FROM Book\n"
                + ") AS SubQuery\n"
                + "WHERE RowNum BETWEEN " + start + " AND " + end + ";";
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

    public Vector<Book> getPurchases(String bookID) {
        String sql = "SELECT Purchases FROM Book WHERE BookID = '" + bookID + "'";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int purchases = rs.getInt(1);
                Book bk = new Book(purchases);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Book> getStatus(String bookID) {
        String sql = "SELECT Flag, Status FROM Book WHERE BookID = '" + bookID + "'";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String flag = rs.getString(1);
                String status = rs.getString(2);
                Book bk = new Book(flag, status);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Book> sortPriceDESC(int start, int end) {
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT *, ROW_NUMBER() OVER (ORDER BY Price DESC) AS RowNum\n"
                + "    FROM Book\n"
                + ") AS SubQuery\n"
                + "WHERE RowNum BETWEEN " + start + " AND " + end + ";";
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

    public Vector<Book> getBookCat(String catID) {
        String sql = "SELECT Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
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

    public Vector<Book> getBookCate(String catID, String sql) {
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

    public Vector<Book> getCat(String BookID, String sql) {
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

    public Vector<Book> searchName(int start, int end, String bName) {
        Vector<Book> vector = new Vector<Book>();
        String sql = "select * FROM ( \n"
                + "SELECT BookID, BookImg, Name, Description, PublisherName, AuthorID, Edition, CategoryID, PublicationDate, Quantity, Price, ROW_NUMBER() OVER (ORDER BY BookID ASC) AS RowNum\n"
                + "from Book where Name like '%" + bName + "%' ) AS SubQuery\n"
                + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "'";
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
}
