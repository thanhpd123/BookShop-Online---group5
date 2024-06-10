/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class CartItem {
    private int CartID, Quantity, Price;
    private String BookID;

    public CartItem() {
    }

    public CartItem(int CartID, int Quantity, int Price, String BookID) {
        this.CartID = CartID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.BookID = BookID;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
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

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    @Override
    public String toString() {
        return "CartItem{" + "CartID=" + CartID + ", Quantity=" + Quantity + ", Price=" + Price + ", BookID=" + BookID + '}';
    }
    
    // fividovkodvk
}
