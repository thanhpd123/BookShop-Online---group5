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
        if (customer != null && customer.getStatus().equals("active")) {
            HttpSession session = request.getSession();
            session.setAttribute("acc", customer);
            session.setAttribute("msg1", "Login Successfuly");
            if (customer.getRoleID().equals("1")) {
//                request.getRequestDispatcher("AdminController?service=listUser").forward(request, response);
                request.getRequestDispatcher("/jsp/AdminManage.jsp").forward(request, response);
                request.getRequestDispatcher("AdminController?service=dashboard").forward(request, response);
            }
            if (customer.getRoleID().equals("2")) {
//                request.getRequestDispatcher("AdminController?service=listUser").forward(request, response);
                request.getRequestDispatcher("BookManagementServlet").forward(request, response);
            }
            if (customer.getRoleID().equals("4")) {
//                request.getRequestDispatcher("AdminController?service=listUser").forward(request, response);
                request.getRequestDispatcher("/jsp/SaleManage.jsp").forward(request, response);
                request.getRequestDispatcher("SaleController?service=dashboard").forward(request, response);
                request.getRequestDispatcher("SaleController?service=orderList").forward(request, response);
                request.getRequestDispatcher("SaleController?service=changeStatus").forward(request, response);
            }
            if (customer.getRoleID().equals("5")) {
//                request.getRequestDispatcher("AdminController?service=listUser").forward(request, response);
                request.getRequestDispatcher("ShipServlet").forward(request, response);
            }
            request.getRequestDispatcher("Home?service=listAll").forward(request, response);
            request.getRequestDispatcher("LogOut").forward(request, response);
            request.getRequestDispatcher("BookCart?service=checkOut").forward(request, response);
            request.getRequestDispatcher("BookCart?service=payment").forward(request, response);
            request.getRequestDispatcher("BookController?service=listAll").forward(request, response);
            request.getRequestDispatcher("BookCart?service=deleteAll").forward(request, response);
            request.getRequestDispatcher("AboutUs").forward(request, response);
            request.getRequestDispatcher("BookController?service=viewBook").forward(request, response);
            request.getRequestDispatcher("BookCart?service=showAll").forward(request, response);
            request.getRequestDispatcher("BookCart?service=addAddress").forward(request, response);
        } if (!customer.getStatus().equals("active")) {
            request.setAttribute("msg", "Account is not available");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
