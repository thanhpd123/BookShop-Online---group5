
package entity;
import java.sql.Date;

public class Feedback {
    private int feedbackID;
    private String bookName;
    private String userName;
    private String rate;
    private Date fbDate;
    private String fbContent;

    public int getFeedbackID() {
        return feedbackID;
    }

    public Feedback(int feedbackID, String bookName, String userName, String rate, Date fbDate, String fbContent) {
        this.feedbackID = feedbackID;
        this.bookName = bookName;
        this.userName = userName;
        this.rate = rate;
        this.fbDate = fbDate;
        this.fbContent = fbContent;
    }

    public String getBookName() {
        return bookName;
    }

    public String getUserName() {
        return userName;
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

    public Feedback() {
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "Feedback{" + "feedbackID=" + feedbackID + ", bookName=" + bookName + ", userName=" + userName + ", rate=" + rate + ", fbDate=" + fbDate + ", fbContent=" + fbContent + '}';
    }
}
