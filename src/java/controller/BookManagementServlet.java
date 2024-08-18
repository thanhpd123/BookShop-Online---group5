package controller;

import entity.Author;
import entity.BookManage;
import entity.Categoty;
import java.io.IOException;
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

@MultipartConfig
public class BookManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "images"; // Thư mục để lưu trữ ảnh
    private static final long MAX_FILE_SIZE = 3 * 1024 * 1024; // 3MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        BookManageDAO dao = new BookManageDAO();
        List<Author> authors = dao.getAuthor();
        List<BookManage> listBM = dao.getAllBookManage();
        List<Categoty> categories = dao.getCategories();
        request.setAttribute("categoryList", categories);
        request.setAttribute("authorList", authors);
        request.setAttribute("listBookManage", listBM);
        request.getRequestDispatcher("ManagerDashBoard.jsp").forward(request, response);
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

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
        if (daoB.checkDuplicateRollNo(bookID)) {
            request.setAttribute("error", "Roll No existing!");
        } else {
            daoB.insertBook(bookID, bookImg, name, description, publisherName, authorID, edition, categoryID, publicationDate, quantity, price, salePrice, flag, status);
        }
        doGet(request, response);
    }
}
