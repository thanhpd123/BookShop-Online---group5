/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.CartItem;
import entity.OrderDetail;
import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOCart;
import model.DAOOrder;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "thisOrder", urlPatterns = {"/thisOrder"})
public class thisOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOOrder dao = new DAOOrder();
        String service = request.getParameter("service");

        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            // call model
            
            Vector<Orders> vector = dao.getAll("""
                                                  SELECT Orders.OrderID, Orders.OrderDate, Orders.OrderState, Orders.UserID from Orders
                                                  INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID
                                                  INNER JOIN Customers ON Orders.UserID = Customers.UserID""");
            
            Vector<OrderDetail> vectorOD = dao.getAllOrder("""
                                                      SELECT Orders.OrderID, Orders.OrderDate, Orders.OrderState,
                                                                           Book.Name, Book.Price, 
                                                                         Customers.FirstName + Customers.LastName AS Customer, Customers.PhoneNo, Customers.Email 
                                                                          FROM OrderDetail 
                                                                          INNER JOIN Orders ON Orders.OrderID = OrderDetail.OrderID 
                                                                         INNER JOIN Book ON OrderDetail.BookID = Book.BookID 
                                                                          INNER JOIN Customers ON Orders.UserID = Customers.UserID""");
            
            
            /*
            SELECT Orders.OrderID, Orders.OrderDate, Orders.OrderState,
                     Book.Name, Book.Price, 
                   Customers.FirstName + Customers.LastName AS Customer, Customers.PhoneNo, Customers.Email 
                    FROM OrderDetail 
                    INNER JOIN Orders ON Orders.OrderID = OrderDetail.OrderID 
                   INNER JOIN Book ON OrderDetail.BookID = Book.BookID 
                    INNER JOIN Customers ON Orders.UserID = Customers.UserID
            
            */
            request.setAttribute("orders", vector);
            request.setAttribute("thisOrders", vectorOD);
            
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/MyOrder.jsp");
            dis.forward(request, response);
            
        }
        
        
    }
        
         

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
