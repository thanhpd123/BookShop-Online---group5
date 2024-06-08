/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Cart {
    private int CartID, UserID;
    private String CreatedDate;

    public Cart() {
    }

    public Cart(int CartID, int UserID, String CreatedDate) {
        this.CartID = CartID;
        this.UserID = UserID;
        this.CreatedDate = CreatedDate;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    @Override
    public String toString() {
        return "Cart{" + "CartID=" + CartID + ", UserID=" + UserID + ", CreatedDate=" + CreatedDate + '}';
    }
    
}
