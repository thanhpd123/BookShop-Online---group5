/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.sql.Date;
/**
 *
 * @author ADMIN
 */
public class BookManage {
    private String bookID;
    private String bookImg;
    private String name;
    private String description;
    private String publisherName;
    private int authorID;
    private String edition;
    private String categoryID;
    private Date publicationDate;
    private int quantity;
    private int price;
    private float salePrice;
    private boolean flag;
    private String status;
    
    public BookManage() {
    }

    public BookManage(String bookID, String bookImg, String name, String description, String publisherName, int authorID, String edition, String categoryID, Date publicationDate, int quantity, int price, float salePrice, boolean flag, String status) {
        this.bookID = bookID;
        this.bookImg = bookImg;
        this.name = name;
        this.description = description;
        this.publisherName = publisherName;
        this.authorID = authorID;
        this.edition = edition;
        this.categoryID = categoryID;
        this.publicationDate = publicationDate;
        this.quantity = quantity;
        this.price = price;
        this.salePrice = salePrice;
        this.flag = flag;
        this.status = status;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookManage{" + "bookID=" + bookID + ", bookImg=" + bookImg + ", name=" + name + ", description=" + description + ", publisherName=" + publisherName + ", authorID=" + authorID + ", edition=" + edition + ", categoryID=" + categoryID + ", publicationDate=" + publicationDate + ", quantity=" + quantity + ", price=" + price + ", salePrice=" + salePrice + ", flag=" + flag + ", status=" + status + '}';
    }



}
