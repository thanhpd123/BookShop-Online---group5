/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Blog {
    private int blogID;
    private String title;
    private String authorName;
    private Date createdDate;
    private Date lastModifiedDate;
    
    public Blog() {
    }

    public Blog(int blogID, String title, String authorName, Date createdDate, Date lastModifiedDate) {
        this.blogID = blogID;
        this.title = title;
        this.authorName = authorName;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getBlogID() {
        return blogID;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
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

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
