/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import static controller.BookManagementServlet.isNumeric;
import entity.BookManage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import model.BookManageDAO;

/**
 *
 * @author nguye
 */
@MultipartConfig
public class EditBookManageServlet extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "images";
    private static final long MAX_FILE_SIZE = 3 * 1024 * 1024;
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditBookManageServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBookManageServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        BookManageDAO dao = new BookManageDAO();
        BookManage BM = dao.getBookManage(request.getParameter("BookID"));
        request.setAttribute("Book", BM);
        request.getRequestDispatcher("productdetailsformarketing.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
                int authorID = 0;
        int quantity = 0;
        int price = 0;
        float salePrice = 0;
        String bookID = "";
        String bookImg = "";
        if (request.getParameter("bookID") != null) {
            bookID = request.getParameter("bookID");
            if (!isNumeric(bookID)) {
                request.setAttribute("error", "The book ID is must be number");
                request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
            }
        }

        if (request.getParameter("bookImg") != null) {
            Part filePart = request.getPart("bookImg");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String contentType = filePart.getContentType();
            long fileSize = filePart.getSize();

            if ((contentType.equals("image/jpeg") || contentType.equals("image/png")) && fileSize <= MAX_FILE_SIZE) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                filePart.write(uploadPath + File.separator + fileName);
                bookImg = "images" + File.separator + fileName;
            } else {
                request.setAttribute("error", "Your file upload not valid. Please upload again!");
                request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
            }
        }
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String publisherName = request.getParameter("publisherName");
        if (request.getParameter("authorID") != null && isNumeric(request.getParameter("authorID"))) {
            authorID = Integer.parseInt(request.getParameter("authorID"));
        } else {
            request.setAttribute("error", "The authorID is must be number");
            request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
        }

        String edition = request.getParameter("edittion");
        String categoryID = request.getParameter("categoryID");
        String publicationDateString = request.getParameter("publicationDate");
        Date publicationDate = Date.valueOf(publicationDateString);
        if (request.getParameter("quantity") != null && isNumeric(request.getParameter("quantity"))) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        } else {
            request.setAttribute("error", "The authorID is must be number");
            request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
        }

        if (request.getParameter("price") != null && isNumeric(request.getParameter("price"))) {
            price = Integer.parseInt(request.getParameter("price"));
        } else {
            request.setAttribute("error", "The price is must be integer number");
            request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
        }

        if (request.getParameter("salePrice") != null && isNumeric(request.getParameter("salePrice"))) {
            salePrice = Integer.parseInt(request.getParameter("salePrice"));
        } else {
            request.setAttribute("error", "The salePrice is must be float number");
            request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
        }
        boolean flag = Boolean.parseBoolean(request.getParameter("flag"));
        String status = request.getParameter("status");
        BookManageDAO daoB = new BookManageDAO();
        if(!bookID.isBlank()){
        daoB.editBook(bookID, bookImg, name, description, publisherName, authorID, edition, categoryID, publicationDate, quantity, price, salePrice, flag, status);
        }
        response.sendRedirect("BookManagementServlet");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
