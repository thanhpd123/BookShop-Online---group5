/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Feedback;
import entity.FeedbackDetail;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO extends DBConnect {

    protected Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;

    public FeedbackDAO() {
        connection = new DBConnect().getConnection();
    }

    public List<Feedback> getAllFeedbacks() {
        String sql = "SELECT \n"
                + "    Feedback.FeedbackID,\n"
                + "    Book.[Name] AS BookName,\n"
                + "    CONCAT(Account.FirstName, ' ', Account.LastName) AS UserName,\n"
                + "    Feedback.Rate,\n"
                + "    Feedback.FBDate,\n"
                + "    Feedback.FBContent\n"
                + "FROM \n"
                + "    Feedback\n"
                + "JOIN \n"
                + "    Book \n"
                + "    ON Feedback.BookID = Book.BookID\n"
                + "JOIN \n"
                + "    Account \n"
                + "    ON Feedback.UserID = Account.UserID;";
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
        String sql = "SELECT \n"
                + "    f.FeedbackID, \n"
                + "    b.Name AS BookName, \n"
                + "    CONCAT(c.FirstName, ' ', c.LastName) AS UserName,\n"
                + "    f.Rate,\n"
                + "    f.FBDate,\n"
                + "    f.FBContent \n"
                + "FROM \n"
                + "    Feedback f\n"
                + "JOIN \n"
                + "    Account c \n"
                + "    ON f.UserId = c.UserId\n"
                + "JOIN \n"
                + "    Book b \n"
                + "    ON b.BookID = f.BookId\n"
                + "WHERE \n"
                + "    CONCAT(c.FirstName, ' ', c.LastName) LIKE ?\n"
                + "    AND b.Name LIKE ?\n"
                + "    AND f.Rate LIKE ?";
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
        String sql = "	SELECT \n"
                + "    F.FeedbackID, \n"
                + "    F.BookID, \n"
                + "    F.Rate, \n"
                + "    F.FBDate, \n"
                + "    F.FBContent,\n"
                + "    CONCAT(C.FirstName, ' ', C.LastName) AS UserName, \n"
                + "    C.Email, \n"
                + "    C.PhoneNo, \n"
                + "    B.Name AS BookName \n"
                + "FROM \n"
                + "    Feedback F  \n"
                + "JOIN \n"
                + "    Account C \n"
                + "    ON F.UserID = C.UserID  \n"
                + "JOIN \n"
                + "    Book B \n"
                + "    ON F.BookID = B.BookID  \n"
                + "WHERE \n"
                + "    F.FeedbackID = ?;";
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

    public int getTotalFeedback(String from, String to) {
        int total = 0;
        String sql = "Select  count (FeedbackID) as total from Feedback where FBDate between ? and ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, from);
            st.setString(2, to);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public static void main(String[] args) {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getAllFeedbacks();
        for (Feedback o : list) {
            System.out.println(o);
        }
    }
}
