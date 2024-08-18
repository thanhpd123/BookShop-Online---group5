/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static Constant.constant.RECORD_PER_PAGE;
import entity.BlogResponseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.DBConnect;
import java.sql.Date;

public class DAOBlogs extends DBConnect {

    protected Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;

    public DAOBlogs() {
        connection = new DBConnect().getConnection();
    }

    public ArrayList<BlogResponseDTO> getBlogList() {
        ArrayList<BlogResponseDTO> listBlog = new ArrayList<>();
        try {
            String sql = "Select \n"
                    + "  Bl.BlogID,\n"
                    + "  BD.BlogImg,\n"
                    + "  Bl.Title,\n"
                    + "  C.CategoryName,\n"
                    + "  A.AuthorName,\n"
                    + "  Bl.IsPublished,\n"
                    + "  BD.Content\n"
                    + "  from Blogs as Bl\n"
                    + "  Join Author as A on A.AuthorID = Bl.AuthorID\n"
                    + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                    + "  Join Book as B on B.AuthorID = A.AuthorID\n"
                    + "  Join Category as C on C.CategoryID = B.CategoryID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogResponseDTO blogrequest = new BlogResponseDTO();
                blogrequest.setBlogsID(rs.getInt("BlogID"));
                blogrequest.setBlogImg(rs.getString("BlogImg"));
                blogrequest.setTitle(rs.getString("Title"));
                blogrequest.setCategoryName(rs.getString("CategoryName"));
                blogrequest.setAuthorName(rs.getString("AuthorName"));
                blogrequest.setIsPublished(rs.getBoolean("IsPublished"));
                blogrequest.setContent(rs.getString("Content"));
                listBlog.add(blogrequest);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listBlog;
    }

    private void closeResultSetAndStatement(ResultSet rs, PreparedStatement ps) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println(e + "ham mypurchase");
            }
        }

    }

    public int findTotalRecord() {
        String sql = "select count(Bl.BlogID) from Blogs As Bl";
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return -1;
    }

    

    public int findTotalFilter(String Author, String status, String Search) {
        String msg = "";
        int check = 0;
        if (!Author.equals("-1") && (status.equals("1") || status.equals("0"))) {
            msg = "Where Bl.AuthorName = ? and Bl.IsPublished = ?";
            check = 1;
        } else if (!Search.isEmpty()) {
            msg = "Where Bl.Title like ?";
            check = 2;
        } else if (Author.equals("-1") && (status.equals("1") || status.equals("0"))) {
            msg = "Where Bl.IsPublished = ?";
            check = 3;
        } else if ((status.equals("1") || status.equals("0")) && Author.equals("-1")) {
            msg = "Where Bl.IsPublished = ?";
            check = 4;
        } else {
            msg = "";
        }
        String sql = "Select count(Bl.BlogID)\n"
                + " from Blogs As Bl \n"
                + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                + "  Join Account as A on A.UserID = Bl.UserID\n"
                + msg;
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            switch (check) {
                case 0:
                    break;
                case 1:
                    ps.setString(1, Author);
                    ps.setString(2, status);
                    break;
                case 2:
                    ps.setString(1, "%" + Search + "%");
                    break;
                case 3:
                    ps.setString(1, status);
                    break;
                case 4:
                    ps.setString(1, status);
                    break;
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return -1;
    }

    public List<BlogResponseDTO> findBlogByPage(int page) {
        List<BlogResponseDTO> listBlog = new ArrayList<>();
        String sql = "Select \n"
                + "  Bl.BlogID,\n"
                + "  BD.BlogImg,\n"
                + "  Bl.Title,\n"
                + "  Bl.AuthorName,\n"
                + "  Bl.IsPublished,\n"
                + "  BD.BriefInformation\n"
                + "  from Blogs Bl\n"
                + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                + "  Join Account as A on A.UserID = Bl.UserID\n"
                + "  ORDER BY Bl.BlogID asc\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY;";
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(2, RECORD_PER_PAGE);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String BlogImg = rs.getString(2);
                String Title = rs.getString(3);
                String AuthorName = rs.getString(4);
                boolean IsPublished = rs.getBoolean(5);
                String BriefInformation = rs.getString(6);
                BlogResponseDTO responseBlog = new BlogResponseDTO(id, BlogImg, Title, AuthorName, IsPublished, BriefInformation);
                listBlog.add(responseBlog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return listBlog;
    }

    public List<BlogResponseDTO> findBlogByPageFilter(int page, String Author, String status, String Search) {
        String msg = "";
        int check = 0;
        if (!Author.equals("-1") && (status.equals("1") || status.equals("0"))) {
            msg = "Where Bl.AuthorName = ? and Bl.IsPublished = ?";
            check = 1;
        } else if (!Search.isEmpty()) {
            msg = "Where Bl.Title like ?";
            check = 2;
        } else if (Author.equals("-1") && (status.equals("1") || status.equals("0"))) {
            msg = "Where Bl.IsPublished = ?";
            check = 3;
        } else if ((status.equals("1") || status.equals("0")) && Author.equals("-1")) {
            msg = "Where Bl.IsPublished = ?";
            check = 4;
        } else {
            msg = "";
        }
        List<BlogResponseDTO> listBlog = new ArrayList<>();
        String sql = "Select \n"
                + "  Bl.BlogID,\n"
                + "  BD.BlogImg,\n"
                + "  Bl.Title,\n"
                + "  Bl.AuthorName,\n"
                + "  Bl.IsPublished,\n"
                + "  BD.BriefInformation\n"
                + "  from Blogs Bl\n"
                + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                + "  Join Account as A on A.UserID = Bl.UserID\n"
                + msg
                + "  ORDER BY Bl.BlogID asc\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY;";
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            switch (check) {
                case 0:
                    break;
                case 1:
                    ps.setString(1, Author);
                    ps.setString(2, status);
                    ps.setInt(3, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(4, RECORD_PER_PAGE);
                    break;
                case 2:
                    ps.setString(1, "%" + Search + "%");
                    ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(3, RECORD_PER_PAGE);
                    break;
                case 3:
                    ps.setString(1, status);
                    ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(3, RECORD_PER_PAGE);
                    break;
                case 4:
                    ps.setString(1, status);
                    ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
                    ps.setInt(3, RECORD_PER_PAGE);
                    break;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String BlogImg = rs.getString(2);
                String Title = rs.getString(3);
                String AuthorName = rs.getString(4);
                boolean IsPublished = rs.getBoolean(5);
                String BriefInformation = rs.getString(6);
                BlogResponseDTO responseBlog = new BlogResponseDTO(id, BlogImg, Title, AuthorName, IsPublished, BriefInformation);
                listBlog.add(responseBlog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return listBlog;
    }

    public List<BlogResponseDTO> findBlogByPageASC(int page, String sort) {
        List<BlogResponseDTO> listBlog = new ArrayList<>();
        String sql = "Select \n"
                + "  Bl.BlogID,\n"
                + "  BD.BlogImg,\n"
                + "  Bl.Title,\n"
                + "  Bl.AuthorName,\n"
                + "  Bl.IsPublished,\n"
                + "  BD.BriefInformation\n"
                + "  from Blogs Bl\n"
                + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                + "  Join Account as A on A.UserID = Bl.UserID \n"
                + "  ORDER BY " + sort + " asc\n"
                + "  OFFSET ? ROWS\n"
                + "  FETCH NEXT ? ROWS ONLY;";
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(2, RECORD_PER_PAGE);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String BlogImg = rs.getString(2);
                String Title = rs.getString(3);
                String AuthorName = rs.getString(4);
                boolean IsPublished = rs.getBoolean(5);
                String BriefInformation = rs.getString(6);
                BlogResponseDTO responseBlog = new BlogResponseDTO(id, BlogImg, Title, AuthorName, IsPublished, BriefInformation);
                listBlog.add(responseBlog);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return listBlog;
    }
    
    public BlogResponseDTO findBlogById(int idBlog) {
        String sql = "Select \n"
                + "  Bl.BlogID,\n"
                + "  BD.BlogImg,\n"
                + "  Bl.Title,\n"
                + "  Bl.AuthorName,\n"
                + "  Bl.IsPublished,\n"
                + "  BD.Content1,\n"
                + "  BD.BriefInformation\n"
                + "  from Blogs as Bl\n"
                + "  Join BlogDetails as BD on BD.BlogID = Bl.BlogID\n"
                + "  Join Account as A on A.UserID = Bl.UserID\n"
                + "  Where Bl.BlogID = ?";
        BlogResponseDTO blogResponse = null;
        try (Connection connection = new DBConnect().con) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idBlog);
            rs = ps.executeQuery();
            if (rs.next()) {
                int BlogsID = rs.getInt(1);
                String BlogImg = rs.getString(2);
                String Title = rs.getString(3);
                String AuthorName = rs.getString(4);
                boolean IsPublished = rs.getBoolean(5);
                String Content = rs.getString(6);
                String BriefInformation = rs.getString(7);
                blogResponse = new BlogResponseDTO(BlogsID, BlogImg, Title, AuthorName, IsPublished, Content, BriefInformation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeResultSetAndStatement(rs, ps);
        }
        return blogResponse;
    
    }

    public boolean UpdateBlogImg(int blogid, String title, String brief, String content, String status, String fileimage, LocalDate today) {
        String updateSql = "Update Blogs set Title = ?, IsPublished = ?, LastModifiedDate = ? where BlogID = ?; "
                + "Update BlogDetails set BriefInformation = ?, Content1 = ?, BlogImg = ? where BlogID = ?;";
        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setDate(3, Date.valueOf(today));
            ps.setInt(4, blogid);
            ps.setString(5, brief);
            ps.setString(6, content);
            ps.setString(7, fileimage);
            ps.setInt(8, blogid);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean UpdateBlog(int blogid, String title, String brief, String content, String status, LocalDate today) {
        String updateSql = "Update Blogs set Title = ?, IsPublished = ?, LastModifiedDate = ? where BlogID = ?; "
                + "Update BlogDetails set BriefInformation = ?, Content1 = ? where BlogID = ?;";
        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, status);
            ps.setDate(3, Date.valueOf(today));
            ps.setInt(4, blogid);
            ps.setString(5, brief);
            ps.setString(6, content);
            ps.setInt(7, blogid);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

public int getTotalPost(String from, String to) {
        int total = 0;
        String sql = "SELECT COUNT(BlogID) AS total \n"
                + "FROM Blogs where CreatedDate BETWEEN ? and ?";
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
        DAOBlogs dao = new DAOBlogs();
        List<BlogResponseDTO> list = new ArrayList<>();
//        int num = dao.findTotalFilter("-1", "-1", "1", "");
//        System.out.println(num);
        System.out.println(dao.findBlogById(2));
//        list = dao.findBlogByPageASC(1, "Bl.Title");
//        for (BlogResponseDTO c : list) {
//            System.out.println(c);
//        }
    }
}
