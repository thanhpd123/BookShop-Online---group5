/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
            String bookID = request.getParameter("bookID");
            int userID = Integer.parseInt(request.getParameter("userID"));
            Cart cart = (Cart) session.getAttribute(bookID);
            if (cart == null) {
                cart = new Cart();
                Vector<Book> vector = daoBook.getBook(bookID);
                Book bk = vector.get(0);
                String bookImg = bk.getBookImg();
                int price = bk.getPrice();
                dao.addToCart(new Cart(userID, 1, price, bookID, bookImg));
                session.setAttribute(bookID, cart);
                response.sendRedirect("BookCart?service=showCart");
                return;
            }
        }
//        if (service.equals("addToCart")) {
//            String bookID = request.getParameter("bookID");
//            CartItem cart = (CartItem) session.getAttribute(bookID);
//            if(cart == null){
//                cart = new CartItem();
//                Vector <Book> vector = dao.getBook(bookID);
//                Book bk = vector.get(0);
//                cart.setBookImg(bk.getBookImg());
//                cart.setBookID(bookID);
//                cart.setQuantity(1);
//                cart.setPrice(bk.getPrice());
//                session.setAttribute(bookID, cart);
//                response.sendRedirect("Home?service=listAll");
//                return;
//            }else{
//                cart.setQuantity(cart.getQuantity()+1);
//                session.setAttribute(bookID, cart);
//                response.sendRedirect("Home?service=listAll");
//                return;
//            }
//        }
//        if (service.equals("addToCart")) {
//            String bookID = request.getParameter("bookID");
//            Vector<Book> vector = new Vector<Book>();
//            Vector<CartItem> vectorCart = new Vector<CartItem>();
//            vector = dao.getBook(bookID);
//            vectorCart = dao.getAll("SELECT Book.BookImg, CartItem.CartID, Book.Name, CartItem.Quantity, CartItem.Price\n"
//                    + "FROM CartItem\n"
//                    + "INNER JOIN Book ON CartItem.BookID = Book.BookID");
//            int cartID, quantity;
//            cartID = vectorCart.get(0).getCartID();
//            quantity = vectorCart.get(0).getQuantity();
//            if(quantity == 0){
//                quantity = 1;
//            }else{
//                quantity = quantity + 1;
//            }
//            if (session.getAttribute("cart") == null) {
//                vectorCart.add(new CartItem(vector.get(0).getBookImg(), cartID, vector.get(0).getBookID(), quantity, vector.get(0).getPrice()));
//            } else {
//                vectorCart = (Vector<CartItem>) session.getAttribute("cart");
//                boolean checkBookID = false;
//                for(int i = 0; i < vectorCart.size(); i++){
//                    if(vectorCart.get(i).getBookID().equalsIgnoreCase(bookID)) {
//                        vectorCart.get(i).setQuantity(vectorCart.get(i).getQuantity() + 1);
//                        checkBookID = true;
//                        break;
//                    }
//                }
//                if (checkBookID == false){
//                    vectorCart.add(new CartItem(vector.get(0).getBookImg(), cartID, vector.get(0).getBookID(), 1, vector.get(0).getPrice()));
//                }
//            }
//            session.setAttribute("cart", vectorCart);
//            response.sendRedirect("Home?sevice=listAll");
////            Book bk = /*new Book()*/ (Book) session.getAttribute(bookID);
////            CartItem BkCart = (CartItem) session.getAttribute(bookID);
////            // first time get session
////            if (BkCart == null && bk == null) {
////                BkCart = new CartItem();
////                Vector<Book> vector = dao.getBook("bookID");
////                String bookImg = bk.getBookImg();
////                int cartID = BkCart.getCartID();
////                int quantity = 1;
////                int price = bk.getPrice();
////                dao.addToCart(new CartItem(bookImg, cartID, bookID, quantity, price));
////                response.sendRedirect("EmployeeController?service=listAll");
////                return;
//////                BkCart.setBookImg(bk.getBookImg());
//////                BkCart.setCartID(BkCart.getCartID());
//////                BkCart.setBookID(bookID);
//////                BkCart.setPrice(bk.getPrice());
//////                BkCart.setQuantity(1);
//////                session.setAttribute(bookID, BkCart);
//////                dao.addToCart(new CartItem(bookImg, cartID, bookID, quantity, price));
//////                response.sendRedirect("BookCart?service=showCart");
//////                return;
////            } else {
////                BkCart.setQuantity(BkCart.getQuantity() + 1);
////                session.setAttribute(bookID, BkCart);
////                response.sendRedirect("BookCart?service=showCart");
////                return;
////            }
////            String submit = request.getParameter("submit");
////            if (submit != null && submit.equals("addCart")) {
////                int cartID = Integer.parseInt(request.getParameter("CartID"));
////                String bookID = request.getParameter("BookID");
////                int quantity = Integer.parseInt(request.getParameter("Quantity"));
////                int price = Integer.parseInt(request.getParameter("Price"));
////                dao.addToCart(new CartItem(cartID, bookID, quantity, price));
////                response.sendRedirect("BookCart?service=showCart");
////                return;
////            }
////            if (submit == null) {
////
////                // call model
////                Vector<CartItem> vectorCart = dao.getAll("select * from Employee");
////                request.setAttribute("dataEmployee", vectorCart);
////                //select view (jsp)
////                RequestDispatcher dis = request.getRequestDispatcher("/jsp/AddEmployee.jsp");
////                dis.forward(request, response);
////                return;
////            }
//        }

        if (service.equals("deleteCart")) {
            String bookID = (String) request.getParameter("bookID");
            session.removeAttribute(bookID);
            response.sendRedirect("BookCart?service=showCart");
            return;
        }

        if (service.equals("showCart")) {
            Vector<Cart> vector = dao.getAll("SELECT Book.BookImg, Cart.CartID, Cart.UserID, Book.Name, Cart.Quantity, Cart.Price\n"
                    + "FROM Cart\n"
                    + "INNER JOIN Book ON Cart.BookID = Book.BookID");
            request.setAttribute("data", vector);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/ShowCart.jsp");
            dis.forward(request, response);
        }

        if (service.equals("updateQuantity")) {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {

                String bookID = parameterNames.nextElement();
                if (bookID.equals("service") || bookID.equals("submit")) {
                    continue;
                }
                int quantity = Integer.parseInt(request.getParameter(bookID));
                Cart bk = (Cart) session.getAttribute(bookID);
                bk.setQuantity(quantity);
                session.setAttribute(bookID, bk);

            }
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/ShowCart.jsp");
            dis.forward(request, response);

        }
        // delete all
        if (service.equals("deleteAll")) {
            Enumeration<String> attributeNames = request.getSession().getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
//                if (attributeName.equals("userInfo") || attributeName.equals("logged")) {
//                    continue;
//                }
                session.removeAttribute(attributeName);
            }
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/ShowCart.jsp");
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
