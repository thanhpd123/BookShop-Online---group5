/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import entity.MyOrders;
import entity.OrderDetail;
import entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DBConnect {

    private static final Logger LOG = Logger.getLogger(DAOOrder.class.getName());

    public Vector<Orders> getAll(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                //String ssn=rs.getString("SSN");
                String OrderDate = rs.getString(2);
                // String name=rs.getString(2);
                String OrderState = rs.getString(3);

                int UserID = rs.getInt(4);

                Orders ord = new Orders(orderID, OrderDate, OrderState,
                        UserID);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<OrderDetail> getAllOrder(String sql) {
        Vector<OrderDetail> vector = new Vector<OrderDetail>();
        try {
            Statement state = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int orderID = rs.getInt(1);
                //String ssn=rs.getString("SSN");
                String BookID = rs.getString(2);
                int Quantity = rs.getInt(3);
                int Price = rs.getInt(4);

                OrderDetail ord = new OrderDetail(orderID, BookID, Quantity,
                        Price);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Orders> searchId(String id) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            String sql = "select * from Orders where OrderId like '%" + id + "%'";
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                String OrderDate = rs.getString(2);
                String OrderState = rs.getString(3);
                int UserID = rs.getInt(4);
                Orders order = new Orders(OrderID, OrderDate, OrderState, UserID);
                vector.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vector;
    }
}
