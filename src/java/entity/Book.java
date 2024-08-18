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
    private String BookID, Name, PublisherName, AuthorName, AuthorID, Edition, CategoryName, CategoryID, PublicationDate, BookImg, Description;
    private int Quantity, Price, SalePrice, Purchases;
    private String Flag, Status;
    
    public Book() {
    }

    public Book(String BookID, String Name, int Purchases) {
        this.BookID = BookID;
        this.Name = Name;
        this.Purchases = Purchases;
    }

    public Book(String Flag, String Status) {
        this.Flag = Flag;
        this.Status = Status;
    }
    
    public Book(int Purchases) {
        this.Purchases = Purchases;
    }

    public Book(String BookID, String BookImg, String Name, String Description, String PublisherName, String AuthorID, String Edition, String CategoryID, String PublicationDate, int Quantity, int Price) {
        this.BookID = BookID;
        this.BookImg = BookImg;
        this.Name = Name;
        this.Description = Description;
        this.PublisherName = PublisherName;
        this.AuthorID = AuthorID;
        this.Edition = Edition;
        this.CategoryID = CategoryID;
        this.PublicationDate = PublicationDate;
        this.Quantity = Quantity;
        this.Price = Price;
    }
    public Book(String BookID, String BookImg, String Name, String PublisherName, String AuthorName, String Edition, String CategoryName, String PublicationDate,int Quantity, int Price, int SalePrice, String Flag, String Status) {
        this.BookID = BookID;
        this.BookImg = BookImg;
        this.Name = Name;
        this.PublisherName = PublisherName;
        this.AuthorName = AuthorName;
        this.Edition = Edition;
        this.CategoryName = CategoryName;
        this.PublicationDate = PublicationDate;
        this.Quantity = Quantity;
        this.Price = Price;
        this.SalePrice = SalePrice;
        this.Flag = Flag;
        this.Status = Status;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getBookImg() {
        return BookImg;
    }

    public void setBookImg(String BookImg) {
        this.BookImg = BookImg;
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

    public String getAuthorID() {
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

    public int getPurchases() {
        return Purchases;
    }

    public void setPurchases(int Purchases) {
        this.Purchases = Purchases;
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

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public int getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(int SalePrice) {
        this.SalePrice = SalePrice;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setAuthorID(String AuthorID) {
        this.AuthorID = AuthorID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    @Override
    public String toString() {
        return "Book{" + "BookID=" + BookID + ", Name=" + Name + ", PublisherName=" + PublisherName + ", AuthorName=" + AuthorName + ", AuthorID=" + AuthorID + ", Edition=" + Edition + ", CategoryName=" + CategoryName + ", CategoryID=" + CategoryID + ", PublicationDate=" + PublicationDate + ", BookImg=" + BookImg + ", Description=" + Description + ", Quantity=" + Quantity + ", Price=" + Price + ", SalePrice=" + SalePrice + ", Flag=" + Flag + ", Status=" + Status + '}';
    }

    
    
}
