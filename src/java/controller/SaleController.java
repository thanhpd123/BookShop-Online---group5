/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.OrderDetail;
import entity.Orders;
import entity.ReceiverInfo;
import entity.Roles;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import model.DAOAdmin;
import model.DAOOrders;

/**
 *
 * @author Dung Dinh
 */
public class SaleController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        DAOAdmin daoAcc = new DAOAdmin();
        LocalDate current = LocalDate.now();
        String now = current.toString();
        String service = request.getParameter("service");
        String day = request.getParameter("day");
        HttpSession session = request.getSession(true);

        if (day == null) {
            day = "7";
        }

        if (service == null) {
            service = "dashboard";
        }

        if (service.equals("dashboard")) {
            Vector<Orders> vector = dao.getAll();
            Account acc = (Account) session.getAttribute("acc");
            String submit = request.getParameter("submit");
            Vector<Orders> vectorOr = new Vector<Orders>();
            String select = "Lựa Chọn";
            Vector<Orders> vectorOrByUser = new Vector<Orders>();
            vectorOrByUser = dao.getOrderByUser(1);
            String message = "nah";
            if (submit != null) {
                String UserID;
                if (request.getParameter("userID").isEmpty()) {
                    UserID = "1";
                } else {
                    UserID = request.getParameter("userID");
                }
                int userID = Integer.parseInt(UserID);
                String start = request.getParameter("start");
                String end = request.getParameter("end");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                Vector<Account> vectorUser = daoAcc.getAll();
                if (day.equals("30")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-30 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate ");
                    select = "30 Ngày Gần Nhất";
                }

                if (day.equals("365")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-365 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate ");
                    select = "365 Ngày Gần Nhất";

                }
                if (day.equals("7")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-7 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate");
                    select = "7 Ngày Gần Nhất";
                }
                if (request.getParameter("end").isEmpty()) {
                    if (request.getParameter("start").isEmpty()) {
                        message = "Vui Lòng Nhập Ngày Bắt Đầu và Ngày Kết Thúc";
//                        vectorOrByUser = dao.getOrderByUser(userID);
//                        LocalDate startDate = LocalDate.parse(vectorOrByUser.get(0).getOrderDate());
//                        request.setAttribute("startDate", formatter.format(startDate).toString());
//                        request.setAttribute("endDate", formatter.format(current).toString());
                    } else {
                        LocalDate startDate = LocalDate.parse(start);
                        message = "Vui Lòng Nhập Ngày Kết Thúc";
//                        vectorOrByUser = dao.getOrder(userID, start, now);
//                        request.setAttribute("startDate", formatter.format(startDate).toString());
//                        request.setAttribute("endDate", formatter.format(current).toString());
                    }
                }
                if (request.getParameter("start").isEmpty()) {
                    message = "Vui Lòng Nhập Ngày Bắt Đầu";
                } else {
                    message = "correct";
                    vectorOrByUser = dao.getOrder(userID, start, end);
                    LocalDate endDate = LocalDate.parse(end);
                    LocalDate startDate = LocalDate.parse(start);
                    request.setAttribute("startDate", formatter.format(startDate).toString());
                    request.setAttribute("endDate", formatter.format(endDate).toString());
                }
                Vector<OrderDetail> vectorOrder = dao.getOrderDetail("SELECT OrderDetail.OrderID, OrderDetail.BookID, OrderDetail.Quantity, OrderDetail.Price, OrderDetail.Payment, OrderDetail.UserID\n"
                        + "FROM OrderDetail\n"
                        + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                        + "WHERE Orders.OrderState = N'Đã giao hàng'");
                Vector<Orders> vectorNew = dao.getNewestOrder();
                request.setAttribute("vectorNew", vectorNew);
                request.setAttribute("vectorOrder", vectorOrder);
                request.setAttribute("select", select);
                request.setAttribute("dataOrder", vector);
                request.setAttribute("dataOr", vectorOr);
//                request.setAttribute("mess", message);
                request.setAttribute("dataOrByUser", vectorOrByUser);
                request.setAttribute("day", day);
                request.setAttribute("data", vectorUser);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/SaleDashboard.jsp");
                dis.forward(request, response);
            }
            if (submit == null) {
                Vector<Account> vectorUser = daoAcc.getAll();
                if (day.equals("30")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-30 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate ");
                    select = "30 Ngày Gần Nhất";
                }

                if (day.equals("365")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-365 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate ");
                    select = "365 Ngày Gần Nhất";

                }
                if (day.equals("7")) {
                    vectorOr = dao.getOrder("SELECT Orders.OrderDate , Orders.OrderState, SUM(OrderDetail.Quantity) AS Quantity, SUM(OrderDetail.Price) AS Price\n"
                            + "FROM Orders\n"
                            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                            + "WHERE Orders.OrderDate BETWEEN GETDATE()-7 AND GETDATE() AND Orders.OrderState = N'Đã giao hàng'\n"
                            + "GROUP By Orders.OrderDate, Orders.OrderState\n"
                            + "ORDER BY Orders.OrderDate");
                    select = "7 Ngày Gần Nhất";
                }
                Vector<OrderDetail> vectorOrder = dao.getAllOrderDetail();
                Vector<Orders> vectorNew = dao.getNewestOrder();
                request.setAttribute("vectorNew", vectorNew);
                session.setAttribute("acc", acc);
                request.setAttribute("vectorOrder", vectorOrder);
                request.setAttribute("select", select);
                request.setAttribute("dataOrder", vector);
//                request.setAttribute("mess", message);
                request.setAttribute("dataOr", vectorOr);
                request.setAttribute("dataOrByUser", vectorOrByUser);
                request.setAttribute("day", day);
                request.setAttribute("data", vectorUser);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/SaleDashboard.jsp");
                dis.forward(request, response);
            }
        }

        if (service.equals("filter")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            String submit = request.getParameter("submit");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            Vector<Orders> vectorList = new Vector<Orders>();
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            if (submit != null && submit.equals("Tra Cứu")) {
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String startPrice = request.getParameter("startPrice");
                String endPrice = request.getParameter("endPrice");
                String[] status = request.getParameterValues("status");

                if (status != null && startDate == null) {

                    Vector<Orders> vector = dao.getUserID();
                    Vector<Orders> vectorAll = dao.getAllOrderList();
                    request.setAttribute("dataAll", vectorAll);
                    request.setAttribute("data", vector);
                }

            }
            if (submit == null) {
                vectorList = dao.getOrderList(1, 7);
            }
            Vector<Orders> vectorAll = dao.getAllOrderList();
            Vector<Orders> vector = dao.getUserID();
            request.setAttribute("dataList", vectorList);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("acc", acc);
            session.setAttribute("service", "filter");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("orderList")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            Vector<Orders> vectorList = dao.getOrderList(1, 7);
            Vector<Orders> vectorAll = dao.getAllOrderList();
            Vector<Orders> vector = dao.getUserID();
            request.setAttribute("dataList", vectorList);
            request.setAttribute("data", vector);
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("acc", acc);
            session.setAttribute("service", "pagination");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortPriceDESC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY SUM(OrderDetail.Price) DESC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortPriceDESC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("filter")) {
            String fil = request.getParameter("type");
            String type = "";
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            if (fil.equals("1")) {
                type = "Đang xử lý";
            }
            if (fil.equals("2")) {
                type = "Đang giao hàng";
            }
            if (fil.equals("3")) {
                type = "Đã giao hàng";
            }
            if (fil.equals("4")) {
                type = "Đã hủy";
            }
            Vector<Orders> vectorAll = dao.filter(fil, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.ID) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "	WHERE Orders.OrderState = N'" + fil + "'\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery");
            Vector<Orders> vectorfilter = dao.filter(fil, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.ID) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "	WHERE Orders.OrderState = N'" + fil + "'\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "'");
            Vector<Orders> vector = dao.getUserID();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorfilter);
            session.setAttribute("service", "filter&type=" + fil + "");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortPriceASC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY SUM(OrderDetail.Price) ASC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortPriceASC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDDESC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.ID DESC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortIDDESC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortStateDESC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.OrderState DESC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortStateDESC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortStateASC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.OrderState ASC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortStateASC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDASC")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorSort = dao.sort(start, end, "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        SUM(OrderDetail.Price) AS Price, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Orders.ID ASC) AS RowNum\n"
                    + "    FROM Orders\n"
                    + "    INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                    + "    INNER JOIN Account ON OrderDetail.UserID = Account.UserID\n"
                    + "    GROUP BY \n"
                    + "        Orders.ID, \n"
                    + "        Orders.OrderDate, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Orders.OrderState, \n"
                    + "        Orders.UserID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            request.setAttribute("dataList", vectorSort);
            session.setAttribute("service", "sortIDASC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            //RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
            dis.forward(request, response);
        }

        if (service.equals("pagination")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            Vector<Orders> vectorList = dao.getOrderList(start, end);
            Vector<Orders> vector = dao.getUserID();
            Vector<Orders> vectorAll = dao.getAllOrderList();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("dataList", vectorList);
            request.setAttribute("data", vector);
            session.setAttribute("acc", acc);
            session.setAttribute("service", "pagination");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("search")) {
            Account acc = (Account) session.getAttribute("acc");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 7;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 7 * page;
            }
            int userID = acc.getUserID();
            String search = request.getParameter("search");
            Vector<Orders> vectorAll = dao.searchOrderList(search);
            Vector<Orders> vectorList = dao.searchOrderList(search, start, end);
            Vector<Orders> vector = dao.searchUserID(search);
            request.setAttribute("dataList", vectorList);
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vector);
            session.setAttribute("service", "search");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("changeStatus")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            String state = request.getParameter("state");
            String order = request.getParameter("order");
            int ID = Integer.parseInt(request.getParameter("id"));
            String status = "";
            if (state.equals("1")) {
                status = "Đang xử lý";
            }
            if (state.equals("2")) {
                status = "Đang giao hàng";
            }
            if (state.equals("3")) {
                status = "Đã giao hàng";
            }
            if (state.equals("4")) {
                status = "Đã hủy";
            }
            dao.updateState(status, ID);
            dao.updateSaleID(userID, ID);
            if (order.equals("orderlist")) {
                response.sendRedirect("SaleController?service=orderList");
                return;
            }
            if (order.equals("orderdetail")) {
                response.sendRedirect("SaleController?service=orderDetail&id=" + ID + "&userID=" + userID + "");
                return;
            }
        }

        if (service.equals("deactivate")) {
            Account acc = (Account) session.getAttribute("acc");
            int userID = acc.getUserID();
            String order = request.getParameter("order");
            String status = "Đã hủy";
            int ID = Integer.parseInt(request.getParameter("id"));
            dao.updateState(status, ID);
            if (order.equals("orderlist")) {
                response.sendRedirect("SaleController?service=orderList");
                return;
            }
            if (order.equals("orderdetail")) {
                response.sendRedirect("SaleController?service=orderDetail&id=" + ID + "&userID=" + userID + "");
                return;
            }
        }

        if (service.equals("orderDetail")) {
            Account acc = (Account) session.getAttribute("acc");
            int UserID = Integer.parseInt(request.getParameter("userID"));
            int iD = Integer.parseInt(request.getParameter("id"));
            Vector<Account> vectorUser = dao.getAccount(UserID);
            Vector<ReceiverInfo> vectorRcv = dao.getReceiverAdr(iD);
            Vector<Orders> vectorOrderInfo = dao.getOrderInfo(iD);
            Vector<OrderDetail> vectorOrderDtl = dao.getOrderDetail(iD);
            session.setAttribute("acc", acc);
            request.setAttribute("dataUser", vectorUser);
            request.setAttribute("dataOrdDtl", vectorOrderDtl);
            request.setAttribute("dataRcv", vectorRcv);
            request.setAttribute("dataOrd", vectorOrderInfo);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/OrderDetail.jsp");
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
