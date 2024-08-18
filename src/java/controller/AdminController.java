/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.Book;
import entity.EntityFeedback;
import entity.OrderDetail;
import entity.Orders;
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
import model.DAOAdmin;
import java.time.LocalDate;
import java.time.Period;
import javax.mail.MessagingException;
import model.DAOBook;
import model.DAOFeedback;
import model.DAOOrders;
import model.EmailNoti;

/**
 *
 * @author Dung Dinh
 */
public class AdminController extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin();
        DAOBook daoBk = new DAOBook();
        DAOFeedback daoFB = new DAOFeedback();
        DAOOrders daoOr = new DAOOrders();
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listUser";
        }

        if (service.equals("changeRole")) {
            String roleID = request.getParameter("roleID");
            String type = request.getParameter("type");
            int userID = Integer.parseInt(request.getParameter("userID"));
            dao.updateRole(roleID, userID);
            if (type.equals("userlist")) {
                response.sendRedirect("AdminController?service=listUser");
                return;
            }
            if (type.equals("userdetail")) {
                response.sendRedirect("AdminController?service=viewUser&userID=" + userID + "");
                return;
            }
        }

        if (service.equals("deactivate")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            dao.updateStatus("suspended", userID);
            response.sendRedirect("AdminController?service=listUser");
            return;
        }

        if (service.equals("listUser")) {
            Account acc = (Account) session.getAttribute("acc");
            Vector<Account> vectorUser = dao.getAll(1, 5);
            Vector<Account> vectorAll = dao.getAll();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            request.getRequestDispatcher("/jsp/AdminManage.jsp");
            session.setAttribute("service", "pagination");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("pagination")) {
            Account acc = (Account) session.getAttribute("acc");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 5;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }
            Vector<Account> vectorUser = dao.getAll(start, end);
            Vector<Account> vectorAll = dao.getAll();
            request.setAttribute("dataAll", vectorAll);
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            session.setAttribute("acc", acc);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDDESC")) {
            Account acc = (Account) session.getAttribute("acc");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 5;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }
            Vector<Account> vectorUser = dao.sort(start, end, "SELECT * \n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Account.UserID, \n"
                    + "        Roles.RoleName, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Account.Email, \n"
                    + "        Account.Password, \n"
                    + "        Account.PhoneNo, \n"
                    + "        Account.Address, \n"
                    + "        Account.Gender, \n"
                    + "        Account.DOB, \n"
                    + "        Account.imgUser, \n"
                    + "        Account.Status, \n"
                    + "        Account.Created_Date, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Account.UserID DESC) AS RowNum\n"
                    + "    FROM Account\n"
                    + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            Vector<Account> vectorAll = dao.getAll();
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("service", "sortIDDESC");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDASC")) {
            Account acc = (Account) session.getAttribute("acc");
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 5;
            if (page == 1) {
            }
            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }
            Vector<Account> vectorUser = dao.sort(start, end, "SELECT * \n"
                    + "FROM (\n"
                    + "    SELECT \n"
                    + "        Account.UserID, \n"
                    + "        Roles.RoleName, \n"
                    + "        Account.FirstName, \n"
                    + "        Account.LastName, \n"
                    + "        Account.Email, \n"
                    + "        Account.Password, \n"
                    + "        Account.PhoneNo, \n"
                    + "        Account.Address, \n"
                    + "        Account.Gender, \n"
                    + "        Account.DOB, \n"
                    + "        Account.imgUser, \n"
                    + "        Account.Status, \n"
                    + "        Account.Created_Date, \n"
                    + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                    + "    FROM Account\n"
                    + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                    + ") AS SubQuery\n"
                    + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
            Vector<Account> vectorAll = dao.getAll();
            request.setAttribute("dataAll", vectorAll);
            session.setAttribute("service", "sortIDASC");
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("filter")) {
            Account acc = (Account) session.getAttribute("acc");
            int page = Integer.parseInt(request.getParameter("page"));
            String type = request.getParameter("type");
            int start = 1, pageID;
            Vector<Account> vectorUser = new Vector<Account>();
            int end = 5;
            if (page == 1) {
            }

            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }

            if (type.equals("GenderM")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE GENDER = '1'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.Gender = '1'");
                request.setAttribute("dataAll", vectorAll);
                session.setAttribute("service", "filter&type=GenderM");
            }

            if (type.equals("GenderF")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE GENDER = '0'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.Gender = '0'");
                session.setAttribute("service", "filter&type=GenderF");
                request.setAttribute("dataAll", vectorAll);
            }

            if (type.equals("Admin")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.RoleID = '1'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.RoleID = '1'");
                session.setAttribute("service", "filter&type=Admin");
                request.setAttribute("dataAll", vectorAll);
            }

            if (type.equals("Staff")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.RoleID = '2'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.RoleID = '2'");
                session.setAttribute("service", "filter&type=Staff");
                request.setAttribute("dataAll", vectorAll);
            }

            if (type.equals("Customer")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.RoleID = '3'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.RoleID = '3'");
                session.setAttribute("service", "filter&type=Customer");
                request.setAttribute("dataAll", vectorAll);
            }

            if (type.equals("Sale")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.RoleID = '4'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.RoleID = '4'");
                session.setAttribute("service", "filter&type=Sale");
                request.setAttribute("dataAll", vectorAll);
            }

            if (type.equals("Ship")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.RoleID = '5'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.RoleID = '5'");
                session.setAttribute("service", "filter&type=Ship");
                request.setAttribute("dataAll", vectorAll);
            }
            if (type.equals("Active")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "    WHERE Account.Status = 'active'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.Status = 'active'");
                session.setAttribute("service", "filter&type=Active");
                request.setAttribute("dataAll", vectorAll);
            }
            if (type.equals("Suspended")) {
                vectorUser = dao.filter(start, end, "SELECT * \n"
                        + "FROM (\n"
                        + "    SELECT \n"
                        + "        Account.UserID, \n"
                        + "        Roles.RoleName, \n"
                        + "        Account.FirstName, \n"
                        + "        Account.LastName, \n"
                        + "        Account.Email, \n"
                        + "        Account.Password, \n"
                        + "        Account.PhoneNo, \n"
                        + "        Account.Address, \n"
                        + "        Account.Gender, \n"
                        + "        Account.DOB, \n"
                        + "        Account.imgUser, \n"
                        + "        Account.Status, \n"
                        + "        Account.Created_Date, \n"
                        + "        ROW_NUMBER() OVER (ORDER BY Account.UserID ASC) AS RowNum\n"
                        + "    FROM Account\n"
                        + "    INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "	WHERE Account.Status = 'suspended'\n"
                        + ") AS SubQuery\n"
                        + "WHERE RowNum BETWEEN '" + start + "' AND '" + end + "';");
                Vector<Account> vectorAll = dao.getAllFilter("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status, Account.Created_Date\n"
                        + "FROM Account\n"
                        + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                        + "WHERE Account.Status = 'suspended'");
                session.setAttribute("service", "filter&type=Suspended");
                request.setAttribute("dataAll", vectorAll);
            }
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("getGender")) {
            Account acc = (Account) session.getAttribute("acc");
            int gender = Integer.parseInt(request.getParameter("gender"));
            Vector<Account> vectorUser = dao.get("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
                    + "FROM Account\n"
                    + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                    + "WHERE Account.Gender = '" + gender + "'");
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            request.getRequestDispatcher("/jsp/AdminManage.jsp");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("dashboard")) {
            String submit = request.getParameter("submit");
            Vector<EntityFeedback> vectorFB = daoFB.getAllFeedback();
            Vector<Orders> vectorOr = daoOr.getOrderDash();
            Vector<Orders> vectorSale = dao.getDashboard();
            int[] userIDs = new int[vectorSale.size()];
            int index = 0;
            for (Orders or : vectorSale) {
                userIDs[index++] = or.getUserID();
            }
            int maxcount = 0;
            int element_having_max_freq = 0;
            for (int i = 0; i < vectorSale.size(); i++) {
                int count = 0;
                for (int j = 0; j < vectorSale.size(); j++) {
                    if (userIDs[i] == userIDs[j]) {
                        count++;
                    }
                }

                if (count > maxcount) {
                    maxcount = count;
                    element_having_max_freq = userIDs[i];
                }
            }
            int mincount = 1000000000;
            int element_having_min_freq = -1;
            for (int i = 0; i < vectorSale.size(); i++) {
                int count = 0;
                for (int j = 0; j < vectorSale.size(); j++) {
                    if (userIDs[i] == userIDs[j]) {
                        count++;
                    }
                }

                if (count < mincount) {
                    mincount = count;
                    element_having_min_freq = userIDs[i];
                }
            }
            Vector<Book> vectorMax = dao.getMax();
            Vector<Book> vectorMin = dao.getMin();
            Vector<Orders> vectorSaleMax = dao.getDashboardDetail(element_having_max_freq);
            Vector<Orders> vectorSaleMin = dao.getDashboardDetail(element_having_min_freq);
            request.setAttribute("dataFB", vectorFB);
            request.setAttribute("mincount", mincount);
            request.setAttribute("maxcount", maxcount);
            request.setAttribute("dataOr", vectorOr);
            request.setAttribute("dataMax", vectorSaleMax);
            request.setAttribute("dataMin", vectorSaleMin);
            request.setAttribute("Max", vectorMax);
            request.setAttribute("Min", vectorMin);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/Dashboard.jsp");
            dis.forward(request, response);

        }

        if (service.equals("search")) {
            int page = Integer.parseInt(request.getParameter("page"));
            int start = 1, pageID;
            int end = 5;
            if (page == 1) {
            }

            if (page != 1) {
                pageID = page - 1;
                start = pageID * end + 1;
                end = 5 * page;
            }
            String search = request.getParameter("search");
            if (search == null || search.isEmpty()) {
                Account acc = (Account) session.getAttribute("acc");
                Vector<Account> vectorUser = dao.getAll(1, 5);
                Vector<Account> vectorAll = dao.getAll();
                request.setAttribute("dataAll", vectorAll);
                request.setAttribute("data", vectorUser);
                session.setAttribute("service", "pagination");
                request.setAttribute("titleTable", "Danh sách Khách Hàng");
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
                dis.forward(request, response);
            } else {
                Vector<Account> vector = dao.search(start, end, search);
                Vector<Account> vectorAll = dao.search(search);
                request.setAttribute("dataAll", vectorAll);
                request.setAttribute("data", vector);
                session.setAttribute("service", "search&search=" + search + "");
                request.setAttribute("titleTable", "Danh sách Khách Hàng");
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
                dis.forward(request, response);
            }
        }

        if (service.equals("getRole")) {
            Account acc = (Account) session.getAttribute("acc");
            Vector<Account> vectorUser = dao.get("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
                    + "FROM Account\n"
                    + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                    + "ORDER BY UserID ASC");
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            request.getRequestDispatcher("/jsp/AdminManage.jsp");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("viewUser")) {
            LocalDate curDate = LocalDate.now();
            Account acc = (Account) session.getAttribute("acc");
            int userID = Integer.parseInt(request.getParameter("userID"));
            Vector<Account> vector = dao.getAcc(userID);
            Vector<Orders> vectorNew = dao.getNewestOrder(userID);
            Vector<OrderDetail> vectorOr = new Vector<OrderDetail>();
            for (Orders or : vectorNew) {
                vectorOr = daoOr.getOrderDetail("SELECT OrderDetail.OrderID, Book.Name, OrderDetail.Quantity, OrderDetail.Price, OrderDetail.Payment, OrderDetail.UserID\n"
                        + "FROM OrderDetail\n"
                        + "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID\n"
                        + "INNER JOIN Book ON OrderDetail.BookID = Book.BookID\n"
                        + "WHERE Orders.ID = '" + or.getID() + "' AND OrderDetail.UserID = '" + userID + "'");
            }
            String dob = vector.get(0).getDOB();
            request.setAttribute("dataOrder", vectorOr);
            LocalDate dob1 = LocalDate.parse(dob);
            int age = Period.between(dob1, curDate).getYears();
            request.setAttribute("data", vector);
            request.setAttribute("dataNew", vectorNew);
            request.setAttribute("age", age);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserDetail.jsp");
            dis.forward(request, response);
        }

        if (service.equals("add")) {
            String submit = request.getParameter("submit");
            LocalDate current = LocalDate.now();
            if (submit != null && submit.equals("Thêm Tài Khoản")) {
                String RoleID = request.getParameter("RoleID");
                String FName = request.getParameter("FirstName");
                String LName = request.getParameter("LastName");
                String Email = request.getParameter("Email");
                String PW = request.getParameter("Password");
                String PhoneNo = request.getParameter("PhoneNo");
                String Address = request.getParameter("Address");
                boolean Gender = Boolean.parseBoolean(request.getParameter("Gender"));
                String DOB = request.getParameter("DOB");
                String now = current.toString();
                dao.addAccount(new Account(RoleID, FName, LName, Email, PW, PhoneNo, Address, DOB, Gender, "active", now));
                EmailNoti em = new EmailNoti();
                try {
                    em.sendEmail();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                response.sendRedirect("AdminController?service=listUser");
                return;
            }
            if (submit == null) {
                Vector<Account> vectorUser = dao.getAll(1, 5);
                Vector<Roles> vectorRole = dao.getAllRole();
                request.setAttribute("data", vectorUser);
                request.setAttribute("dataRole", vectorRole);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/AddAccount.jsp");
                dis.forward(request, response);
            }
        }

        if (service.equals("update")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String submit = request.getParameter("submit");
            if (submit != null && submit.equals("Chỉnh Sửa")) {
                String RoleID = request.getParameter("RoleID");
                String FName = request.getParameter("FirstName");
                String LName = request.getParameter("LastName");
                String Email = request.getParameter("Email");
                String PW = request.getParameter("PassWord");
                String PhoneNo = request.getParameter("PhoneNo");
                String Address = request.getParameter("Address");
                boolean Gender = Boolean.parseBoolean(request.getParameter("Gender"));
                String DOB = request.getParameter("DOB");
                String imgUser = request.getParameter("imgUser");
                dao.updateAcc(new Account(RoleID, FName, LName, Email, PW, PhoneNo, Address, DOB, Gender, imgUser), userID);
                response.sendRedirect("AdminController?service=listUser");
                return;
            }
            if (submit == null) {
                Vector<Account> vectorUserInfo = dao.getAcc(userID);
                Vector<Roles> vectorRole = dao.getAllRole();
                request.setAttribute("dataRole", vectorRole);
                request.setAttribute("dataAcc", vectorUserInfo);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/UpdateAccount.jsp");
                dis.forward(request, response);
            }
        }

        if (service.equals("update")) {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String submit = request.getParameter("submit");
            if (submit != null && submit.equals("Chỉnh Sửa")) {
                String RoleID = request.getParameter("RoleID");
                String FName = request.getParameter("FirstName");
                String LName = request.getParameter("LastName");
                String Email = request.getParameter("Email");
                String PW = request.getParameter("PassWord");
                String PhoneNo = request.getParameter("PhoneNo");
                String Address = request.getParameter("Address");
                boolean Gender = Boolean.parseBoolean(request.getParameter("Gender"));
                String DOB = request.getParameter("DOB");
                String imgUser = request.getParameter("imgUser");
                dao.updateAcc(new Account(RoleID, FName, LName, Email, PW, PhoneNo, Address, DOB, Gender, imgUser), userID);
                response.sendRedirect("AdminController?service=listUser");
                return;
            }
            if (submit == null) {
                Vector<Account> vectorUserInfo = dao.getAcc(userID);
                Vector<Roles> vectorRole = dao.getAllRole();
                request.setAttribute("dataRole", vectorRole);
                request.setAttribute("dataAcc", vectorUserInfo);
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/UpdateAccount.jsp");
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
