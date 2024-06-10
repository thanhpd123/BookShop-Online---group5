/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Feedback {
    private int FeedbackID, UserID;
    private String BookID, Rate, FBDate, FBContent;

    public Feedback() {
    }
    
    public Feedback(int FeedbackID, String BookID, String Rate, int UserID, String FBDate, String FBContent) {
        this.FeedbackID = FeedbackID;
        this.BookID = BookID;
        this.Rate = Rate;
        this.UserID = UserID;
        this.FBDate = FBDate;
        this.FBContent = FBContent;
    }

    public int getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String Rate) {
        this.Rate = Rate;
    }

    public String getFBDate() {
        return FBDate;
    }

    public void setFBDate(String FBDate) {
        this.FBDate = FBDate;
    }

    public String getFBContent() {
        return FBContent;
    }

    public void setFBContent(String FBContent) {
        this.FBContent = FBContent;
    }

    @Override
    public String toString() {
        return "Feedback{" + "FeedbackID=" + FeedbackID + ", UserID=" + UserID + ", BookID=" + BookID + ", Rate=" + Rate + ", FBDate=" + FBDate + ", FBContent=" + FBContent + '}';
    }
    
    
}
