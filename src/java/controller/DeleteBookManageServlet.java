package controller;

import static controller.BookManagementServlet.isNumeric;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BookManageDAO;

public class DeleteBookManageServlet extends HttpServlet {

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookID = "";
        if (request.getParameter("bookID") != null) {
            bookID = request.getParameter("bookID");
            if (!isNumeric(bookID)) {
                request.setAttribute("error", "The book ID is must be number");
                request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
            }
        }
        String status = "Disable";
        BookManageDAO dao = new BookManageDAO();
        dao.updateBook(bookID, status);
        response.sendRedirect("BookManagementServlet");
    }
}
