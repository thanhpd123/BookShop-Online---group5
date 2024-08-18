/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Ship;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import model.DAOOrder;

/**
 *
 * @author ADMIN
 */
public class ShipServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShipServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShipServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        DAOOrder daoShip = new DAOOrder();
        List<Ship> listShip = daoShip.GetShipOrderbyOrderState("Đang giao hàng");

        request.setAttribute("listShip", listShip);
        request.getRequestDispatcher("/jsp/ShipList.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    DAOOrder daoShip = new DAOOrder();
    
    // Lấy tất cả các tham số từ request
    Map<String, String[]> parameterMap = request.getParameterMap();
    
    for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
        String paramName = entry.getKey();
        String[] paramValues = entry.getValue();
        
        if (paramName.startsWith("orderState_")) {
            // Lấy OrderID từ tên tham số
            int orderId = Integer.parseInt(paramName.substring("orderState_".length()));
            String newStatus = paramValues[0];
            
            // Cập nhật trạng thái đơn hàng
            daoShip.updateOrderStatus(orderId, newStatus);
        }
    }
    
    // Sau khi cập nhật, lấy lại danh sách đơn hàng đang giao
    List<Ship> listShip = daoShip.GetShipOrderbyOrderState("Đang giao hàng");
    request.setAttribute("listShip", listShip);
    
    // Chuyển hướng trở lại trang danh sách đơn hàng
    request.getRequestDispatcher("/jsp/ShipList.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
