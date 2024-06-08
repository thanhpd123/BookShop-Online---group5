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
public class BlogDetail {
    private int blogID;
    private String title;
    private String authorName;
    private String content;
    private Date createdDate;
    private int userID;
    private Date commentDate;
    private String commentText;
    
    public BlogDetail() {
    }

    public BlogDetail(int blogID, String title, String authorName, String content, Date createdDate, int userID, Date commentDate, String commentText) {
        this.blogID = blogID;
        this.title = title;
        this.authorName = authorName;
        this.content = content;
        this.createdDate = createdDate;
        this.userID = userID;
        this.commentDate = commentDate;
        this.commentText = commentText;
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

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getUserID() {
        return userID;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public String getCommentText() {
        return commentText;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    
    
    
}
