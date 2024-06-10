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

    private int CartID, UserID, Quantity, Price;
    private String BookID, BookImg;

    public Cart() {
    }

    public Cart(int CartID, int UserID, String BookID, String BookImg, int Quantity, int Price) {
        this.CartID = CartID;
        this.UserID = UserID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.BookID = BookID;
        this.BookImg = BookImg;
    }

    public Cart(int UserID, int Quantity, int Price, String BookID, String BookImg) {
        this.UserID = UserID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.BookID = BookID;
        this.BookImg = BookImg;
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

    public String getBookImg() {
        return BookImg;
    }

    public void setBookImg(String BookImg) {
        this.BookImg = BookImg;
    }

    @Override
    public String toString() {
        return "Cart{" + "CartID=" + CartID + ", UserID=" + UserID + ", Quantity=" + Quantity + ", Price=" + Price + ", BookID=" + BookID + ", BookImg=" + BookImg + '}';
    }

}
