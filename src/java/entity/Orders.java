/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Orders {
    private int OrderID, UserID, count, Quantity, Price;
    private String OrderDate, OrderState;

    public Orders() {
    }

    public Orders(String OrderDate, String OrderState, int Quantity, int Price) {
        this.OrderDate = OrderDate;
        this.Quantity = Quantity;
        this.Price = Price;
        this.OrderState = OrderState;
    }
    
    public Orders(String OrderDate, String OrderState, int UserID, int Quantity, int Price) {
        this.OrderDate = OrderDate;
        this.UserID = UserID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.OrderState = OrderState;
    }

    public Orders(int OrderID, String OrderDate, String OrderState, int UserID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.UserID = UserID;
    }
    
    public Orders(String OrderDate, String OrderState, int UserID) {
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.UserID = UserID;
    }
    
    public Orders(int OrderID, String OrderDate, String OrderState, int UserID, int count) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.UserID = UserID;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrderID() {
        return OrderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getOrderState() {
        return OrderState;
    }

    public int getUserID() {
        return UserID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public void setOrderState(String OrderState) {
        this.OrderState = OrderState;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", OrderState=" + OrderState + ", UserID=" + UserID + '}';
    }
    
}
