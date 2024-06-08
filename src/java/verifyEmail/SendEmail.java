/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifyEmail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
       public static void sendEmail(String to, String subject, String content, String user, String pass){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        };
        //Phien lam viec 
        Session session = Session.getInstance(properties, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user)); // Đặt địa chỉ người gửi

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Đặt địa chỉ người nhận
            message.setSubject("Email System"); // Đặt tiêu đề email
            message.setContent(content, "text/html;charset=UTF-8"); // Đặt nội dung email dưới dạng HTML

            Transport.send(message); // Gửi email
        } catch (MessagingException e) {
            e.printStackTrace();
        }    
    }
}
