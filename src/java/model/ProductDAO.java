/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import entity.Account;
import entity.Categoty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import entity.Book;
import entity.Author;
import entity.BlogResponseDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProductDAO extends DBConnect {

    protected Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;

    public ProductDAO() {
        connection = new DBConnect().getConnection();
    }

    public ArrayList<Categoty> loadCategory() {
        ArrayList<Categoty> listCa = new ArrayList<>();
        String sql = "select * from Category";
        try {
            ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Categoty category = new Categoty();
                    category.setCategoryID(rs.getString("CategoryID"));
                    category.setCategoryName(rs.getString("CategoryName"));
                    listCa.add(category);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return listCa;
    }

    public ArrayList<BlogResponseDTO> loadAuthor() {
        ArrayList<BlogResponseDTO> listAu = new ArrayList<>();
        String sql = "SELECT DISTINCT [AuthorName]\n"
                + "FROM [BookShop].[dbo].[Blogs];";
        try {
            ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BlogResponseDTO author = new BlogResponseDTO();
                    author.setAuthorName(rs.getString("AuthorName"));
                    listAu.add(author);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return listAu;
    }

    public Book loadBook(String BookID) {
        String sql = "Select \n"
                + "  B.BookID,\n"
                + "  B.BookImg,\n"
                + "  B.Name,\n"
                + "  B.PublisherName,\n"
                + "  A.AuthorName,\n"
                + "  B.Edition,\n"
                + "  C.CategoryName,\n"
                + "  B.PublicationDate,\n"
                + "  B.Quantity,\n"
                + "  B.Price,\n"
                + "  B.SalePrice,\n"
                + "  B.Flag,\n"
                + "  B.Status\n"
                + "  From Book As B\n"
                + "  Join Category As C On C.CategoryID = B.CategoryID\n"
                + "  Join Author As A On A.AuthorID = B.AuthorID \n"
                + "  Where B.BookID = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, BookID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getString("BookID"),
                            rs.getString("BookImg"),
                            rs.getString("Name"),
                            rs.getString("PublisherName"),
                            rs.getString("AuthorName"),
                            rs.getString("Edition"),
                            rs.getString("CategoryName"),
                            rs.getString("PublicationDate"),
                            rs.getInt("Quantity"),
                            rs.getInt("Price"),
                            rs.getInt("SalePrice"),
                            rs.getString("Flag"),
                            rs.getString("Status")
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    
    public boolean UpdateProductImg(String bookID, String image, String title, String category, String author, int quantity, long listprice, long saleprice, String status, String flagtoturn, String publishername) {
        String updateSql = "UPDATE [BookShop].[dbo].[Book] \n"
                + "SET\n"
                + "  BookImg = ?,\n"
                + "  Name = ?,\n"
                + "  PublisherName = ?,\n"
                + "  AuthorID = ?,\n"
                + "  CategoryID = ?,\n"
                + "  Quantity = ?,\n"
                + "  Price = ?,\n"
                + "  SalePrice = ?,\n"
                + "  Flag = ?,\n"
                + "  Status = ?\n"
                + "WHERE BookID = ?;";

        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, image);
            ps.setString(2, title);
            ps.setString(3, publishername);
            ps.setString(4, author);
            ps.setString(5, category);
            ps.setInt(6, quantity);
            ps.setLong(7, listprice);
            ps.setLong(8, saleprice);
            ps.setString(9, flagtoturn);
            ps.setString(10, status);
            ps.setString(11, bookID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
public boolean UpdateProduct(String bookID, String title, String category, String author, int quantity, long listprice, long saleprice, String status, String flagtoturn, String publishername) {
        String updateSql = "UPDATE [BookShop].[dbo].[Book]\n"
                + "SET\n"
                + "  Name = ?,\n"
                + "  PublisherName = ?,\n"
                + "  AuthorID = ?,\n"
                + "  CategoryID = ?,\n"
                + "  Quantity = ?,\n"
                + "  Price = ?,\n"
                + "  SalePrice = ?,\n"
                + "  Flag = ?,\n"
                + "  Status = ?\n"
                + "WHERE BookID = ?;";

        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, title);
            ps.setString(2, publishername);
            ps.setString(3, author);
            ps.setString(4, category);
            ps.setInt(5, quantity);
            ps.setLong(6, listprice);
            ps.setLong(7, saleprice);
            ps.setString(8, flagtoturn);
            ps.setString(9, status);
            ps.setString(10, bookID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public String[] get7days(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        String[] daysArray = new String[8];
        // Thiết lập ngày bắt đầu là ngày "from"
        LocalDate currentDate = fromDate;
        // Lặp qua 7 ngày và thêm chúng vào mảng
        for (int i = 0; i < 8; i++) {
            // Chuyển LocalDate thành chuỗi và thêm vào mảng
            daysArray[i] = currentDate.format(outputFormatter);
            // Tăng ngày hiện tại lên 1 để di chuyển đến ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }
        return daysArray;
    }
public int getTotalProducts(String from, String to) {
        int total = 0;
        String sql = "Select count (BookID) as total from Book where PublicationDate between ? and ?";
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
public ArrayList<Author> loadAuthorProduct() {
        ArrayList<Author> listAu = new ArrayList<>();
        String sql = "select * from Author";
        try {
            ps = connection.prepareStatement(sql);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Author author = new Author();
                    author.setAuthorID(rs.getInt("AuthorID"));
                    author.setAuthorName(rs.getString("AuthorName"));
                    listAu.add(author);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return listAu;
    }


    public static void main(String[] args) {
        ProductDAO prodao = new ProductDAO();
//        System.out.println(prodao.loadBook("1"));
//        ArrayList<Categoty> list = new ArrayList<>();
//        list = prodao.loadCategory();
//        for (Categoty c : list) {
//            System.out.println(c.getCategoryName());
//        }

    }
}
