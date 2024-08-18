/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.OrderDetail;
import entity.OrderInfor;
import entity.Orders;
import entity.ReceiverInfo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Vector;
import model.DAOOrder;

/**
 *
 * @author Dung Dinh
 */
public class ThisOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
//        int OrderID = Integer.parseInt(request.getParameter("OrderID"));
        int ID = Integer.parseInt(request.getParameter("ID"));
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        DAOOrder odao = new DAOOrder();
        Vector<ReceiverInfo> vectorRcv = odao.getRcv(ID);
        Vector<OrderDetail> vectorDetail = odao.getOrderDetail(ID);
        Vector<Orders> vectorList = odao.getOrderInfo(acc.getUserID(), ID);
        request.setAttribute("dataDetail", vectorDetail);
        request.setAttribute("dataRcv", vectorRcv);
        request.setAttribute("ID", ID);
        request.setAttribute("dataList", vectorList);
        request.getRequestDispatcher("/jsp/MyOrder.jsp").forward(request, response);
        request.getRequestDispatcher("BookCart?service=cancel").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        int ID = Integer.parseInt(request.getParameter("ID"));
        DAOOrder odao = new DAOOrder();

        boolean success = odao.DeleteOrderDetail(ID);
        if (success) {
            response.sendRedirect("OrderController?message=Order deleted successfully");
        } else {
            response.sendRedirect("ThisOrderServlet?OrderID=" + ID + "&error=Failed to delete order");
        }
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
