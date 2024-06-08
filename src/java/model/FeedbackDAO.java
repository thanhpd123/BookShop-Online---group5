/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Feedback;
import entity.FeedbackDetail;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBConnect {

    public List<Feedback> getAllFeedbacks() {
        String sql = "SELECT \n"
                + "    Feedback.FeedbackID,\n"
                + "    Book.[Name] AS BookName,\n"
                + "    CONCAT(Customers.FirstName, ' ', Customers.LastName) AS UserName,\n"
                + "    Feedback.Rate,\n"
                + "    Feedback.FBDate,\n"
                + "    Feedback.FBContent\n"
                + "FROM \n"
                + "    Feedback\n"
                + "JOIN \n"
                + "    Book ON Feedback.BookID = Book.BookID\n"
                + "JOIN \n"
                + "    Customers ON Feedback.UserID = Customers.UserID;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Feedback> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt("FeedbackID"), rs.getNString("BookName"), rs.getNString("UserName"), rs.getNString("Rate"), rs.getDate("FBDate"), rs.getNString("FBContent")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Feedback> getFeedbackByFilter(String fullname, String bookname, String rate, String sortby) {
        String sql = "select f.FeedbackID, b.Name as BookName, CONCAT(c.FirstName, ' ', c.LastName) AS UserName,\n"
                + "    f.Rate,\n"
                + "    f.FBDate,\n"
                + "    f.FBContent from Feedback f\n"
                + "join Customers c on f.UserId = c.UserId\n"
                + "join Book b on b.BookID = f.BookId\n"
                + "where (c.FirstName + ' ' + c.LastName) like ?\n"
                + "AND b.Name like ?\n"
                + "AND f.Rate like ?\n";
        try {

            if (sortby != null && !sortby.isBlank()) {
                sql += " ORDER BY " + sortby + " ASC";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + fullname + "%");
            ps.setString(2, "%" + bookname + "%");
            ps.setString(3, "%" + rate + "%");

            ResultSet rs = ps.executeQuery();
            List<Feedback> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt("FeedbackID"), rs.getNString("BookName"), rs.getNString("UserName"), rs.getNString("Rate"), rs.getDate("FBDate"), rs.getNString("FBContent")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<FeedbackDetail> getFeedbackDetailbyID(int feedbackID) {
        String sql = "SELECT F.FeedbackID, F.BookID, F.Rate, F.FBDate, F.FBContent,CONCAT(C.FirstName, ' ', C.LastName) AS UserName, C.Email, C.PhoneNo, B.Name AS BookName \n"
                + "                       FROM Feedback F  \n"
                + "                       JOIN Customers C ON F.UserID = C.UserID  \n"
                + "                       JOIN Book B ON F.BookID = B.BookID  \n"
                + "                       WHERE F.FeedbackID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, feedbackID);
            ResultSet rs = ps.executeQuery();
            List<FeedbackDetail> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new FeedbackDetail(rs.getInt("FeedbackID"), rs.getString("BookID"), rs.getNString("Rate"), rs.getDate("FBDate"), rs.getNString("FBContent"), rs.getNString("UserName"), rs.getString("Email"), rs.getString("PhoneNo"), rs.getNString("BookName")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
