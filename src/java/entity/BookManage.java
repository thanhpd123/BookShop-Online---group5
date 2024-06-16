/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class BookManage {
    private String bookID;
    private String name;
    private int authorID;
    private int price;
    private int quantity;

    public BookManage() {
    }

    public BookManage(String bookID, String name, int authorID, int price, int quantity) {
        this.bookID = bookID;
        this.name = name;
        this.authorID = authorID;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookManage{" + "bookID=" + bookID + ", name=" + name + ", authorID=" + authorID + ", price=" + price + ", quantity=" + quantity + '}';
    }
}
