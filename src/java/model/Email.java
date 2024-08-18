/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Dung Dinh
 */
public class Email extends DBConnect {

    private static final Logger LOG = Logger.getLogger(Email.class.getName());

    public Vector<Orders> getEmail(int iD) {
        String sql = "SELECT Account.Email, Account.FirstName, Account.LastName, Orders.ID, SUM(OrderDetail.Price) AS Price, Orders.OrderState\n"
                + "FROM Orders \n"
                + "INNER JOIN OrderDetail ON OrderDetail.OrderID = Orders.OrderID\n"
                + "INNER JOIN Account ON OrderDetail.UserID = Account. UserID\n"
                + "WHERE Orders.ID = '" + iD + "'\n"
                + "GROUP BY Account.Email, Account.FirstName, Account.LastName, Orders.ID, Orders.OrderState";
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                int id = rs.getInt(4);
                int price = rs.getInt(5);
                String orderstate = rs.getString(6);
                Orders or = new Orders(email, fname, lname, id, price, orderstate);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void send(int id) throws MessagingException {
        Email dao = new Email();
        Vector<Orders> vector = dao.getEmail(id);
        final String fromEmail = "mobiledot1002@gmail.com";
        final String password = "frjb appb cpim zsbt";
        final String toEmail = vector.get(0).getEmail();
        final String subject = "Xác Nhận Đặt Hàng";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        message.setSubject(subject);
        message.setSubject("Xác Nhận Đặt Hàng", "UTF-8");
        String htmlContent = "<!DOCTYPE html>\n"
                + "<html lang=\"vi\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Xác Nhận Đặt Hàng</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            line-height: 1.6;\n"
                + "            background-color: #f9f9f9;\n"
                + "            padding: 20px;\n"
                + "        }\n"
                + "        .container {\n"
                + "            width: 100%;\n"
                + "            margin: auto;\n"
                + "            padding: 20px;\n"
                + "            background-color: #ffffff;\n"
                + "            border-radius: 10px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "        h2 {\n"
                + "            color: #2c3e50;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        h4 {\n"
                + "            color: #2c3e50;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .divider {\n"
                + "            text-align: center;\n"
                + "            margin: 20px 0;\n"
                + "        }\n"
                + "        .order-details {\n"
                + "            margin-top: 20px;\n"
                + "        }\n"
                + "        .order-details h5, .order-details h6 {\n"
                + "            margin: 5px 0;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h2>Cảm Ơn " + vector.get(0).getLName() + " " + vector.get(0).getFName() + "</h2>\n"
                + "        <h2>Đã mua hàng tại BookEShop</h2>\n"
                + "        <div class=\"divider\">\n"
                + "            <hr>\n"
                + "            ========================\n"
                + "            <hr>\n"
                + "        </div>\n"
                + "        <div class=\"order-details\">\n"
                + "            <h4>Chi Tiết Đơn Hàng:</h4>\n"
                + "            <h5>Mã Đơn Hàng: <span id=\"orderID\">" + vector.get(0).getID() + "</span></h5>\n"
                + "            <h5>Tổng Thanh Toán: <span id=\"totalPrice\"> " + vector.get(0).getPrice() + "</span></h5>\n"
                + "            <h5>Trạng Thái Đơn Hàng: <span id=\"orderState\">" + vector.get(0).getOrderState() + "</span></h5>\n"
                + "        </div>\n"
                + "        <div class=\"divider\">\n"
                + "            <hr>\n"
                + "            ========================\n"
                + "            <hr>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        message.setContent(htmlContent, "text/html; charset=UTF-8");
        Transport.send(message);
    }
}
