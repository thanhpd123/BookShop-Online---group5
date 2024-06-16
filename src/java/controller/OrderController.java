/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Book;
import entity.OrderDetail;
import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOOrder;

/**
 *
 * @author ADMIN
 */
public class OrderController extends HttpServlet {

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
        DAOOrder dao = new DAOOrder();
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("search")) {
            String id = request.getParameter("Name");
            Vector<Orders> vector = dao.searchId(id);
            request.setAttribute("orders", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            dis.forward(request, response);
        }
        if (service.equals("listAll")) {
            // call model

            Vector<Orders> vector = dao.getAll("""
                                                  SELECT Orders.OrderID, Orders.OrderDate, Orders.OrderState, Orders.UserID from Orders
                                                  INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID
                                                  INNER JOIN Account ON Orders.UserID = Account.UserID""");
            request.setAttribute("orders", vector);
            Vector<OrderDetail> vectorDetail = dao.getAllOrder("""
                                                                   SELECT OrderDetail.OrderID, OrderDetail.BookID, OrderDetail.Quantity, 
                                                                   OrderDetail.Price from OrderDetail
                                                                   INNER JOIN Orders ON Orders.OrderID = OrderDetail.OrderID
                                                                   INNER JOIN Book ON Book.BookID = OrderDetail.BookID""");
            request.setAttribute("orderDetail", vectorDetail);
            Vector<Book> vectorBook = dao.getAllBook("""
                                                     SELECT Book.BookID, Book.BookImg, Book.Name,Book.Description, Book.PublisherName, Book.AuthorID,Book.Edition,
                                                     Book.CategoryID,Book.PublicationDate,Book.Quantity,Book.Price from Book
                                                     INNER JOIN OrderDetail On Book.BookID = OrderDetail.BookID""");
            request.setAttribute("book", vectorBook);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/MyAllOrders.jsp");
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
