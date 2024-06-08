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
    private String BookID;
    private int OrderID, Quantity, Price;

    public OrderDetail() {
    }

    public OrderDetail(int OrderID, String BookID, int Quantity, int Price) {
        this.OrderID = OrderID;
        this.BookID = BookID;
        this.Quantity = Quantity;
        this.Price = Price;
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
