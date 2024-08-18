/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Account;
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
public class EmailNoti extends DBConnect {

    private static final Logger LOG = Logger.getLogger(Email.class.getName());

    public Vector<Account> getPassword(int iD) {
        String sql = "SELECT FirstName, LastName, Email, Password\n"
                + "FROM Account\n"
                + "WHERE UserID = '" + iD + "'";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                Account or = new Account(fname, lname,email, password);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Account> getAll() {
        String sql = "SELECT FirstName, LastName, Email, Password, UserID\n"
                + "FROM Account\n";
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                int userID = rs.getInt(5);
                Account or = new Account(fname, lname,email, password, userID);
                vector.add(or);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void sendEmail() throws MessagingException {
        EmailNoti dao = new EmailNoti();
        Vector<Account> vectorAll = dao.getAll();
        int userID = vectorAll.lastElement().getUserID();
        Vector<Account> vector = dao.getPassword(userID);
        final String fromEmail = "mobiledot1002@gmail.com";
        final String password = "frjb appb cpim zsbt";
        final String toEmail = vector.get(0).getPhoneNo();
        final String subject = "Xác Nhận Tài Khoản";
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
        message.setSubject("Xác Nhận Tài Khoản", "UTF-8");
        String htmlContent = "<!DOCTYPE html>\n"
                + "<html lang=\"vi\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Tạo Tài Khoản Thành Công</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f4f4f4;\n"
                + "            color: #333;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "        h3 {\n"
                + "            color: #2c3e50;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .container {\n"
                + "            width: 100%;\n"
                + "            max-width: 600px;\n"
                + "            margin: 0 auto;\n"
                + "            background-color: #fff;\n"
                + "            padding: 20px;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "        }\n"
                + "        .content {\n"
                + "            padding: 20px 0;\n"
                + "        }\n"
                + "        .divider {\n"
                + "            text-align: center;\n"
                + "            margin: 20px 0;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"content\">\n"
                + "            <h3>Tạo tài khoản thành công</h3>\n"
                + "            <div class=\"divider\">\n"
                + "            <hr>\n"
                + "            ========================\n"
                + "            </hr>\n"
                + "            </div>\n"
                + "            <p>Xin chào " + vector.get(0).getFirstName() + " " + vector.get(0).getLastName() + ",</p>\n"
                + "            <p>Chúng tôi xin chân thành cảm ơn bạn đã đăng ký và tạo tài khoản ở BookEShop.</p>\n"
                + "            <p>Chúng tôi rất vui được phục vụ bạn và hy vọng bạn sẽ có những trải nghiệm tuyệt vời với dịch vụ của chúng tôi.</p>\n"
                + "            <div class=\"divider\">\n"
                + "            <hr>\n"
                + "            ========================\n"
                + "            <hr>\n"
                + "            </div>\n"
                + "            <p>Mật khẩu của bạn là: " + vector.get(0).getAddress() + "</p>\n"
                + "            <div class=\"divider\"></div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        message.setContent(htmlContent, "text/html; charset=UTF-8");
        Transport.send(message);
    }
}
