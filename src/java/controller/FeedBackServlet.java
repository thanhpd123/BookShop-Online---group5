/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Feedback;
import entity.FeedbackDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.FeedbackDAO;

/**
 *
 * @author ADMIN
 */
public class FeedBackServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> listF = dao.getAllFeedbacks();
        request.setAttribute("feedbacks", listF);
        request.getRequestDispatcher("feedbacklist.jsp").forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String bookname = request.getParameter("bookname");
        String rate = request.getParameter("rate");
        String sortBy = request.getParameter("sortBy");
        
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getFeedbackByFilter(fullname,bookname, rate, sortBy);
        request.setAttribute("feedbacks", list);
        request.getRequestDispatcher("feedbacklist.jsp").forward(request, response);
       
    }

}
