/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class OrderInfor {
    private int OrderID;
    private String BookName;
    private int Quantity;
    private int Price;
    private String Rname;
    private String Address;
    private String PhoneNo; 
    private String Payment;
    private String OrderState;
    private String BookImg;
    private int UserID;
    private int ID;

    public OrderInfor() {
    }

    public OrderInfor(int OrderID, String BookName, int Quantity, int Price, String Rname, String Address, String PhoneNo, String Payment, String OrderState, String BookImg, int UserID, int ID) {
        this.OrderID = OrderID;
        this.BookName = BookName;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Rname = Rname;
        this.Address = Address;
        this.PhoneNo = PhoneNo;
        this.Payment = Payment;
        this.OrderState = OrderState;
        this.BookImg = BookImg;
        this.UserID  = UserID;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }



    public String getPayment() {
        return Payment;
    }

    public String getBookImg() {
        return BookImg;
    }

    public void setBookImg(String BookImg) {
        this.BookImg = BookImg;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
    }



    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String BookName) {
        this.BookName = BookName;
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

    public String getRname() {
        return Rname;
    }

    public void setRname(String Rname) {
        this.Rname = Rname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getOrderState() {
        return OrderState;
    }

    public void setOrderState(String OrderState) {
        this.OrderState = OrderState;
    }

    @Override
    public String toString() {
        return "OrderInfor{" + "OrderID=" + OrderID + ", BookName=" + BookName + ", Quantity=" + Quantity + ", Price=" + Price + ", Rname=" + Rname + ", Address=" + Address + ", PhoneNo=" + PhoneNo + ", Payment=" + Payment + ", OrderState=" + OrderState + ", BookImg=" + BookImg + ", UserID=" + UserID + ", ID=" + ID + '}';
    }
    
}
