/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accdao = new AccountDAO();
        HttpSession session = request.getSession();
        Account customer = (Account) session.getAttribute("acc");
        if (customer != null) {
            Account customerLoad = accdao.loadAccount(customer.getEmail());
            request.setAttribute("msg1", customerLoad.getPassword());
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String repass = request.getParameter("repass");
        Account customer = (Account) session.getAttribute("acc");
        AccountDAO accdao = new AccountDAO();
        ///Example
        String roleID = customer.getRoleID().toString();
        if(!roleID.equals("2")){
            response.sendRedirect("login");
        }
        ///Example
        Account customerLoad = accdao.loadAccount(customer.getEmail());
        String msg = "";
        int count = 0;
        for(int i = 0; i < newpass.length(); i++){
            if(Character.isDigit(newpass.charAt(i))){
                count ++;
            }
        }
        if (!oldpass.equals(customer.getPassword())) {
            msg = "Old Password not valid!";
        } else if (newpass.length() < 6) {
            msg = "New password is too short!";
        } else if (newpass.contains(" ")) {
            msg = "New password not valid!";
        } else if (!newpass.equals(repass)) {
            msg = "New Pass not matches repass!";
        } else if (!Character.isUpperCase(newpass.charAt(0))){
            msg = "New password must start with an uppercase letter!";
        } else if (count == 0){
            msg = "Password must have at least one digit!";
        }
        else {
            accdao.Changepass(customer.getEmail(), repass);
            msg = "Change password successfuly!";
        }
        
        
        request.setAttribute("msg", msg);
        request.setAttribute("msg1", customerLoad.getPassword());
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
