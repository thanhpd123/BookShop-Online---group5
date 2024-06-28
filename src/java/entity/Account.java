/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Account {
    private int UserID;
    private String RoleID, FirstName, LastName, Email, Password, PhoneNo, Address, DOB;
    private boolean Gender;
    private String imgUser;

    public Account() {
    }

    public Account(String RoleID, String FirstName, String LastName, String Email, String Password, String PhoneNo, String Address, String DOB, boolean Gender, String imgUser) {
        this.RoleID = RoleID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.DOB = DOB;
        this.Gender = Gender;
        this.imgUser = imgUser;
    }
       
    public Account(int UserID, String RoleID, String FirstName, String LastName, String Email, String Password, String PhoneNo, String Address, String DOB, boolean Gender, String imgUser) {
        this.UserID = UserID;
        this.RoleID = RoleID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.DOB = DOB;
        this.Gender = Gender;
        this.imgUser = imgUser;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    @Override
    public String toString() {
        return "Customers{" + "UserID=" + UserID + ", RoleID=" + RoleID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Email=" + Email + ", Password=" + Password + ", PhoneNo=" + PhoneNo + ", Address=" + Address + ", DOB=" + DOB + ", Gender=" + Gender + ", imgUser=" + imgUser + '}';
    }
}
