/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Account;
import entity.Book;
import entity.Orders;
import entity.Roles;
import java.sql.PreparedStatement;
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
public class DAOAdmin extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOAdmin.class.getName());

    public Vector<Book> getMax() {
        String sql = "SELECT BookID, Name, Purchases\n"
                + "FROM Book\n"
                + "WHERE Purchases = (SELECT MAX(Purchases) FROM Book);";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bookID = rs.getString(1);
                String name = rs.getString(2);
                int purchases = rs.getInt(3);
                Book bk = new Book(bookID, name, purchases);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Book> getMin() {
        String sql = "SELECT BookID, Name, Purchases\n"
                + "FROM Book\n"
                + "WHERE Purchases = (SELECT MIN(Purchases) FROM Book);";
        Vector<Book> vector = new Vector<Book>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bookID = rs.getString(1);
                String name = rs.getString(2);
                int purchases = rs.getInt(3);
                Book bk = new Book(bookID, name, purchases);
                vector.add(bk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int updateRole(String RoleID, int ID) {
        int n = 0;
        String sql = "UPDATE Account \n"
                + "SET RoleID = '" + RoleID + "'\n"
                + "WHERE UserID = '" + ID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateStatus(String status, int ID) {
        int n = 0;
        String sql = "UPDATE Account \n"
                + "SET Status = N'" + status + "'\n"
                + "WHERE UserID = '" + ID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Account> getAcc(int UserID) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "WHERE Account.UserID = '" + UserID + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getRole(String RoleID) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "WHERE Account.RoleID = '" + RoleID + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> search(int start, int end, String search) {
        Vector<Account> vector = new Vector<Account>();
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        Account.UserID, \n"
                + "        Roles.RoleName, \n"
                + "        Account.FirstName, \n"
                + "        Account.LastName, \n"
                + "        Account.Email, \n"
                + "        Account.Password, \n"
                + "        Account.PhoneNo, \n"
                + "        Account.Address, \n"
                + "        Account.Gender, \n"
                + "        Account.DOB, \n"
                + "        Account.imgUser, \n"
                + "        Account.Status,\n"
                + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                + "    FROM \n"
                + "        Account\n"
                + "    INNER JOIN \n"
                + "        Roles \n"
                + "    ON \n"
                + "        Account.RoleID = Roles.RoleID\n"
                + "    WHERE \n"
                + "        Account.FirstName LIKE '%" + search + "%'\n"
                + "        OR Account.LastName LIKE '%" + search + "%'\n"
                + "        OR Account.Email LIKE '%" + search + "%'\n"
                + "        OR Account.PhoneNo LIKE '%" + search + "%'\n"
                + ") AS NumberedRoles\n"
                + "WHERE \n"
                + "    RowNum BETWEEN '" + start + "' AND '" + end + "';";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> search(String search) {
        Vector<Account> vector = new Vector<Account>();
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        Account.UserID, \n"
                + "        Roles.RoleName, \n"
                + "        Account.FirstName, \n"
                + "        Account.LastName, \n"
                + "        Account.Email, \n"
                + "        Account.Password, \n"
                + "        Account.PhoneNo, \n"
                + "        Account.Address, \n"
                + "        Account.Gender, \n"
                + "        Account.DOB, \n"
                + "        Account.imgUser, \n"
                + "        Account.Status,\n"
                + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                + "    FROM \n"
                + "        Account\n"
                + "    INNER JOIN \n"
                + "        Roles \n"
                + "    ON \n"
                + "        Account.RoleID = Roles.RoleID\n"
                + "    WHERE \n"
                + "        Account.FirstName LIKE '%" + search + "%'\n"
                + "        OR Account.LastName LIKE '%" + search + "%'\n"
                + "        OR Account.Email LIKE '%" + search + "%'\n"
                + "        OR Account.PhoneNo LIKE '%" + search + "%'\n"
                + ") AS NumberedRoles\n";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getAllOrder() {
        String sql = "Select * from Orders";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                Orders or = new Orders(orderID, orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getNewestOrder(int userId) {
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT Orders.ID, Orders.OrderDate, Orders.OrderState, ROW_NUMBER() OVER (ORDER BY Orders.OrderDate DESC) AS RowNum\n"
                + "    FROM Orders\n"
                + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID"
                + "    WHERE OrderDetail.UserID = '" + userId + "'\n"
                + "    GROUP BY Orders.ID, Orders.OrderDate, Orders.OrderState\n"
                + ") AS SUBQuery\n"
                + "WHERE RowNum BETWEEN 1 AND 5;";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                Orders or = new Orders(id, orderDate, orderState);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getMostBuy() {
        String sql = "SELECT TOP 5 UserID, COUNT(*)\n"
                + "FROM Orders\n"
                + "GROUP BY UserID\n"
                + "HAVING COUNT(*) > 1";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                int count = rs.getInt(5);
                Orders or = new Orders(orderID, orderDate, orderState, userID, count);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getAll(int start, int end) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "WHERE UserID BETWEEN " + start + " AND " + end + "";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getDashboard() {
        String sql = "SELECT Account.UserID, Orders.ID\n"
                + "FROM Orders\n"
                + "INNER JOIN Account ON Orders.UserID = Account.UserID\n"
                + "GROUP BY Account.UserID, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                int id = rs.getInt(2);
                Orders or = new Orders(userID, id);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getDashboardDetail(int UserID) {
        String sql = "SELECT Account.LastName, Orders.ID\n"
                + "FROM Orders\n"
                + "INNER JOIN Account ON Orders.UserID = Account.UserID\n"
                + "WHERE Orders.UserID = '" + UserID + "'\n"
                + "GROUP BY Account.LastName, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String lname = rs.getString(1);
                int id = rs.getInt(2);
                Orders or = new Orders(lname, id);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> sort(int start, int end, String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> filter(int start, int end, String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getAllFilter(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getAll() {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> get(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Roles> getAllRole() {
        String sql = "SELECT * FROM Roles";
        Vector<Roles> vector = new Vector<Roles>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String RoleID = rs.getString(1);
                String RoleName = rs.getString(2);
                Roles acc = new Roles(RoleID, RoleName);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int addAccount(Account acc) {
        int n = 0;
        String sql = "INSERT INTO Account (RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, DOB, Gender, Status, Created_Date)\n"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            // khi dung preparedStatement ta khong can convẻt boolean thanh 0 and 1
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, acc.getRoleID());
            pre.setString(2, acc.getFirstName());
            pre.setString(3, acc.getLastName());
            pre.setString(4, acc.getEmail());
            pre.setString(5, acc.getPassword());
            pre.setString(6, acc.getPhoneNo());
            pre.setString(7, acc.getAddress());
            pre.setString(8, acc.getDOB());
            pre.setBoolean(9, acc.isGender());
            pre.setString(10, acc.getStatus());
            pre.setString(11, acc.getRegisterDate());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateAcc(Account acc, int userID) {
        int n = 0;
        String sql = "UPDATE Account"
                + "     SET RoleID = ?\n"
                + "       , FirstName = ?\n"
                + "       , LastName = ?\n"
                + "       , Email = ?\n"
                + "       , Password = ?\n"
                + "       , PhoneNo = ?\n"
                + "       , Address = ?\n"
                + "       , Gender = ?\n"
                + "       , DOB = ?\n"
                + "       , imgUser = ?\n"
                + "     WHERE UserID = '" + userID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, acc.getRoleID());
            pre.setString(2, acc.getFirstName());
            pre.setString(3, acc.getLastName());
            pre.setString(4, acc.getEmail());
            pre.setString(5, acc.getPassword());
            pre.setString(6, acc.getPhoneNo());
            pre.setString(7, acc.getAddress());
            pre.setBoolean(8, acc.isGender());
            pre.setString(9, acc.getDOB());
            pre.setString(10, acc.getImgUser());
            pre.setInt(11, acc.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeAcc(int userID) {
        int n = 0;
        String sql = "DELETE FROM Account WHERE UserID = '" + userID + "';";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeAll() {
        int n = 0;
        String sql = "DELETE FROM Account";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
