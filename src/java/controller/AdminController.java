/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import entity.Book;
import entity.EntityFeedback;
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
import model.DAOBook;
import model.DAOFeedback;

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
        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);
        if (service == null) {
            service = "listUser";
        }

        if (service.equals("listUser")) {
            Account acc = (Account) session.getAttribute("acc");
            Vector<Account> vectorUser = dao.getAll();
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            request.getRequestDispatcher("/jsp/AdminManage.jsp");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDDESC")) {
            Account acc = (Account) session.getAttribute("acc");
            Vector<Account> vectorUser = dao.get("SELECT Account.UserID, Roles.RoleName, Account.FirstName, Account.LastName, Account.Email, Account.Password, Account.PhoneNo, Account.Address, Account.Gender, Account.DOB, Account.imgUser, Account.Status\n"
                    + "FROM Account\n"
                    + "INNER JOIN Roles ON Account.RoleID = Roles.RoleID\n"
                    + "ORDER BY UserID DESC");
            request.setAttribute("data", vectorUser);
            request.setAttribute("titleTable", "Danh sách Khách Hàng");
            request.getRequestDispatcher("/jsp/AdminManage.jsp");
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
            dis.forward(request, response);
        }

        if (service.equals("sortIDASC")) {
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
//            String submit = "1";
            String bookID = request.getParameter("BookID");
            Vector<EntityFeedback> vectorFB = daoFB.getFeedback(bookID);
            Vector<Book> vectorBk = daoBk.getAllBook("select Book.BookID, Book.BookImg, Book.Name, Book.Description, Book.PublisherName, Author.AuthorName, Book.Edition, Category.CategoryName, Book.PublicationDate, Book.Quantity, Book.Price\n"
                    + "from Book \n"
                    + "INNER JOIN Author ON Book.AuthorID = Author.AuthorID\n"
                    + "INNER JOIN Category ON Book.CategoryID = Category.CategoryID;");
            request.setAttribute("dataFB", vectorFB);
            request.setAttribute("bookID", bookID);
            request.setAttribute("dataBk", vectorBk);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/Dashboard.jsp");
            dis.forward(request, response);
        }

        if (service.equals("search")) {
            String search = request.getParameter("search");
            if (search == null) {
                Vector<Account> vectorUser = dao.getAll();
                request.setAttribute("data", vectorUser);
                request.setAttribute("titleTable", "Danh sách Khách Hàng");
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserList.jsp");
                dis.forward(request, response);
            } else {
                Vector<Account> vector = dao.search(search);
                request.setAttribute("data", vector);
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
            String dob = vector.get(0).getDOB();
            LocalDate dob1 = LocalDate.parse(dob);
            int age = Period.between(dob1, curDate).getYears();
            request.setAttribute("data", vector);
            request.setAttribute("age", age);
            RequestDispatcher dis = request.getRequestDispatcher("/jsp/UserDetail.jsp");
            dis.forward(request, response);
        }

        if (service.equals("add")) {
            String submit = request.getParameter("submit");
            if (submit != null && submit.equals("Thêm Tài Khoản")) {
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
                dao.addAccount(new Account(RoleID, FName, LName, Email, PW, PhoneNo, Address, DOB, Gender, imgUser));
                response.sendRedirect("AdminController?service=listUser");
                return;
            }
            if (submit == null) {
                Vector<Account> vectorUser = dao.getAll();
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
