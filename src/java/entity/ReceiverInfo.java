/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class ReceiverInfo {
    private String Name, PhoneNo, Address, Description;
    private int UserID, ID;
    
    public ReceiverInfo() {
        
    }
    
    public ReceiverInfo(String Name, String PhoneNo, String Address, int UserID) {
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.UserID = UserID;
    }
    
    public ReceiverInfo(String Name, String PhoneNo, String Address) {
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
    }
    
    public ReceiverInfo(int ID, String Name, String PhoneNo, String Address, String Description) {
        this.ID = ID;
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.Description = Description;
    }
    
    public ReceiverInfo(int ID, String Name, String PhoneNo, String Address, int UserID) {
        this.ID = ID;
        this.Name = Name;
        this.PhoneNo = PhoneNo;
        this.Address = Address;
        this.UserID = UserID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    
}
