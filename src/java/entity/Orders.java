/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dung Dinh
 */
public class Orders {

    private int OrderID, UserID, count, Quantity, Price, ReceiverID, ID;
    private String OrderDate, OrderState, LName, FName, Payment, Email;

    public Orders() {
    }
    
    public Orders(int UserID, int ID) {
        this.UserID = UserID;
        this.ID = ID;
    }
    
    public Orders(int ID, String OrderDate, String OrderState) {
        this.ID = ID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
    }

    public Orders(String Email, String LName, String FName, int ID, int Price, String OrderState) {
        this.ID = ID;
        this.LName = LName;
        this.FName = FName;
        this.Email = Email;
        this.Price = Price;
        this.OrderState = OrderState;
    }
    
    public Orders(String OrderState, int ID) {
        this.OrderState = OrderState;
        this.ID = ID;
    }
    
    public Orders(int ID, String OrderDate, String FName, String LName, int Price, String OrderState, int UserID) {
        this.ID = ID;
        this.OrderDate = OrderDate;
        this.FName = FName;
        this.LName = LName;
        this.Price = Price;
        this.OrderState = OrderState;
    }

    public Orders(String OrderDate, int ID, int UserID, String OrderState) {
        this.OrderDate = OrderDate;
        this.ID = ID;
        this.UserID = UserID;
        this.OrderState = OrderState;
    }
    
    public Orders(String OrderDate, String OrderState, int Quantity, int Price) {
        this.OrderDate = OrderDate;
        this.Quantity = Quantity;
        this.Price = Price;
        this.OrderState = OrderState;
    }
//    
//    public Orders(String OrderDate, String OrderState, int count) {
//        this.OrderDate = OrderDate;
//        this.count = count;
//        this.OrderState = OrderState;
//    }

    public Orders(String OrderDate, String OrderState, int UserID, int Quantity, int Price) {
        this.OrderDate = OrderDate;
        this.UserID = UserID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.OrderState = OrderState;
    }

    public Orders(int OrderID, String OrderDate, String OrderState, int ReceiverID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.ReceiverID = ReceiverID;
    }
    
    public Orders(int OrderID, String OrderDate, String OrderState, int ReceiverID, int UserID, int ID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.ReceiverID = ReceiverID;
        this.UserID = UserID;
        this.ID = ID;
    }

    public Orders(String OrderDate, String OrderState, int UserID) {
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.UserID = UserID;
    }

    public Orders(int OrderID, String OrderDate, String OrderState, int ReceiverID, int ID) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.ReceiverID = ReceiverID;
        this.ID = ID;
    }

    public Orders(String OrderDate, String OrderState, String Payment, int UserID, String LName, int ID) {
        this.UserID = UserID;
        this.ID = ID;
        this.OrderDate = OrderDate;
        this.OrderState = OrderState;
        this.Payment = Payment;
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getReceiverID() {
        return ReceiverID;
    }

    public void setReceiverID(int ReceiverID) {
        this.ReceiverID = ReceiverID;
    }
    

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrderID() {
        return OrderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public String getOrderState() {
        return OrderState;
    }

    public int getUserID() {
        return UserID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public void setOrderState(String OrderState) {
        this.OrderState = OrderState;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", OrderState=" + OrderState + ", UserID=" + UserID + '}';
    }

}
