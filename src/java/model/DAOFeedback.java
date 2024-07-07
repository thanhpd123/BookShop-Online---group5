/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.EntityFeedback;
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
public class DAOFeedback extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOFeedback.class.getName());

    public Vector<EntityFeedback> getFeedback(String BID) {
        String sql = "SELECT Feedback.FeedbackID, Book.Name, Feedback.Rate, Account.LastName, Feedback.FBDate, Feedback.FBContent\n"
                + "	FROM Feedback\n"
                + "	INNER JOIN Book ON Feedback.BookID = Book.BookID\n"
                + "	INNER JOIN Account ON Feedback.UserID = Account.UserID\n"
                + "	WHERE Feedback.BookID = '" + BID + "';";
        Vector<EntityFeedback> vector = new Vector<EntityFeedback>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int fbID = rs.getInt(1);
                String bookID = rs.getString(2);
                int rate = rs.getInt(3);
                String IDuser = rs.getString(4);
                String fbDate = rs.getString(5);
                String fbContent = rs.getString(6);
                EntityFeedback fd = new EntityFeedback(fbID, bookID, rate, IDuser, fbDate, fbContent);
                vector.add(fd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<EntityFeedback> getFeedback(int start, int end, String BookID) {
        String sql = "SELECT *\n"
                + "FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY FBDate DESC) AS RowNum\n"
                + "FROM Feedback WHERE BookID = '" + BookID + "') AS SubQuery\n"
                + "WHERE RowNum BETWEEN " + start + " AND " + end + "";
        Vector<EntityFeedback> vector = new Vector<EntityFeedback>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int fbID = rs.getInt(1);
                String bookID = rs.getString(2);
                int rate = rs.getInt(3);
                String IDuser = rs.getString(4);
                String fbDate = rs.getString(5);
                String fbContent = rs.getString(6);
                EntityFeedback fd = new EntityFeedback(fbID, bookID, rate, IDuser, fbDate, fbContent);
                vector.add(fd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<EntityFeedback> getFeedback(String BookID, String sql) {
        Vector<EntityFeedback> vector = new Vector<EntityFeedback>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int fbID = rs.getInt(1);
                String bookID = rs.getString(2);
                int rate = rs.getInt(3);
                int userID = rs.getInt(4);
                String fbDate = rs.getString(5);
                String fbContent = rs.getString(6);
                EntityFeedback fd = new EntityFeedback(fbID, bookID, rate, userID, fbDate, fbContent);
                vector.add(fd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBook.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<EntityFeedback> getAllFeedback() {
//        String sql = "SELECT Feedback.FeedbackID, Book.Name, Feedback.Rate, Account.LastName, Feedback.FBDate, Feedback.FBContent\n"
        String sql = "SELECT Feedback.FeedbackID, Book.Name, Feedback.Rate, Feedback.UserID, Feedback.FBDate, Feedback.FBContent\n"
                + "               FROM Feedback\n"
                + "               INNER JOIN Book ON Feedback.BookID = Book.BookID\n"
                + "               INNER JOIN Account ON Feedback.UserID = Account.UserID\n";
        Vector<EntityFeedback> vector = new Vector<EntityFeedback>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int fbID = rs.getInt(1);
                String bookID = rs.getString(2);
                int rate = rs.getInt(3);
                int userID = rs.getInt(4);
                String fbDate = rs.getString(5);
                String fbContent = rs.getString(6);
                EntityFeedback fd = new EntityFeedback(fbID, bookID, rate, userID, fbDate, fbContent);
                vector.add(fd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOFeedback.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
