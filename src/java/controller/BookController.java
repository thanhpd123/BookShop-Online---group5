/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOBook;
import entity.Book;
import jakarta.servlet.RequestDispatcher;
import java.util.Vector;

/**
 *
 * @author Dung Dinh
 */
@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

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
        DAOBook dao = new DAOBook();
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        
        if (service.equals("bookByCat")) {
            String cat = request.getParameter("cat");
            Vector<Book> vector = dao.getBookCat(cat);
            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            dis.forward(request, response);
        }
        
        if (service.equals("sortPriceASC")) {
            Vector<Book> vector = dao.sortPriceASC();
            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }
        
        if (service.equals("sortPriceDESC")) {
            Vector<Book> vector = dao.sortPriceDESC();
            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }
        
        if (service.equals("viewBook")) {
            String bookID = request.getParameter("bookID");
            Vector<Book> vectorBook = dao.getBook(bookID);
            Vector<Book> vectorC = dao.getCat(bookID, "Select BookID, BookImg, Name, Description, PublisherName, AuthorID, Edition, CategoryID, PublicationDate, Quantity, Price from Book\n"
                    + "Where BookID = '" + bookID + "'");
            String cat = vectorC.get(0).getCategoryID();
            Vector<Book> vectorCat = dao.getBookCate(cat, "SELECT TOP 4 Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                    + "FROM Book \n"
                    + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                    + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                    + "WHERE Book.CategoryID ='" + cat + "' AND BookID <> '"+ bookID +"';");
            request.setAttribute("dataBook", vectorBook);
            request.setAttribute("data", vectorC);
            request.setAttribute("dataCate", vectorCat);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookDetail.jsp");
            dis.forward(request, response);
        }
        
        if (service.equals("listAll")) {
            // call model
            Vector<Book> vector = dao.getAllBook("select Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                    + "from Book \n"
                    + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                    + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID;");
////             set data to view
//            Vector<Book> vector = dao.getBook("B4");
//            vector = dao.getBook("B7");

            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }
        
        if (service.equals("search")) {
            String name = request.getParameter("Name");
            Vector<Book> vector = dao.searchName(name);
            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
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
