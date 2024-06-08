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
    private String BookID, BookImg;

    public CartItem() {
    }

    public CartItem(String BookImg, int CartID, String BookID, int Quantity, int Price) {
        this.BookImg = BookImg;
        this.CartID = CartID;
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
        return "CartItem{" + "CartID=" + CartID + ", Quantity=" + Quantity + ", Price=" + Price + ", BookID=" + BookID + ", BookImg=" + BookImg + '}';
    }
}
