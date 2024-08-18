package dashboard;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.AccountDAO;
import model.DAOBlogs;
import model.FeedbackDAO;
import model.ProductDAO;

public class dashboardinformation_forMarketing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate toDate = LocalDate.now();
        // Lùi lại 7 ngày
        LocalDate fromDate = toDate.minusDays(7);
        // Chuyển đổi ngày thành chuỗi với định dạng 'yyyy-MM-dd' để truy vấn CSDL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String from = fromDate.format(formatter);
        String to = toDate.format(formatter);

        DAOBlogs bDao = new DAOBlogs();
        int total_blog = bDao.getTotalPost(from, to);
        request.setAttribute("total_blog", total_blog);
        
        ProductDAO pDao = new ProductDAO();
        int total_product = pDao.getTotalProducts(from, to);
        request.setAttribute("total_product", total_product);
        
        AccountDAO aDao = new AccountDAO();
        int total_account = aDao.getTotalAccount(from, to);
        request.setAttribute("total_account", total_account);
        
        FeedbackDAO fDao = new FeedbackDAO();
        int total_feedback = fDao.getTotalFeedback(from, to);
        request.setAttribute("total_feedback", total_feedback);

        request.getRequestDispatcher("dashboard/dashboardforMarketing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String from = request.getParameter("from_date");
        String to = request.getParameter("to_date");

        DAOBlogs bDao = new DAOBlogs();
        int total_blog = bDao.getTotalPost(from, to);
        request.setAttribute("total_blog", total_blog);
        
        ProductDAO pDao = new ProductDAO();
        int total_product = pDao.getTotalProducts(from, to);
        request.setAttribute("total_product", total_product);
        
        AccountDAO aDao = new AccountDAO();
        int total_account = aDao.getTotalAccount(from, to);
        request.setAttribute("total_account", total_account);
        
        FeedbackDAO fDao = new FeedbackDAO();
        int total_feedback = fDao.getTotalFeedback(from, to);
        request.setAttribute("total_feedback", total_feedback);

        request.getRequestDispatcher("dashboard/dashboardforMarketing.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
