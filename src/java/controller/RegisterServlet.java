/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.ReceiverInfo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountDAO;
import model.DAOOrders;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String pass = request.getParameter("pass");
        String gender = request.getParameter("gender");
        String date = request.getParameter("dob");
        String msg = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(date, formatter);
        LocalDate today = LocalDate.now();
        AccountDAO accdao = new AccountDAO();
        DAOOrders daoOrder= new DAOOrders();
        LocalDate rd = LocalDate.now(); 
        String registerDate = rd.toString(); 
        if (email.length() > 40 || fname.length() > 40 || lname.length() > 40 || pass.length() > 40) {
            msg = "Your first name, last name, email, password exceed 40 characters";
        } else if (!(email.matches("^[a-zA-Z0-9_]+[@][a-zA-Z]+[.][a-zA-Z]+") || email.matches("^[a-zA-Z0-9_]+[@][a-zA-Z]+[.][a-zA-Z]+[.][a-zA-Z]+"))) {
            msg = "Email is invalid";
        } else if (pass.length() < 6 || pass.contains(" ")) {
            msg = "Password is invalid";
        } else if (!(phone.matches("[0]{1}[35798]{1}[0-9]{8}"))) {
            msg = "Phone is invalid";
        } else if (accdao.checkEmailExist(email)) {
            msg = "Email is existed!";
        } else if (today.isBefore(dob)) {
            msg = "Invalid date of birth";
        } else {
            try {
                accdao.AddNewAccount(fname, lname, email, phone, address, pass, gender, date, "active", registerDate);
                String name = fname + " " + lname;
                ArrayList <Account> list = accdao.getEmailList();
                int userID = list.get(list.size() - 1).getUserID();
                daoOrder.addReceiverInfo(new ReceiverInfo(name, phone, address, userID));
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            msg = "Successfully";
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
