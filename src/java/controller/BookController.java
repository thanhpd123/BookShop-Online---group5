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
import entity.EntityFeedback;
import jakarta.servlet.RequestDispatcher;
import java.util.Vector;
import model.DAOFeedback;

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
        DAOFeedback daoFB = new DAOFeedback();
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }

        if (service.equals("bookByCat")) {
            String cat = request.getParameter("cat");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 16;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 16 * page;
            }
            Vector<Book> vectorAll = dao.getAllBook("Select * FROM Book WHERE CategoryID = '" + cat + "'");
            Vector<Book> vector = dao.getPaginatedBookCAT(start, end, cat);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("service", "bookByCat&cat=" + cat + "");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortPriceASC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 16;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 16 * page;
            }
            Vector<Book> vectorAll = dao.getAllBook("Select * FROM Book");
            Vector<Book> vector = dao.sortPriceASC(start, end);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("service", "sortPriceASC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortPriceDESC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            Vector<Book> vectorAll = dao.getAllBook("Select * FROM Book");
            int start = 1, pageID;
            int end = 16;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 16 * page;
            }
            Vector<Book> vector = dao.sortPriceDESC(start, end);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("service", "sortPriceDESC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("FB")) {
            int page = Integer.parseInt(request.getParameter("page"));
            String bookID = request.getParameter("bookID");
            Vector<EntityFeedback> vectorAll = daoFB.getFeedback(bookID);
            Vector<Book> vectorBook = dao.getBook(bookID);
            Vector<Book> vectorC = dao.getCat(bookID, "Select BookID, BookImg, Name, Description, PublisherName, AuthorID, Edition, CategoryID, PublicationDate, Quantity, Price from Book\n"
                    + "Where BookID = '" + bookID + "'");
            String cat = vectorC.get(0).getCategoryID();
            Vector<Book> vectorCat = dao.getBookCate(cat, "SELECT TOP 4 Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                    + "FROM Book \n"
                    + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                    + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                    + "WHERE Book.CategoryID ='" + cat + "' AND BookID <> '" + bookID + "';");
            int start = 1, pageID;
            int end = 5;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }
            Vector<EntityFeedback> vectorFB = daoFB.getFeedback(start ,end ,bookID);
            request.setAttribute("dataFB", vectorFB);
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("dataBook", vectorBook);
            request.setAttribute("dataCate", vectorCat);
            request.setAttribute("data", vectorC);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookDetail.jsp");
            dis.forward(request, response);
        }

        if (service.equals("viewBook")) {
            String bookID = request.getParameter("bookID");
            Vector<EntityFeedback> vectorFB = daoFB.getFeedback(1, 5, bookID);
            Vector<Book> vectorBook = dao.getBook(bookID);
            Vector<EntityFeedback> vectorAll = daoFB.getFeedback(bookID);
            Vector<Book> vectorC = dao.getCat(bookID, "Select BookID, BookImg, Name, Description, PublisherName, AuthorID, Edition, CategoryID, PublicationDate, Quantity, Price from Book\n"
                    + "Where BookID = '" + bookID + "'");
            String cat = vectorC.get(0).getCategoryID();
            Vector<Book> vectorCat = dao.getBookCate(cat, "SELECT TOP 4 Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                    + "FROM Book \n"
                    + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                    + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID\n"
                    + "WHERE Book.CategoryID ='" + cat + "' AND BookID <> '" + bookID + "';");
            request.setAttribute("dataFB", vectorFB);
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("dataBook", vectorBook);
            request.setAttribute("data", vectorC);
            request.setAttribute("dataCate", vectorCat);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookDetail.jsp");
            dis.forward(request, response);
        }

        if (service.equals("listAll")) {
            String logged = (String) session.getAttribute("msg1");
            // call model
            Vector<Book> vectorAll = dao.getAllBook("Select * FROM Book");
            Vector<Book> vector = dao.getPaginatedBook(1, 16);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("logged", logged);
            session.setAttribute("service", "pagination");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("pagination")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 16;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 16 * page;
            }
            Vector<Book> vector = dao.getPaginatedBook(start, end);
            Vector<Book> vectorAll = dao.getAllBook("SELECT * FROM Book");
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/BookManage.jsp");
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
