/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.MyOrder;
import entity.OrderDetail;
import entity.OrderInfor;
import entity.Orders;
import entity.ReceiverInfo;
import entity.Ship;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DBConnect {

    public Vector<ReceiverInfo> getRcv(int ID) {
        String sql = "SELECT ReceiverInfo.ReceiverID, ReceiverInfo.Rname, ReceiverInfo.PhoneNo, ReceiverInfo.Address, ReceiverInfo.UserID\n"
                + "FROM ReceiverInfo\n"
                + "INNER JOIN Orders ON ReceiverInfo.ReceiverID = Orders.ReceiverID\n"
                + "WHERE Orders.ID = '" + ID + "'\n"
                + "GROUP BY ReceiverInfo.ReceiverID, ReceiverInfo.Rname, ReceiverInfo.PhoneNo, ReceiverInfo.Address, ReceiverInfo.UserID";
        Vector<ReceiverInfo> vector = new Vector<ReceiverInfo>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int rcvid = rs.getInt(1);
                String rname = rs.getString(2);
                String phoneNo = rs.getString(3);
                String address = rs.getString(4);
                int userID = rs.getInt(5);
                ReceiverInfo rcv = new ReceiverInfo(rcvid, rname, phoneNo, address, userID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Orders> getOrderList(int userID, String searchQuery, int numberPaging) {
        String sql = "				SELECT Orders.ID, Orders.OrderDate, Orders.OrderState, SUM(OrderDetail.Price) AS PRICE \n"
                + "                   FROM Orders \n"
                + "                   INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID \n"
                + "                   WHERE OrderDetail.UserID = ?\n"
                + "                   AND Orders.OrderState LIKE ?\n"
                + "                   GROUP BY Orders.ID, Orders.OrderDate, Orders.OrderState \n"
                + "                   ORDER BY Orders.OrderDate DESC \n"
                + "                   OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, "%" + searchQuery + "%");
            ps.setInt(3, (numberPaging - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int price = rs.getInt(4);
                Orders or = new Orders(id, orderDate, orderState, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Orders> getOrderInfo(int userID, int ID) {
        String sql = "SELECT Orders.ID, Orders.OrderDate, Orders.ORderState, SUM(OrderDetail.Price) AS PRICE\n"
                + "FROM Orders\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "WHERE OrderDetail.UserID = '" + userID + "' AND Orders.ID = '" + ID + "'\n"
                + "GROUP BY Orders.ID, Orders.OrderDate, Orders.ORderState";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String orderDate = rs.getString(2);
                String orderState = rs.getString(3);
                int price = rs.getInt(4);
                Orders or = new Orders(id, orderDate, orderState, price);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<OrderDetail> getOrderDetail(int ID) {
        String sql = "SELECT Book.Name, Book.BookImg, OrderDetail.Quantity, OrderDetail.Price, OrderDetail.Payment, OrderDetail.UserID\n"
                + "FROM OrderDetail\n"
                + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                + "INNER JOIN Book ON OrderDetail.BookID = Book.BookID\n"
                + "WHERE Orders.ID = '" + ID + "'";
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(1);
                String bookImg = rs.getString(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                String payment = rs.getString(5);
                int userID = rs.getInt(6);
                OrderDetail or = new OrderDetail(name, bookImg, quantity, price, payment, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public List<MyOrder> GetAllMyOrder(int UserID) {
        String sql = "select od.OrderID, o.OrderDate, o.OrderState, o.ID, od.UserID from Orders o \n"
                + "join OrderDetail od on o.OrderID=od.OrderID\n"
                + "where od.UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, UserID);
            ResultSet rs = ps.executeQuery();
            List<MyOrder> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new MyOrder(rs.getInt("OrderID"),
                        rs.getString("OrderDate"),
                        rs.getNString("OrderState"),
                        rs.getInt("ID"),
                        rs.getInt("UserID")));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public OrderInfor GetOrderDetailbyID(int OrderID) {
        String sql = "select od.OrderID, b.Name, od.Quantity, od.Price, r.Rname, r.Address, r.PhoneNo, od.Payment, o.OrderState, b.BookImg, od.UserID, o.ID\n"
                + "from OrderDetail od \n"
                + "join Orders o on od.OrderID = o.OrderID\n"
                + "join Book b on od.BookID = b.BookID\n"
                + "join ReceiverInfo r on o.ReceiverID = r.ReceiverID\n"
                + "join Account a on od.UserID = a.UserID\n"
                + "where od.OrderID = ?\n";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, OrderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrderInfor(rs.getInt("OrderID"),
                        rs.getNString("Name"),
                        rs.getInt("Quantity"),
                        rs.getInt("Price"),
                        rs.getNString("Rname"),
                        rs.getNString("Address"),
                        rs.getNString("PhoneNo"),
                        rs.getNString("Payment"),
                        rs.getNString("OrderState"),
                        rs.getNString("BookImg"),
                        rs.getInt("UserID"),
                        rs.getInt("ID"));
            }

        } catch (Exception ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean DeleteOrderDetail(int OrderID) {
        String sql = "delete from OrderDetail\n"
                + "where OrderID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, OrderID);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Ship> GetShipOrderbyOrderState(String OrderState) {
        String sql = "	\n"
                + "SELECT \n"
                + "    s.UserID, \n"
                + "    s.OrderID, \n"
                + "    a.LastName, \n"
                + "    b.Name AS BookName, \n"
                + "    o.ID, \n"
                + "    o.OrderDate, \n"
                + "    o.OrderState, \n"
                + "    r.Rname, \n"
                + "    r.PhoneNo, \n"
                + "    r.Address, \n"
                + "	od.Quantity,\n"
                + "	od.Price,\n"
                + "    SUM(od.Quantity * od.Price) AS TotalPrice\n"
                + "FROM \n"
                + "    Ship s \n"
                + "JOIN \n"
                + "    Orders o ON o.OrderID = s.OrderID\n"
                + "JOIN \n"
                + "    Account a ON a.UserID = s.UserID\n"
                + "JOIN \n"
                + "    OrderDetail od ON od.OrderID = o.OrderID\n"
                + "JOIN \n"
                + "    Book b ON b.BookID = od.BookID\n"
                + "JOIN \n"
                + "    ReceiverInfo r ON r.ReceiverID = o.ReceiverID\n"
                + "WHERE \n"
                + "    OrderState like ?\n"
                + "GROUP BY \n"
                + "    s.UserID, s.OrderID, a.LastName, b.Name, o.ID, o.OrderDate, o.OrderState, r.Rname, r.PhoneNo, r.Address, od.Price, od.Quantity";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setNString(1, OrderState);
            ResultSet rs = ps.executeQuery();
            List<Ship> listS = new ArrayList<>();
            while (rs.next()) {
                listS.add(new Ship(rs.getInt(1),
                        rs.getInt(2),
                        rs.getNString(3),
                        rs.getNString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getNString(7),
                        rs.getNString(8),
                        rs.getString(9),
                        rs.getNString(10),
                        rs.getInt(11),
                        rs.getInt(12), rs.getInt(13)));
            }
            return listS;
        } catch (Exception ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE Orders SET OrderState = ? WHERE OrderID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<Orders> GetPaginatedAndFilteredMyOrders(int userID, String searchQuery, int numberPaging) {
        Vector<Orders> list = new Vector<Orders>();
        String sql = "SELECT od.OrderID, o.OrderDate, o.OrderState, o.ID, od.UserID FROM Orders o \n"
                + "                     JOIN OrderDetail od ON o.OrderID = od.OrderID \n"
                + "                     WHERE od.UserID = ? AND o.OrderState LIKE ?\n"
                + "                     ORDER BY o.OrderDate DESC \n"
                + "					 OFFSET ? ROWS\n"
                + "					 FETCH NEXT 4 ROWS ONLY ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, "%" + searchQuery + "%");
            ps.setInt(3, (numberPaging - 1) * 4);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Orders(rs.getInt("OrderID"),
                        rs.getString("OrderDate"),
                        rs.getNString("OrderState"),
                        rs.getInt("ID"),
                        rs.getInt("UserID")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int GetTotalFilteredMyOrders(int userID, String searchQuery) {
        int count = 0;
        String sql = "SELECT COUNT(DISTINCT od.OrderID) FROM Orders o "
                + "JOIN OrderDetail od ON o.OrderID = od.OrderID "
                + "WHERE od.UserID = ? AND o.OrderState LIKE ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userID);
            st.setString(2, "%" + searchQuery + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();

        // Test parameters
        int userID = 1; // Thay đổi thành một userID hợp lệ trong cơ sở dữ liệu của bạn
        String searchQuery = "Đang xử lý"; // Thử nghiệm với các giá trị khác nhau, ví dụ: "Pending", "Delivered", etc.
        int page = 1;

        // Test GetPaginatedAndFilteredMyOrders
        System.out.println("Testing GetPaginatedAndFilteredMyOrders:");
        Vector<Orders> orders = dao.getOrderList(userID, searchQuery, page);
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Orders order : orders) {
                System.out.println("Order ID: " + order.getOrderID()
                        + ", Date: " + order.getOrderDate()
                        + ", State: " + order.getOrderState()
                        + ", ID: " + order.getID()
                        + ", User ID: " + order.getUserID());
            }
        }

        // Test GetTotalFilteredMyOrders
        System.out.println("\nTesting GetTotalFilteredMyOrders:");
        int totalOrders = dao.GetTotalFilteredMyOrders(userID, searchQuery);
        System.out.println("Total filtered orders: " + totalOrders);

        // Test with different search queries
        String[] searchQueries = {"Pending", "Delivered", "Cancelled", "InvalidState"};
        for (String query : searchQueries) {
            System.out.println("\nTesting with search query: " + query);
            orders = dao.GetPaginatedAndFilteredMyOrders(userID, query, 1);
            System.out.println("Number of orders found: " + orders.size());
            totalOrders = dao.GetTotalFilteredMyOrders(userID, query);
            System.out.println("Total filtered orders: " + totalOrders);
        }
    }
}
