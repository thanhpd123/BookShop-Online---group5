/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.Book;
import entity.MyOrder;
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
import java.util.List;
import java.util.Vector;
import model.DAOOrder;

/**
 *
 * @author ADMIN
 */
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOOrder dao = new DAOOrder();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        // Get search query
        String searchQuery = request.getParameter("searchQuery");
        if (searchQuery == null || searchQuery.isEmpty()) {
            searchQuery = "";
        }

        // Pagination parameters
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Get paginated and filtered orders
        Vector<Orders> listO = dao.GetPaginatedAndFilteredMyOrders(acc.getUserID(), searchQuery, page);
        Vector<Orders> vectorList = dao.getOrderList(acc.getUserID(), searchQuery, page);
        // Get total number of filtered orders for pagination
        int totalRecords = dao.GetTotalFilteredMyOrders(acc.getUserID(), searchQuery);
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / 4); // 4 records per page

        request.setAttribute("dataList", vectorList);
        request.setAttribute("ListOrders", listO);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("searchQuery", searchQuery);

        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
            request.setAttribute("successMessage", message);
        }

        String error = request.getParameter("error");
        if (error != null && !error.isEmpty()) {
            request.setAttribute("errorMessage", error);
        }

        request.getRequestDispatcher("/jsp/MyAllOrders.jsp").forward(request, response);
    }
}
