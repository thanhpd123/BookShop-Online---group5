/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Blog;
import entity.BlogDetail;
import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BlogDAO extends DBConnect {

    public List<Blog> getAllBlog() {
        String sql = "select b.BlogID, b.Title, a.AuthorName, b.CreatedDate, b.LastModifiedDate, b.IsPublished from Blogs b join Author a on b.AuthorID = a.AuthorID where IsPublished='1'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Blog> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Blog(rs.getInt("BlogID"), rs.getNString("Title"), rs.getNString("AuthorName"), rs.getDate("CreateDate"), rs.getDate("LastModifiedDate")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<BlogDetail> getBlogDetailbyID(int blogID) {
        String sql = "select b.BlogID, b.Title, b.AuthorName, b.Content, b.CreatedDate, b.UserID, bc.CommentDate, bc.CommentText \n"
                + "from Blogs b join BlogComments bc \n"
                + "on b.BlogID = bc.BlogID where b.BlogID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogID);
            ResultSet rs = ps.executeQuery();
            List<BlogDetail> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new BlogDetail(rs.getInt("BlogID"), rs.getNString("Title"), rs.getNString("AuthorName"), rs.getNString("Content"), rs.getDate("CreatedDate"), rs.getInt("UserID"), rs.getDate("CommentDate"), rs.getNString("CommentText")) );
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
