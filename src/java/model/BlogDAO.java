/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Blog;
import entity.BlogComments;
import entity.BlogDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BlogDAO extends DBConnect {

    public List<Blog> getAllBlog() {
        String sql = "select b.BlogID, bd.BlogImg, bd.BlogAuthorImg, b.Title, b.AuthorName, b.CreatedDate from Blogs b join BlogDetails bd on b.BlogID=bd.BlogID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Blog> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("BlogID"), rs.getNString("BlogImg"), rs.getNString("BlogAuthorImg"), rs.getNString("Title"), rs.getNString("AuthorName"), rs.getDate("CreatedDate")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public BlogDetail getBlogDetailbyID(int blogID) {
        String sql = "SELECT b.BlogID, b.Title, bd.BlogImg, bd.BlogAuthorImg, b.AuthorName, \n"
                + "       bd.Content1 + bd.Content2 + bd.Content3 AS Content, \n"
                + "       b.CreatedDate, b.UserID\n"
                + "FROM Blogs b\n"
                + "JOIN BlogDetails bd ON b.BlogID = bd.BlogID\n"
                + "FULL JOIN BlogComments bc ON b.BlogID = bc.BlogID\n"
                + "WHERE b.BlogID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BlogDetail(rs.getInt("BlogID"), rs.getNString("Title"), rs.getNString("BlogImg"), rs.getNString("BlogAuthorImg"), rs.getNString("AuthorName"), rs.getNString("Content"), rs.getDate("CreatedDate"), rs.getInt("UserID"));
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertBlogComments(int blogID, int userID, String commentText) {
        String sql = "INSERT INTO BlogComments VALUES (?,?,GETDATE(),?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogID);
            ps.setInt(2, userID);
            ps.setString(3, commentText);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<BlogComments> getAllCommentsByBlog(int BlogID) {
        Vector<BlogComments> vector = new Vector<>();
        String sql = "select b.BlogID, CONCAT(a.FirstName,' ',a.LastName) as UserFullName,bc.CommentDate,bc.CommentText,a.imgUser from BlogComments bc\n"
                + "join Blogs b on b.BlogID = bc.BlogID\n"
                + "join Account a on a.UserID = bc.UserID\n"
                + "where b.BlogID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, BlogID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vector.add(new BlogComments(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int getTotalBlog() {
        String sql = "select count(*) from Blogs";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Blog> pagingBlogs(int numberPaging) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT b.BlogID, bd.BlogImg, bd.BlogAuthorImg, b.Title, b.AuthorName, b.CreatedDate\n"
                + "FROM Blogs b\n"
                + "JOIN BlogDetails bd ON b.BlogID = bd.BlogID \n"
                + "ORDER BY CreatedDate DESC\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (numberPaging - 1) * 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("BlogID"), rs.getNString("BlogImg"), rs.getNString("BlogAuthorImg"), rs.getNString("Title"), rs.getNString("AuthorName"), rs.getDate("CreatedDate")));
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Blog> getRecentsBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT b.BlogID, bd.BlogImg, bd.BlogAuthorImg, b.Title, b.AuthorName, b.CreatedDate\n"
                + "FROM Blogs b\n"
                + "JOIN BlogDetails bd ON b.BlogID = bd.BlogID\n"
                + "ORDER BY CreatedDate DESC\n"
                + "OFFSET 0 ROWS\n"
                + "FETCH NEXT 5 ROWS ONLY";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("BlogID"), rs.getNString("BlogImg"), rs.getNString("BlogAuthorImg"), rs.getNString("Title"), rs.getNString("AuthorName"), rs.getDate("CreatedDate")));
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
        List<Blog> list = dao.pagingBlogs(2);
        for (Blog o : list) {
            System.out.println(o);
        }
    }
}
