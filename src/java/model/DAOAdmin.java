/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Account;
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

    public Vector<Account> getAcc(int UserID) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.RegisterDate\n"
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

    public Vector<Account> search(String search) {
        Vector<Account> vector = new Vector<Account>();
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "WHERE Account.FirstName LIKE '%" + search + "%'\n"
                + "OR Account.LastName LIKE '%" + search + "%'\n"
                + "OR Account.Email LIKE '%" + search + "%'\n"
                + "OR Account.PhoneNo LIKE '%" + search + "%'";
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

    public Vector<Account> getAll() {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
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
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status);
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
        String sql = "INSERT INTO Account (RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, Gender, DOB, imgUser)\n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
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
            pre.setBoolean(8, acc.isGender());
            pre.setString(9, acc.getDOB());
            pre.setString(10, acc.getImgUser());
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
