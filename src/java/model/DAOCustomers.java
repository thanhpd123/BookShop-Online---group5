/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Book;
import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOCustomers extends DBConnect{
    private static final Logger LOG = Logger.getLogger(DAOCustomers.class.getName());
    public Vector<Account> getAll(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int UserID = rs.getInt(1);
                String RoleID = rs.getString(2);
                String FirstName = rs.getString(3);
                String LastName = rs.getString(4);
                String Email = rs.getString(5);
                String Password = rs.getString(6);
                String PhoneNo = rs.getString(7);
                String Address = rs.getString(8);
                String imgUser = rs.getString(9);
                int gen = rs.getInt(9);
                boolean sex = (gen == 1 ? true : false);
                String DOB = rs.getString(10);
                Account cus = new Account(UserID, RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, DOB, sex, imgUser);
                vector.add(cus);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public Vector<Account> searchName(String FirstName) {
        Vector<Account> vector = new Vector<Account>();
        String sql = "select * from Customers where FirstName like '%" + FirstName + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int UserID = rs.getInt(1);
                String RoleID = rs.getString(2);
                String Fname = rs.getString(3);
                String LastName = rs.getString(4);
                String Email = rs.getString(5);
                String Password = rs.getString(6);
                String PhoneNo = rs.getString(7);
                String Address = rs.getString(8);
                String imgUser = rs.getString(9);
                int gen = rs.getInt(9);
                boolean sex = (gen == 1 ? true : false);
                String DOB = rs.getString(10);
                Account cus = new Account(UserID, RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, DOB, sex, imgUser);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int updateCustomers(Account cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]\n"
                + "   SET [RoleID] = ?\n"
                + "      ,[FirstName] = ?\n"
                + "      ,[LastName] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Password] = ?\n"
                + "      ,[PhoneNo] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[DOB] = ?\n"
                + " WHERE [UserID] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(10, cus.getUserID());
            pre.setString(1, cus.getRoleID());
            pre.setString(2, cus.getFirstName());
            pre.setString(3, cus.getLastName());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getPassword());
            pre.setString(6, cus.getPhoneNo());
            pre.setString(7, cus.getAddress());
            pre.setBoolean(8, cus.isGender());
             pre.setString(9, cus.getDOB());
           
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
}
