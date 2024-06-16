package controller;

import entity.BookManage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BookManageDAO;

public class BookManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        
        BookManageDAO dao = new BookManageDAO();
        
        List<BookManage> listBM = dao.getAllBookManage();
        dao.deleteBook(bookID);
        request.setAttribute("listBookManage", listBM);
        request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = request.getParameter("bookID");
        String bookImg = request.getParameter("bookImg");
        String name = request.getParameter("name");
        String publisherName = request.getParameter("publisherName");
        int authorID = Integer.parseInt(request.getParameter("authorID"));
        String edition = request.getParameter("edittion");
        String categoryID = request.getParameter("categoryID");
        String publicationDateString = request.getParameter("publicationDate");
        Date publicationDate = Date.valueOf(publicationDateString);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));

        BookManageDAO daoB = new BookManageDAO();
        if (daoB.checkDuplicateRollNo(bookID)) {
            request.setAttribute("error", "Roll No existing!");
        } else {
            daoB.insertBook(bookID, bookImg, name, publisherName, authorID, edition, categoryID, publicationDate, quantity, price);
        }
        doGet(request, response);
    }
}
