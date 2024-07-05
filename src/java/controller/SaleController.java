/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.DAOOrders;

/**
 *
 * @author Dung Dinh
 */
public class SaleController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        String service = request.getParameter("service");
        String day = request.getParameter("day");
        HttpSession session = request.getSession(true);
        
        if (day == null) {
            day = "7";
        }
        
        if (service == null) {
            service = "dashboard";
        }

        if (service.equals("dashboard")) {
            Vector<Orders> vector = dao.getAll();
            Vector<Orders> vectorOr = new Vector<Orders>();
            String select = "Lựa Chọn";
//            int userID = Integer.parseInt(request.getParameter("userID"));
            Vector<Orders> vectorOrByUser = dao.getOrderByUser(2);
            if (day.equals("30")) {
                vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                        + "FROM Orders\n"
                        + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                        + "WHERE Orders.OrderDate BETWEEN GETDATE()-30 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                        + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                        + "ORDER BY Orders.OrderDate ");
                select = "30 Ngày Gần Nhất";
            }
            
            if (day.equals("365")) {
                vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                        + "FROM Orders\n"
                        + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                        + "WHERE Orders.OrderDate BETWEEN GETDATE()-365 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                        + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                        + "ORDER BY Orders.OrderDate ");
                select = "365 Ngày Gần Nhất";
                
            } 
            if (day.equals("7")) {
                vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                        + "FROM Orders\n"
                        + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                        + "WHERE Orders.OrderDate BETWEEN GETDATE()-7 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                        + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                        + "ORDER BY Orders.OrderDate");
                select = "7 Ngày Gần Nhất";
            }
            request.setAttribute("dataOrder", vector);
            request.setAttribute("dataOr", vectorOr);
            request.setAttribute("dataOrByUser", vectorOrByUser);
            request.setAttribute("day", day);
            request.setAttribute("select", select);
            request.getRequestDispatcher("/jsp/SaleDashboard.jsp");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/SaleDashboard.jsp");
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
