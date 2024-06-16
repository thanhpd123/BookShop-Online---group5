/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.BookManage;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BookManageDAO extends DBConnect {

    public List<BookManage> getAllBookManage() {
        String sql = "select b.BookID, b.Name, b.AuthorID, b.Price, b.Quantity from Book b";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<BookManage> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new BookManage(rs.getString("BookID"), rs.getNString("Name"), rs.getInt("AuthorID"), rs.getInt("Price"), rs.getInt("Quantity")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BookManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkDuplicateRollNo(String rollNo) {
        String sql = "SELECT COUNT(*) FROM Book WHERE BookID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception ex) {
            Logger.getLogger(BookManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insertBook(String BookID, String BookImg, String Name, String PublisherName, int AuthorID, String Edition, String CategoryID, Date PublicationDate, int Quantity, int Price) {
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([BookID]\n"
                + "           ,[BookImg]\n"
                + "           ,[Name]\n"
                + "           ,[PublisherName]\n"
                + "           ,[AuthorID]\n"
                + "           ,[Edition]\n"
                + "           ,[CategoryID]\n"
                + "           ,[PublicationDate]\n"
                + "           ,[Quantity]\n"
                + "           ,[Price])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, BookID);
            ps.setString(2, BookImg);
            ps.setNString(3, Name);
            ps.setNString(4, PublisherName);
            ps.setInt(5, AuthorID);
            ps.setString(6, Edition);
            ps.setString(7, CategoryID);
            ps.setDate(8, PublicationDate);
            ps.setInt(9, Quantity);
            ps.setInt(10, Price);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BookManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteBook(String id) {

        String sql = "DELETE FROM [dbo].[Book]\n"
                + "      WHERE BookID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BookManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        BookManageDAO dao = new BookManageDAO();
        List<BookManage> list = dao.getAllBookManage();
        for (BookManage bookManage : list) {
            System.out.println(bookManage);
        }
    }
}
