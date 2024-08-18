/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class MyOrder {
    private int OrderID;
    private String OrderDate;
    private String OrderState;
    private int ID;
    private int UserID;

    public MyOrder() {
    }

    public MyOrder(int OrderID, String OrderDate, String OrderState, int ID, int UserID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.ID = ID;
        this.UserID = UserID;
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

    public int getID() {
        return ID;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "MyOrder{" + "OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", OrderState=" + OrderState + ", ID=" + ID + ", UserID=" + UserID + '}';
    }
    
    
}

