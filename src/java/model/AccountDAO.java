package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entity.Account;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

public class AccountDAO extends DBConnect {

    protected Connection connection;
    private ResultSet rs;
    private PreparedStatement ps;

    public AccountDAO() {
        connection = new DBConnect().getConnection();
    }

    public Account verifyAccount(String email) {
        String sql = "SELECT TOP (1000) [UserID]\n"
                + "      ,[RoleID]\n"
                + "      ,[FirstName]\n"
                + "      ,[LastName]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[PhoneNo]\n"
                + "      ,[Address]\n"
                + "      ,[Gender]\n"
                + "      ,[DOB]\n"
                + "      ,[imgUser]\n"
                + "  FROM [BookShop].[dbo].[Account]\n"
                + "  where Email = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("UserID"),
                            rs.getString("RoleID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("PhoneNo"),
                            rs.getString("Address"),
                            rs.getString("DOB"),
                            rs.getBoolean("Gender"),
                            rs.getString("imgUser")
                    );
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }
    

    public ArrayList<Account> getEmailList() {
        ArrayList<Account> listEmail = new ArrayList<>();
        try {
            String sql = "SELECT Email, UserID FROM Account";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserID(rs.getInt("UserID"));
                acc.setEmail(rs.getString("Email"));
                listEmail.add(acc);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return listEmail;
    }

    public Account login(String email, String password) {
        String sql = "SELECT * FROM Account WHERE Email = ? AND Password = ?";
        try (Connection connection = new DBConnect().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("UserID"),
                            rs.getString("RoleID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("PhoneNo"),
                            rs.getString("Address"),
                            rs.getString("DOB"),
                            rs.getBoolean("Gender"),
                            rs.getString("imgUser"),
                            rs.getString("Status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean checkEmailExist(String inputEmail) {
        String sql = "select * from Account where Email = ?;";
        try (Connection connection = new DBConnect().getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, inputEmail);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void AddNewAccount(String fname, String lname, String email, String phone, String address, String pass, String gender, String date, String status, String rd) throws SQLException {
        Connection connection = null;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dob = LocalDate.parse(date, formatter);
        try {
            // Lấy kết nối từ DBContext
            connection = (Connection) new DBConnect().getConnection();
            String sql = "INSERT INTO Account (RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, Gender, DOB, Status, Created_Date) "
                    + "VALUES (3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, fname);
                ps.setString(2, lname);
                ps.setString(3, email);
                ps.setString(4, pass);
                ps.setString(5, phone);
                ps.setString(6, address);
                ps.setString(7, gender);
                ps.setString(8, date);
                ps.setString(9, status);
                ps.setString(10, rd);
                ps.executeUpdate();
                System.out.println("Account added successfully!");
            }
        } finally {
            // Đóng kết nối sau khi sử dụng
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean Changepass(String password, String email) {
        String updateSql = "UPDATE Account SET Password = ? WHERE Email = ?";

        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, password);
            ps.setString(2, email);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateProfilesImg(int UserID, String fname, String lname, String phone, String address, String gender, String Dob, String img) {
        String updateSql = "UPDATE Account SET FirstName = ?, LastName = ?, PhoneNo = ?, Address = ?, Gender = ?, DOB = ?, imgUser = ? \n"
                + " WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, Dob);
            ps.setString(7, img);
            ps.setInt(8, UserID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateProfiles(int UserID, String fname, String lname, String phone, String address, String gender, String Dob) {
        String updateSql = "UPDATE Account SET FirstName = ?, LastName = ?, PhoneNo = ?, Address = ?, Gender = ?, DOB = ? \n"
                + " WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(updateSql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, Dob);
            ps.setInt(7, UserID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Account loadAccount(String inputEmail) {
        String sql = "select * from Account c where c.Email = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, inputEmail);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("UserID"),
                            rs.getString("RoleID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("PhoneNo"),
                            rs.getString("Address"),
                            rs.getString("DOB"),
                            rs.getBoolean("Gender"),
                            rs.getString("imgUser")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account loadUserProfile(int inputUserID) {
        String sql = "select * from Account c where c.UserID = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, inputUserID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("UserID"),
                            rs.getString("RoleID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("PhoneNo"),
                            rs.getString("Address"),
                            rs.getString("DOB"),
                            rs.getBoolean("Gender"),
                            rs.getString("imgUser")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String[] getStaticsCustomerIn7Days(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse chuỗi ngày/tháng/năm sang LocalDate
        LocalDate fromDate = LocalDate.parse(from, formatter);
        // Mảng để lưu trữ 7 ngày
        String[] customersArray = new String[8];
        // Thiết lập ngày bắt đầu là ngày "from"
        LocalDate currentDate = fromDate;
        AccountDAO dao = new AccountDAO();
        // Lặp qua 7 ngày và thêm chúng vào mảng
        for (int i = 0; i < 8; i++) {
            // Chuyển LocalDate thành chuỗi và thêm vào mảng
            int total = dao.getStaticsCustomerInDay(currentDate.toString());
            customersArray[i] = Integer.toString(total);
            // Tăng ngày hiện tại lên 1 để di chuyển đến ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }
        return customersArray;
    }

    public int getStaticsCustomerInDay(String day) {
        int total = 0;
        String sql = "Select  count (UserID) as total from Account "
                + "where Created_Date = ? and roleID='3'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, day);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public int getTotalAccount(String from, String to) {
        int total = 0;
        String sql = "Select  count (UserID) as total from Account where Created_Date  between ? and ? and roleID='3';";
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

    public String[] getStaticsPostIn7Days(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse chuỗi ngày/tháng/năm sang LocalDate
        LocalDate fromDate = LocalDate.parse(from, formatter);
        // Mảng để lưu trữ 7 ngày
        String[] customersArray = new String[8];
        // Thiết lập ngày bắt đầu là ngày "from"
        LocalDate currentDate = fromDate;
        AccountDAO dao = new AccountDAO();
        // Lặp qua 7 ngày và thêm chúng vào mảng
        for (int i = 0; i < 8; i++) {
            // Chuyển LocalDate thành chuỗi và thêm vào mảng
            int total = dao.getStaticsPostsInDay(currentDate.toString());
            customersArray[i] = Integer.toString(total);
            // Tăng ngày hiện tại lên 1 để di chuyển đến ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }
        return customersArray;
    }

    public int getStaticsPostsInDay(String day) {
        int total = 0;
        String sql = "Select  count (BlogID) as total from Blogs "
                + "where CreatedDate = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, day);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public String[] getStaticsProductIn7Days(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Parse chuỗi ngày/tháng/năm sang LocalDate
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        // Mảng để lưu trữ 7 ngày
        String[] customersArray = new String[8];
        // Thiết lập ngày bắt đầu là ngày "from"
        LocalDate currentDate = fromDate;
        AccountDAO dao = new AccountDAO();
        // Lặp qua 7 ngày và thêm chúng vào mảng
        for (int i = 0; i < 8; i++) {
            // Chuyển LocalDate thành chuỗi và thêm vào mảng
            int total = dao.getStaticsProductInDay(currentDate.toString());
            customersArray[i] = Integer.toString(total);
            // Tăng ngày hiện tại lên 1 để di chuyển đến ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }

        return customersArray;
    }

    public int getStaticsProductInDay(String day) {
        int total = 0;
        String sql = "Select count (BookID) as total from Book "
                + "where PublicationDate = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, day);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }

    public String[] getStaticsFeedbackIn7Days(String from, String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Parse chuỗi ngày/tháng/năm sang LocalDate
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);
        // Mảng để lưu trữ 7 ngày
        String[] customersArray = new String[8];
        // Thiết lập ngày bắt đầu là ngày "from"
        LocalDate currentDate = fromDate;
        AccountDAO dao = new AccountDAO();
        // Lặp qua 7 ngày và thêm chúng vào mảng
        for (int i = 0; i < 8; i++) {
            // Chuyển LocalDate thành chuỗi và thêm vào mảng
            int total = dao.getStaticsFeedbackInDay(currentDate.toString());
            customersArray[i] = Integer.toString(total);
            // Tăng ngày hiện tại lên 1 để di chuyển đến ngày tiếp theo
            currentDate = currentDate.plusDays(1);
        }

        return customersArray;
    }

    public int getStaticsFeedbackInDay(String day) {
        int total = 0;
        String sql = "Select count (FeedbackID) as total from Feedback "
                + "where FBDate = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, day);
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
        AccountDAO dao = new AccountDAO();
//        System.out.println(dao.verifyAccount("customer1@gmail.com"));
//          System.out.println(dao.checkEmailExist("customer1@gmail.com"));
          dao.Changepass("1234567", "nhoanga421dqh@gmail.com");
//        dao.Changepass("12345", "customer1@gmail.com");
//        System.out.println(dao.loadAccount("customer1@gmail.com"));
//        System.out.println(dao.loadUserProfile(1));
//        dao.UpdateProfiles(1, "hoang", "hoang", "1234567", "0900000000", "Me So", "1", "1990-10-2");
//        System.out.println(dao.loadAccount("nghoanga421dqh@gmai.com"));
//        dao.Changepass("nghoanga421dqh@gmai.com", "12345556789");
//        System.out.println(dao.checkEmailExist("nhoanga421dqh@gmail.com"));
//    System.out.println(dao.login("customer1@gmail.com", "password1"));
//    try {
//        dao.AddNewAccount("Nguyen", "Viet Hoang", "nhoanga421dqh@gmail.com", "0900000000", "Mễ Sở, Văn Giang", "anhhoangdepzai", "1", "1990-01-01");
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
    }
}
