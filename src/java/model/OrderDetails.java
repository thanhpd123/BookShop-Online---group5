    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class OrderDetails {
    private int OrderID;
    private String BookImg;
    private String BookName;
    private int Quantity;
    private int Price;
    private String OrderDate;
    private String OrderState;
    private int UserID;

    public OrderDetails() {
    }

    public OrderDetails(int OrderID, String BookImg, String BookName, int Quantity, int Price, String OrderDate, String OrderState, int UserID) {
        this.OrderID = OrderID;
        this.BookImg = BookImg;
        this.BookName = BookName;
        this.Quantity = Quantity;
        this.Price = Price;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.UserID = UserID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getBookImg() {
        return BookImg;
    }

    public void setBookImg(String BookImg) {
        this.BookImg = BookImg;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}
