/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Ship {
    private int ShipID, OrderID, UserID;

    public Ship() {
    }

    public Ship(int ShipID, int OrderID, int UserID) {
        this.ShipID = ShipID;
        this.OrderID = OrderID;
        this.UserID = UserID;
    }

    public int getShipID() {
        return ShipID;
    }

    public void setShipID(int ShipID) {
        this.ShipID = ShipID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "Ship{" + "ShipID=" + ShipID + ", OrderID=" + OrderID + ", UserID=" + UserID + '}';
    }
    
}
