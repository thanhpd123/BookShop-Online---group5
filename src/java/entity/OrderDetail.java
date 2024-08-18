/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class OrderDetail {

    private String BookID, Payment, BookImg, Name, AuthorName, CategoryName;
    private int OrderID, Quantity, Price, UserID;

    public OrderDetail() {
    }

    public OrderDetail(String Name, String BookImg, int Quantity, int Price, String Payment, int UserID) {
        this.Payment = Payment;
        this.Name = Name;
        this.BookImg = BookImg;
        this.Quantity = Quantity;
        this.Price = Price;
        this.UserID = UserID;
    }
    
    
    public OrderDetail(int OrderID, String BookID, int Quantity, int Price, String Payment) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Payment = Payment;
    }
    
    public OrderDetail(int OrderID, String BookID, int Quantity, int Price, String Payment, int UserID) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.UserID = UserID;
        this.Payment = Payment;
    }

    public OrderDetail(String BookImg, String Name, String AuthorName, String CategoryName, int Quantity, int Price) {
        this.BookImg = BookImg;
        this.Name = Name;
        this.AuthorName = AuthorName;
        this.CategoryName = CategoryName;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    
    
    public OrderDetail(int OrderID, String BookID, int Quantity, int Price) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public String getBookImg() {
        return BookImg;
    }

    public void setBookImg(String BookImg) {
        this.BookImg = BookImg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }
    

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
    }

    public int getOrderID() {
        return OrderID;
    }

    public String getBookID() {
        return BookID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "OrderID=" + OrderID + ", BookID=" + BookID + ", Quantity=" + Quantity + ", Price=" + Price + '}';
    }

}
