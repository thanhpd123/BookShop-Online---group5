package entity;

import java.sql.Date;

public class BlogComments {
    public int BlogID;
    public String UserFullName;
    public Date CommentDate;
    public String CommentText;
    public String imgUser;

    public BlogComments() {
    }

    public BlogComments(int BlogID, String UserFullName, Date CommentDate, String CommentText, String imgUser) {
        this.BlogID = BlogID;
        this.UserFullName = UserFullName;
        this.CommentDate = CommentDate;
        this.CommentText = CommentText;
        this.imgUser = imgUser;
    }

    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int BlogID) {
        this.BlogID = BlogID;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public void setUserFullName(String UserFullName) {
        this.UserFullName = UserFullName;
    }

    public Date getCommentDate() {
        return CommentDate;
    }

    public void setCommentDate(Date CommentDate) {
        this.CommentDate = CommentDate;
    }

    public String getCommentText() {
        return CommentText;
    }

    public void setCommentText(String CommentText) {
        this.CommentText = CommentText;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }
    
}
