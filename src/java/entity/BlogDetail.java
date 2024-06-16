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
public class BlogDetail {
    private int blogID;
    private String title;
    private String blogImg;
    private String blogAuthorImg;
    private String authorName;
    private String content;
    private Date createdDate;
    private int userID;
    
    public BlogDetail() {
    }

    public BlogDetail(int blogID, String title, String blogImg, String blogAuthorImg, String authorName, String content, Date createdDate, int userID) {
        this.blogID = blogID;
        this.title = title;
        this.blogImg = blogImg;
        this.blogAuthorImg = blogAuthorImg;
        this.authorName = authorName;
        this.content = content;
        this.createdDate = createdDate;
        this.userID = userID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public String getBlogAuthorImg() {
        return blogAuthorImg;
    }

    public void setBlogAuthorImg(String blogAuthorImg) {
        this.blogAuthorImg = blogAuthorImg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}