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
public class FeedbackDetail {
    private int feedbackID;
    private String bookID;
    private String rate;
    private Date fbDate;
    private String fbContent;
    private String userName;
    private String email;
    private String phoneNo;
    private String bookName;

    public FeedbackDetail() {
    }

    public FeedbackDetail(int feedbackID, String bookID, String rate, Date fbDate, String fbContent, String userName, String email, String phoneNo, String bookName) {
        this.feedbackID = feedbackID;
        this.bookID = bookID;
        this.rate = rate;
        this.fbDate = fbDate;
        this.fbContent = fbContent;
        this.userName = userName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.bookName = bookName;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public String getBookID() {
        return bookID;
    }

    public String getRate() {
        return rate;
    }

    public Date getFbDate() {
        return fbDate;
    }

    public String getFbContent() {
        return fbContent;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setFbDate(Date fbDate) {
        this.fbDate = fbDate;
    }

    public void setFbContent(String fbContent) {
        this.fbContent = fbContent;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
}

