
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookManageDAO;

public class DeleteBookManageServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        BookManageDAO dao = new BookManageDAO();
        dao.deleteBook(bookID);
        response.sendRedirect("BookManagementServlet");
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        int price = Integer.parseInt(request.getParameter("price"));
        BookManageDAO dao = new BookManageDAO();
        dao.updateBook(bookID, price);
        response.sendRedirect("BookManagementServlet");
    } 
}
