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
        String sql = "select * from Book";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<BookManage> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new BookManage(rs.getNString(1), rs.getNString(2), rs.getNString(3), rs.getNString(4), rs.getNString(5), rs.getInt(6), rs.getNString(7), rs.getString(8), rs.getDate(9), rs.getInt(10), rs.getInt(11), rs.getFloat(12), rs.getBoolean(13), rs.getString(14)));
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

    public void insertBook(String bookID, String bookImg, String name, String description, String publisherName, int authorID, String edition, String categoryID, Date publicationDate, int quantity, int price, float salePrice, boolean flag, String status) {
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([BookID]\n"
                + "           ,[BookImg]\n"
                + "           ,[Name]\n"
                + "           ,[Description]\n"
                + "           ,[PublisherName]\n"
                + "           ,[AuthorID]\n"
                + "           ,[Edition]\n"
                + "           ,[CategoryID]\n"
                + "           ,[PublicationDate]\n"
                + "           ,[Quantity]\n"
                + "           ,[Price]\n"
                + "           ,[SalePrice]\n"
                + "           ,[Flag]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bookID);
            ps.setString(2, bookImg);
            ps.setNString(3, name);
            ps.setNString(4, description);
            ps.setNString(5, publisherName);
            ps.setInt(6, authorID);
            ps.setString(7, edition);
            ps.setString(8, categoryID);
            ps.setDate(9, publicationDate);
            ps.setInt(10, quantity);
            ps.setInt(11, price);
            ps.setFloat(12, salePrice);
            ps.setBoolean(13, flag);
            ps.setString(14, status);
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

    public void updateBook(String bookID, int price) {
        String sql = "update Book\n"
                + "set Price = ?\n"
                + "where BookID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bookID);
            ps.setInt(2, price);
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
