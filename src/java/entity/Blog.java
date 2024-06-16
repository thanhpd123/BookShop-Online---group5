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
public class Blog {
    private int blogID;
    private String blogImg;
    private String blogAuthorImg;
    private String title;
    private String authorName;
    private Date createdDate;
    
    public Blog() {
    }

    public Blog(int blogID, String blogImg, String blogAuthorImg, String title, String authorName, Date createdDate) {
        this.blogID = blogID;
        this.blogImg = blogImg;
        this.blogAuthorImg = blogAuthorImg;
        this.title = title;
        this.authorName = authorName;
        this.createdDate = createdDate;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public void setBlogAuthorImg(String blogAuthorImg) {
        this.blogAuthorImg = blogAuthorImg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getBlogID() {
        return blogID;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public String getBlogAuthorImg() {
        return blogAuthorImg;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogID=" + blogID + ", blogImg=" + blogImg + ", blogAuthorImg=" + blogAuthorImg + ", title=" + title + ", authorName=" + authorName + ", createdDate=" + createdDate + '}';
    }

 



}
