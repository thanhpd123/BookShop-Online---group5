/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import model.*;
import entity.Account;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        
        AccountDAO accdao = new AccountDAO();
        Account customer = accdao.login(email, pass);
        if(customer != null){
            HttpSession session = request.getSession();
            session.setAttribute("acc", customer);
            session.setAttribute("msg1", "Login Successfuly");
            if (customer.getRoleID().equals("3")) {
                request.getRequestDispatcher("Home?service=listAll").forward(request, response);
                request.getRequestDispatcher("BookCart?service=checkOut").forward(request, response);
                request.getRequestDispatcher("BookCart?service=deleteAll").forward(request, response);
            }
            if (customer.getRoleID().equals("1")) {
//                request.getRequestDispatcher("AdminController?service=listUser").forward(request, response);
                request.getRequestDispatcher("/jsp/AdminManage.jsp").forward(request, response);
            }
            request.getRequestDispatcher("BookController?service=viewBook");
            request.getRequestDispatcher("BookCart?service=showAll");
        }
        else{
            request.setAttribute("msg", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
