/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class EntityFeedback {
    private int FeedbackID, UserID, Rate;
    private String BookID, FBDate, FBContent, userID;

    public EntityFeedback() {
    }
    
    public EntityFeedback(int FeedbackID, String BookID, int Rate, int UserID, String FBDate, String FBContent) {
        this.FeedbackID = FeedbackID;
        this.BookID = BookID;
        this.Rate = Rate;
        this.UserID = UserID;
        this.FBDate = FBDate;
        this.FBContent = FBContent;
    }
    
    public EntityFeedback(int FeedbackID, String BookID, int Rate, String userID, String FBDate, String FBContent) {
        this.FeedbackID = FeedbackID;
        this.BookID = BookID;
        this.Rate = Rate;
        this.userID = userID;
        this.FBDate = FBDate;
        this.FBContent = FBContent;
    }
    
    public String getUserIDStr() {
        return userID;
    }
    
    public void setUserIDStr(String userID) {
        this.userID = userID;
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

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
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

