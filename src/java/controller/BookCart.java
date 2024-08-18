/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.Address;
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
import java.time.LocalDate;
import model.DAOCart;
import entity.Book;
import entity.OrderDetail;
import entity.Orders;
import entity.ReceiverInfo;
import javax.mail.MessagingException;
import model.DAOBook;
import model.Email;
import model.DAOOrders;

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
        DAOOrders daoOrder = new DAOOrders();
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
                response.sendRedirect("BookController?service=viewBook&bookID=" + bookID + "&page=1");
                return;
            }
            if (n != 0) {
                for (Cart cart : vectorCart) {
                    int quant = cart.getQuantity() + 1;
                    dao.updateQuantity(cart.getCartID(), userID, bookID, quant, cart.getPrice());
                    //session.setAttribute(bookID, cart);
                    response.sendRedirect("BookController?service=viewBook&bookID=" + bookID + "&page=1");
                    return;
                }
            }
        }

        if (service.equals("updateQuantity")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity < 0) {
                quantity = 1;
            }
            int userID = Integer.parseInt(request.getParameter("userID"));
            String bookID = request.getParameter("bookID");
            Vector<Cart> vector = dao.getCart(bookID, userID);
            dao.updateQuantity(vector.get(0).getCartID(), userID, bookID, quantity, vector.get(0).getPrice());
            response.sendRedirect("BookCart?service=showCart&userID=" + userID + "");
            return;
        }
        
        if (service.equals("cancel")) {
            int id = Integer.parseInt(request.getParameter("ID"));
            daoOrder.updateState("Đã hủy", id);
            response.sendRedirect("ThisOrderServlet?ID=" + id + "");
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
            String way = request.getParameter("way");
            String address = (String) session.getAttribute("address");
            Vector<ReceiverInfo> vectorReceiverInfo = daoOrder.getReceiverInfo(userID);
            if (address == null) {
                address = String.valueOf(vectorReceiverInfo.get(0).getID());
            }
            String name = " ";
            if (way.equals("Payment")) {
                name = "<i class=\"bi bi-cart-check\"></i> Phương Thức Thanh Toán";
            }

            if (way.equals("card")) {
                name = "<i class=\"bi bi-credit-card\"></i> Thẻ Tín Dụng / Ghi Nợ";
            }

            if (way.equals("ck")) {
                name = "<i class=\"bi bi-credit-card-2-front\"></i> Chuyển Khoản";
            }

            if (way.equals("cod")) {
                name = "<i class=\"bi bi-cash-stack\"></i> Thanh Toán Khi Nhận Hàng";
            }
            String ReceiverAdr = address;
            Vector<Cart> vectorC = dao.getAll("SELECT Book.BookImg, Cart.CartID, Cart.UserID, Book.Name, Cart.Quantity, Cart.Price\n"
                    + "FROM Cart\n"
                    + "INNER JOIN Book ON Cart.BookID = Book.BookID\n"
                    + "WHERE Cart.UserID = '" + userID + "';");
            Vector<Orders> vectorOr = daoOrder.getAll();
            int last;
            if (vectorOr.isEmpty()) {
                last = 0;
            } else {
                last = vectorOr.lastElement().getID();
            }
            int ID = last + 1;
            Vector<ReceiverInfo> vectorReceiverAdr = daoOrder.getReceiverAdr(userID, address);
            request.setAttribute("dataReceiverInfo", vectorReceiverInfo);
            request.setAttribute("dataReceiverAdr", vectorReceiverAdr);
            request.setAttribute("dataCart", vectorC);
            request.setAttribute("name", name);
            request.setAttribute("ID", ID);
            request.setAttribute("ReceiverAdr", ReceiverAdr);
            request.setAttribute("payment", way);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/CheckOut.jsp");
            dis.forward(request, response);
            request.getRequestDispatcher("BookCart?service=addAddress").forward(request, response);
            request.getRequestDispatcher("BookCart?service=payment").forward(request, response);
            request.getRequestDispatcher("BookCart?service=address").forward(request, response);
        }

        // delete all
        if (service.equals("deleteAll")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            dao.removeAll(userID);
            response.sendRedirect("BookCart?service=showCart");
            return;
        }

        if (service.equals("payment")) {
            Account acc = (Account) session.getAttribute("acc");
            String payment = request.getParameter("payment");
            String address = request.getParameter("address");
            int RID = Integer.parseInt(address);
            int userID = acc.getUserID();
            LocalDate current = LocalDate.now();
            String now = current.toString();
            String state = "Đang xử lý";
            Vector<Orders> vectorOr = daoOrder.getAll();
            Vector<Cart> vectorCart = dao.getAllCart("SELECT * FROM Cart\n"
                    + "WHERE UserID = '" + userID + "'");
            int n = vectorCart.size();
            int last;
            if (vectorOr.isEmpty()) {
                last = 0;
            } else {
                last = vectorOr.lastElement().getID();
            }
            int ID = last + 1;
            for (int i = 0; i < n; i++) {
                Vector<OrderDetail> vector = daoOrder.getAllOrderDetail();
                int lastE;
                if (vector.isEmpty()) {
                    lastE = 0;
                } else {
                    lastE = vector.lastElement().getOrderID();
                }
                int orderID = lastE + 1;
                int price = vectorCart.get(i).getQuantity() * vectorCart.get(i).getPrice();
                daoOrder.addOrder(new Orders(orderID, now, state, RID, ID));
                daoOrder.addOrderDetail(new OrderDetail(orderID, vectorCart.get(i).getBookID(), vectorCart.get(i).getQuantity(), price, payment, userID));
                daoOrder.deleteCart(vectorCart.get(i).getBookID(), userID);
            }
            Email em = new Email();
            try {
                em.send(ID);
            } catch (MessagingException e) {
                e.printStackTrace();  
            }
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/Check.jsp");
            dis.forward(request, response);
        }

        if (service.equals("address")) {
            String submit = request.getParameter("submit");
            String way = request.getParameter("way");
            String name = request.getParameter("name");
            if (way == null) {
                way = "Payment";
            }
            if (submit != null && submit.equals("Thay Đổi")) {
                String address = request.getParameter("address");
                session.setAttribute("address", address);
                request.setAttribute("name", name);
                RequestDispatcher dis = request.getRequestDispatcher("BookCart?service=checkOut&way=" + way + "&address=" + address + "");
                dis.forward(request, response);
            }
        }

        if (service.equals("addAddress")) {
            Account acc = (Account) session.getAttribute("acc");
            String way = request.getParameter("payment");
            if (way == null) {
                way = "Payment";
            }
            int userID = acc.getUserID();
            String submit = request.getParameter("submit");
            if (submit != null && submit.equals("Thêm Địa Chỉ")) {
                String name = request.getParameter("Name");
                String phoneNo = request.getParameter("PhoneNo");
                String address = request.getParameter("Address");
                daoOrder.addReceiverInfo(new ReceiverInfo(name, phoneNo, address, userID));
                RequestDispatcher dis = request.getRequestDispatcher("BookCart?service=checkOut&way=" + way + "");
                dis.forward(request, response);
            }
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
