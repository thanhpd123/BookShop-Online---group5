/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Book {
    private String BookID, Name, PublisherName, AuthorID, Edition, CategoryID, PublicationDate;
    private int Quantity, Price;

    public Book() {
    }

    public Book(String BookID, String Name, String PublisherName, String AuthorID, String Edition, String CategoryID, String PublicationDate, int Quantity, int Price) {
        this.BookID = BookID;
        this.Name = Name;
        this.PublisherName = PublisherName;
        this.AuthorID = AuthorID;
        this.Edition = Edition;
        this.CategoryID = CategoryID;
        this.PublicationDate = PublicationDate;
        this.Quantity = Quantity;
        this.Price = Price;
    }

    public String getBookID() {
        return BookID;
    }

    public String getName() {
        return Name;
    }

    public String getPublisherName() {
        return PublisherName;
    }

    public String getAuthorName() {
        return AuthorID;
    }

    public String getEdition() {
        return Edition;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public String getPublicationDate() {
        return PublicationDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPublisherName(String PublisherName) {
        this.PublisherName = PublisherName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorID = AuthorID;
    }

    public void setEdition(String Edition) {
        this.Edition = Edition;
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID = CategoryID;
    }

    public void setPublicationDate(String PublicationDate) {
        this.PublicationDate = PublicationDate;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Book{" + "BookID=" + BookID + ", Name=" + Name + ", PublisherName=" + PublisherName + ", AuthorID=" + AuthorID + ", Edition=" + Edition + ", CategoryID=" + CategoryID + ", PublicationDate=" + PublicationDate + ", Quantity=" + Quantity + ", Price=" + Price + '}';
    }
    
    
}
