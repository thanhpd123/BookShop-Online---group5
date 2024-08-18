/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Ship {
    int UserID;
    int OrderID;
    String LastName;
    String BookName;
    int ID;
    String OrderDate;
    String OrderState;
    String Rname;
    String PHoneNo;
    String Address;
    int Quantity;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "Ship{" + "UserID=" + UserID + ", OrderID=" + OrderID + ", LastName=" + LastName + ", BookName=" + BookName + ", ID=" + ID + ", OrderDate=" + OrderDate + ", OrderState=" + OrderState + ", Rname=" + Rname + ", PHoneNo=" + PHoneNo + ", Address=" + Address + ", Quantity=" + Quantity + ", Price=" + Price + ", sum=" + sum + '}';
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getOrderState() {
        return OrderState;
    }

    public void setOrderState(String OrderState) {
        this.OrderState = OrderState;
    }

    public String getRname() {
        return Rname;
    }

    public void setRname(String Rname) {
        this.Rname = Rname;
    }

    public String getPHoneNo() {
        return PHoneNo;
    }

    public void setPHoneNo(String PHoneNo) {
        this.PHoneNo = PHoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    int Price;
    int sum;

    public Ship(int UserID, int OrderID, String LastName, String BookName, int ID, String OrderDate, String OrderState, String Rname, String PHoneNo, String Address, int Quantity, int Price, int sum) {
        this.UserID = UserID;
        this.OrderID = OrderID;
        this.LastName = LastName;
        this.BookName = BookName;
        this.ID = ID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.Rname = Rname;
        this.PHoneNo = PHoneNo;
        this.Address = Address;
        this.Quantity = Quantity;
        this.Price = Price;
        this.sum = sum;
    }

    
    
}
