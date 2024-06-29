/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.Book;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Cart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOCart;
import entity.Book;
import model.DAOBook;

/**
 *
 * @author Dung Dinh
 */
@WebServlet(name = "BookCart", urlPatterns = {"/BookCart"})
public class BookCart extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        DAOCart dao = new DAOCart();
        DAOBook daoBook = new DAOBook();
        String service = request.getParameter("service");
        if (service == null) {
            service = "showCart";
        }

        if (service.equals("addToCart")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String bookID = request.getParameter("bookID");
            //Cart cart = (Cart) session.getAttribute(bookID);
            Vector<Cart> vectorCart = dao.getCart(bookID, userID);
            int n = vectorCart.size();
            if (n == 0) {
                Cart cart = new Cart();
                Vector<Book> vector = daoBook.getBook(bookID);
                Book bk = vector.get(0);
                int price = bk.getPrice();
                dao.addToCart(new Cart(userID, bookID, 1, price));
                session.setAttribute(bookID, cart);
                response.sendRedirect("BookController?service=viewBook&bookID=" + bookID + "");
                return;
            }
            if (n != 0) {
                for (Cart cart : vectorCart) {
                    int quant = cart.getQuantity() + 1;
                    dao.updateQuantity(cart.getCartID(), userID, bookID, quant, cart.getPrice());
                    //session.setAttribute(bookID, cart);
                    response.sendRedirect("BookController?service=viewBook&bookID=" + bookID + "");
                    return;
                }
            }
        }

        if (service.equals("updateQuantity")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int userID = Integer.parseInt(request.getParameter("userID"));
            String bookID = request.getParameter("bookID");
            Vector<Cart> vector = dao.getCart(bookID, userID);
            for (Cart cart : vector) {
                dao.updateQuantity(cart.getCartID(), cart.getUserID(), cart.getBookID(), quantity, cart.getPrice());
                response.sendRedirect("BookCart?service=showCart");
                return;
            }
        }

        if (service.equals("deleteCart")) {
            String bookID = (String) request.getParameter("bookID");
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            dao.removeCart(bookID, userID);
            response.sendRedirect("BookCart?service=showCart");
            return;
        }

        if (service.equals("changeQuantityPlus")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String bookID = request.getParameter("bookID");
            Vector<Cart> vector = dao.getCart(bookID, userID);
            for (Cart cart : vector) {
                int quant = cart.getQuantity() + 1;
                dao.updateQuantity(cart.getCartID(), userID, bookID, quant, cart.getPrice());
                response.sendRedirect("BookCart?service=showCart&userID=" + userID + "");
                return;
            }
        }

        if (service.equals("changeQuantityMinus")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String bookID = request.getParameter("bookID");
            Vector<Cart> vector = dao.getCart(bookID, userID);
            for (Cart cart : vector) {
                int quant;
                if (cart.getQuantity() != 1) {
                    quant = cart.getQuantity() - 1;
                    dao.updateQuantity(cart.getCartID(), userID, bookID, quant, cart.getPrice());
                    response.sendRedirect("BookCart?service=showCart&userID=" + userID + "");
                    return;
                }
                if (cart.getQuantity() == 1) {
                    quant = cart.getQuantity();
                    dao.updateQuantity(cart.getCartID(), userID, bookID, quant, cart.getPrice());
                    response.sendRedirect("BookCart?service=showCart&userID=" + userID + "");
                    return;
                }
            }
        }

        if (service.equals("showCart")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            Vector<Cart> vector = dao.getAll("SELECT Book.BookImg, Cart.CartID, Cart.UserID, Book.Name, Cart.Quantity, Cart.Price\n"
                    + "FROM Cart\n"
                    + "INNER JOIN Book ON Cart.BookID = Book.BookID\n"
                    + "WHERE Cart.UserID = '" + userID + "';");
            Vector<Cart> vectorB = dao.getAllCart("SELECT * FROM Cart\n"
                    + "WHERE UserID = '" + userID + "';");
            //Vector<Book> vectorBk = dao.getAllBook();
            request.setAttribute("data", vector);
            request.setAttribute("dataB", vectorB);
            session.setAttribute("acc", acc);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/ShowCart.jsp");
            dis.forward(request, response);
        }

        if (service.equals("checkOut")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            Vector<Cart> vectorC = dao.getAll("SELECT Book.BookImg, Cart.CartID, Cart.UserID, Book.Name, Cart.Quantity, Cart.Price\n"
                    + "FROM Cart\n"
                    + "INNER JOIN Book ON Cart.BookID = Book.BookID\n"
                    + "WHERE Cart.UserID = '" + userID + "';");
            Vector<Account> vectorA = dao.getAll(userID);
            request.setAttribute("dataAddress", vectorA);
            request.setAttribute("dataCart", vectorC);

            RequestDispatcher dis = request.getRequestDispatcher("/jsp/CheckOut.jsp");
            dis.forward(request, response);
        }

        if (service.equals("payment")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            Vector<Cart> vectorC = dao.getAll("SELECT Book.BookImg, Cart.CartID, Cart.UserID, Book.Name, Cart.Quantity, Cart.Price\n"
                    + "FROM Cart\n"
                    + "INNER JOIN Book ON Cart.BookID = Book.BookID\n"
                    + "WHERE Cart.UserID = '" + userID + "';");
            Vector<Account> vectorA = dao.getAll(userID);
            request.setAttribute("dataAddress", vectorA);
            request.setAttribute("dataCart", vectorC);

            RequestDispatcher dis = request.getRequestDispatcher("/jsp/Payment.jsp");
            dis.forward(request, response);
        }

        // delete all
        if (service.equals("deleteAll")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            dao.removeAll(userID);
            response.sendRedirect("BookCart?service=showCart");
            return;
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
