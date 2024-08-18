/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Account;
import entity.Orders;
import entity.OrderDetail;
import entity.OrderDetails;
import entity.ReceiverInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dung Dinh
 */
public class DAOOrders extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOOrders.class.getName());

    public Vector<Account> getAccount(int UserID) {
        String sql = "SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                + "FROM Account\n"
                + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                + "WHERE UserID = '" + UserID + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int userID = rs.getInt(1);
                String roleID = rs.getString(2);
                String fName = rs.getString(3);
                String lName = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);
                String phoneNo = rs.getString(7);
                String address = rs.getString(8);
                int gt = rs.getInt(9);
                boolean gender = (gt == 1 ? true : false);
                String dob = rs.getString(10);
                String imgUser = rs.getString(11);
                String status = rs.getString(12);
                String rd = rs.getString(13);
                Account acc = new Account(userID, roleID, fName, lName, email, password, phoneNo, address, dob, gender, imgUser, status, rd);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Orders> filter (String status, String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String orderdate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderstate = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(id, orderdate, fname, lname, price, orderstate, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<ReceiverInfo> getReceiverAdr(int id) {
        String sql = "SELECT ReceiverInfo.Rname, ReceiverInfo.PhoneNo, ReceiverInfo.Address\n"
                + "FROM Orders\n"
                + "INNER JOIN ReceiverInfo ON Orders.ReceiverID = ReceiverInfo.ReceiverID\n"
                + "WHERE ID = '" + id + "'\n"
                + "GROUP BY Rname, PhoneNo, Address, ID";
        Vector<ReceiverInfo> vector = new Vector<ReceiverInfo>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String rname = rs.getString(1);
                String phoneNo = rs.getString(2);
                String address = rs.getString(3);
                ReceiverInfo rcv = new ReceiverInfo(rname, phoneNo, address);
                vector.add(rcv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getUserID() {
        String sql = "SELECT Orders.OrderDate, Orders.OrderState, OrderDetail.UserID, Orders.ID\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                + "GROUP BY Orders.OrderDate, Orders.OrderState, OrderDetail.UserID, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int id = rs.getInt(4);
                Orders or = new Orders(orderDate, id, userID, orderState);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getAll() {
        String sql = "Select * From Orders";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                int receiverID = rs.getInt(5);
                int iD = rs.getInt(6);
                Orders or = new Orders(orderID, orderDate, orderState, userID, receiverID, iD);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrderInfo(int id) {
        String sql = "SELECT Orders.OrderDate, Orders.OrderState, OrderDetail.Payment, Orders.UserID, Account.LastName, Orders.ID\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID \n"
                + "INNER JOIN Account ON Orders.UserID = Account.UserID \n"
                + "WHERE Orders.ID = '" + id + "'\n"
                + "GROUP BY Orders.OrderDate, Orders.OrderState, OrderDetail.Payment, Orders.UserID,  Account.LastName, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                String payment = rs.getString(3);
                int userID = rs.getInt(4);
                String name = rs.getString(5);
                int iD = rs.getInt(6);
                Orders or = new Orders(orderDate, orderState, payment, userID, name, iD);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getNewestOrder() {
        String sql = "SELECT *\n"
                + "FROM Orders\n"
                + "WHERE Orders.OrderDate BETWEEN GETDATE()-7 AND GETDATE()";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                Orders or = new Orders(orderID, orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<OrderDetail> getAllOrderDetail() {
        String sql = "Select * From OrderDetail";
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String bookID = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                OrderDetail or = new OrderDetail(orderID, bookID, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<OrderDetail> getOrderDetail(String sql) {
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String bookID = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                String payment = rs.getString(5);
                int userID = rs.getInt(6);
                OrderDetail or = new OrderDetail(orderID, bookID, quantity, price, payment, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<OrderDetail> getOrderDetail(int id) {
        String sql = "SELECT Book.BookImg, Book.Name, Author.AuthorName, Category.CategoryName, OrderDetail.Quantity, Book.Price\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Book ON OrderDetail.BookID = Book.BookID\n"
                + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                + "WHERE Orders.ID = '" + id + "'";
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bookImg = rs.getString(1);
                String name = rs.getString(2);
                String aname = rs.getString(3);
                String cname = rs.getString(4);
                int quantity = rs.getInt(5);
                int price = rs.getInt(6);
                OrderDetail or = new OrderDetail(bookImg, name, aname, cname, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int updateState(String orderstate, int ID) {
        int n = 0;
        String sql = "UPDATE Orders \n"
                + "SET OrderState = N'" + orderstate + "'\n"
                + "WHERE ID = '" + ID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateSaleID(int UserID, int ID) {
        int n = 0;
        String sql = "UPDATE Orders \n"
                + "SET UserID = N'" + UserID + "'\n"
                + "WHERE ID = '" + ID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updatePurchases(int num, int ID) {
        int n = 0;
        String sql = "UPDATE Book \n"
                + "SET Purchases = '" + num + "'\n"
                + "WHERE BookID = '" + ID + "'";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Orders> getOrderList(int start, int end) {
        String sql = "SELECT Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, SUM(OrderDetail.Price) AS Price, Orders.OrderState, Orders.UserID\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                + "WHERE Orders.ID BETWEEN '" + start + "' AND '" + end + "'\n"
                + "GROUP BY Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, Orders.OrderState, Orders.UserID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> filter(int start, int end, String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> sort(int start, int end, String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getAllOrderList() {
        String sql = "SELECT Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, SUM(OrderDetail.Price) AS Price, Orders.OrderState, Orders.UserID\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                + "GROUP BY Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, Orders.OrderState, Orders.UserID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> searchOrderList(String search) {
        String sql = "SELECT Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, SUM(OrderDetail.Price) AS Price, Orders.OrderState, Orders.UserID\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                + "WHERE Orders.ID LIKE '%" + search + "%' OR Account.FirstName LIKE '%" + search + "%' OR Account.LastName LIKE '%" + search + "%'\n"
                + "GROUP BY Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, Orders.OrderState, Orders.UserID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> searchOrderList(String search, int start, int end) {
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT Orders.ID, \n"
                + "           Orders.OrderDate, \n"
                + "           Account.FirstName, \n"
                + "           Account.LastName, \n"
                + "           SUM(OrderDetail.Price) AS Price, \n"
                + "           Orders.OrderState, \n"
                + "           Orders.UserID,\n"
                + "           ROW_NUMBER() OVER (ORDER BY Orders.ID) AS RowNum\n"
                + "    FROM Orders\n"
                + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                + "    WHERE Orders.ID LIKE '%a%' \n"
                + "       OR Account.FirstName LIKE '%a%' \n"
                + "       OR Account.LastName LIKE '%a%'\n"
                + "    GROUP BY Orders.ID, Orders.OrderDate, Account.FirstName, Account.LastName, Orders.OrderState, Orders.UserID\n"
                + ") AS SubQuery\n"
                + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "'";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int iD = rs.getInt(1);
                String orderDate = rs.getString(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                int price = rs.getInt(5);
                String orderState = rs.getString(6);
                int userID = rs.getInt(7);
                Orders or = new Orders(iD, orderDate, fname, lname, price, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> searchUserID(String search) {
        String sql = "SELECT Orders.OrderDate, Orders.OrderState, OrderDetail.UserID, Orders.ID\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                + "INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                + "WHERE Orders.ID LIKE '%" + search + "%' OR Account.FirstName LIKE '%" + search + "%' OR Account.LastName LIKE '%" + search + "%'\n"
                + "GROUP BY Orders.OrderDate, Orders.OrderState, OrderDetail.UserID, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int iD = rs.getInt(4);
                Orders or = new Orders(orderDate, iD, userID, orderState);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrder(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                Orders or = new Orders(orderDate, orderState, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrderDash() {
        String sql = "SELECT Orders.OrderDate , Orders.OrderState, Orders.ID\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE Orders.OrderDate BETWEEN GETDATE()-30 AND GETDATE()\n"
                + "GROUP By Orders.OrderDate, Orders.OrderState, Orders.ID";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                Orders or = new Orders(orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Account> getAddressInfo(int UserID) {
        String sql = "Select FirstName, LastName, PhoneNo, Address\n"
                + "from Account\n"
                + "WHERE UserID = '" + UserID + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String firstName = rs.getString(1);
                String lastName = rs.getString(2);
                String phoneNo = rs.getString(3);
                String address = rs.getString(4);
                Account acc = new Account(firstName, lastName, phoneNo, address);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<ReceiverInfo> getReceiverInfo(int UserID) {
        String sql = "SELECT ReceiverID, Rname, PhoneNo, Address, Description\n"
                + "FROM ReceiverInfo\n"
                + "WHERE UserID = '" + UserID + "'";
        Vector<ReceiverInfo> vector = new Vector<ReceiverInfo>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phoneNo = rs.getString(3);
                String address = rs.getString(4);
                String description = rs.getString(5);
                ReceiverInfo acc = new ReceiverInfo(id, name, phoneNo, address, description);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<ReceiverInfo> getReceiverAdr(int UserID, String ID) {
        String sql = "SELECT ReceiverID, Rname, PhoneNo, Address, Description\n"
                + "FROM ReceiverInfo\n"
                + "WHERE UserID = '" + UserID + "' AND ReceiverID = '" + ID + "'";
        Vector<ReceiverInfo> vector = new Vector<ReceiverInfo>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phoneNo = rs.getString(3);
                String address = rs.getString(4);
                String description = rs.getString(5);
                ReceiverInfo acc = new ReceiverInfo(id, name, phoneNo, address, description);
                vector.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrderByUser(int UserID) {
        String sql = "SELECT Orders.OrderDate , Orders.OrderState, Orders.UserID, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE Orders.OrderState = N'Đã giao hàng' AND Orders.UserID = '" + UserID + "'\n"
                + "GROUP By Orders.OrderDate, Orders.OrderState, Orders.UserID\n"
                + "ORDER BY Orders.OrderDate";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Orders or = new Orders(orderDate, orderState, userID, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrder(int UserID, String start, String end) {
        String sql = "SELECT Orders.OrderDate , Orders.OrderState, Orders.UserID, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE Orders.OrderDate BETWEEN '" + start + "' AND '" + end + "' AND Orders.OrderState = N'Đã giao hàng' AND OrderDetail.UserID = '" + UserID + "'\n"
                + "GROUP By Orders.OrderDate, Orders.OrderState, Orders.UserID\n"
                + "ORDER BY Orders.OrderDate";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String orderDate = rs.getString(1);
                String orderState = rs.getString(2);
                int userID = rs.getInt(3);
                int quantity = rs.getInt(4);
                int price = rs.getInt(5);
                Orders or = new Orders(orderDate, orderState, userID, quantity, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    Vector<Orders> getSuccess() {
        String sql = "SELECT * FROM Orders\n"
                + "WHERE OrderState = N'Đã giao hàng'";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int userID = rs.getInt(4);
                Orders or = new Orders(orderID, orderDate, orderState, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int addReceiverInfo(ReceiverInfo rcv) {
        int n = 0;
        String sql = "INSERT INTO ReceiverInfo (Rname, PhoneNo, Address, UserID)\n"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, rcv.getName());
            pre.setString(2, rcv.getPhoneNo());
            pre.setString(3, rcv.getAddress());
            pre.setInt(4, rcv.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addOrder(Orders or) {
        int n = 0;
        String sql = "INSERT INTO Orders (OrderID, OrderDate, OrderState, ReceiverID, ID)\n"
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, or.getOrderID());
            pre.setString(2, or.getOrderDate());
            pre.setString(3, or.getOrderState());
            pre.setInt(4, or.getReceiverID());
            pre.setInt(5, or.getID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addOrderDetail(OrderDetail or) {
        int n = 0;
        String sql = "INSERT INTO OrderDetail (OrderID, BookID, Quantity, Price, Payment, UserID)\n"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, or.getOrderID());
            pre.setString(2, or.getBookID());
            pre.setInt(3, or.getQuantity());
            pre.setInt(4, or.getPrice());
            pre.setString(5, or.getPayment());
            pre.setInt(6, or.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int deleteCart(String bookID, int userID) {
        int n = 0;
        String sql = "DELETE FROM Cart WHERE BookID = '" + bookID + "' AND UserID = '" + userID + "';";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public OrderDetails getOrderDetailbyID(int orderID) {
        String sql = "select od.OrderID, b.BookImg, b.Name AS BookName, b.Quantity, b.Price, o.OrderDate, o.OrderState, o.UserID from OrderDetail od \n"
                + "join Orders o on od.OrderID = o.OrderID \n"
                + "join Book b on od.BookID = b.BookID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrderDetails(rs.getInt("OrderID"), rs.getNString("BookImg"), rs.getNString("BookName"), rs.getInt("Quantity"), rs.getInt("Price"), rs.getNString("OrderDate"), rs.getNString("OrderState"), rs.getInt("UserID"));
            }
        } catch (Exception ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
