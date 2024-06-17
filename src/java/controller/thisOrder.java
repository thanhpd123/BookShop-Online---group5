/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
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
        int id = Integer.parseInt(request.getParameter("order"));

        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            // call model
            Vector<Orders> vector = dao.getAll("""
                                                 SELECT Orders.OrderID, Orders.OrderDate, Orders.OrderState, Orders.UserID from Orders
                                                 INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID
                                                 INNER JOIN Account ON Orders.UserID = Account.UserID""");

            request.setAttribute("orders", vector);
            Vector<OrderDetail> vectorOD = dao.getAllOrder("""
                                                      SELECT OrderDetail.OrderID, OrderDetail.BookID, OrderDetail.Quantity, 
                                                      OrderDetail.Price from OrderDetail
                                                      INNER JOIN Orders ON Orders.OrderID = OrderDetail.OrderID
                                                      INNER JOIN Book ON Book.BookID = OrderDetail.BookID 
                                                                          """);

            request.setAttribute("thisOrders", vectorOD);
            Vector<Book> vectorBook = dao.getAllBook("""
                                                     SELECT Book.BookID, Book.BookImg, Book.Name,Description, Book.PublisherName, Book.AuthorID,Book.Edition,
                                                     Book.CategoryID,Book.PublicationDate,Book.Quantity,Book.Price from Book
                                                     INNER JOIN OrderDetail On Book.BookID = OrderDetail.BookID""");
            request.setAttribute("book", vectorBook);
            Vector<Account> vectorAcc = dao.getAllAcc("""
                                                      SELECT Account.UserID, Account.RoleID, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.DOB, Account.Gender ,Account.imgUser from Account
                                                      INNER JOIN Orders On Account.UserID = Orders.UserID""");
            request.setAttribute("acc", vectorAcc);
            String submit = request.getParameter("submit");

            if (submit != null) {
                String orderState = request.getParameter("orderState");
                String sql = ("UPDATE Orders SET OrderState = ? WHERE OrderID = ?");
                dao.updateState(orderState,id+1,sql);
            }

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
