package dashboard;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.AccountDAO;
import model.ProductDAO;

public class dashboard_forMarketing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String fromTest = request.getParameter("hidden_param1");
        String toTest = request.getParameter("hidden_param2"); 
        LocalDate toDate = LocalDate.now();
        // Lùi lại 7 ngày
        LocalDate fromDate = toDate.minusDays(8);
        // Chuyển đổi ngày thành chuỗi với định dạng 'yyyy-MM-dd' để truy vấn CSDL
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String from;
        from = fromDate.format(formatter);
        if (fromTest != null) {
            from = fromTest;
        }
        String to;
        to = toDate.format(formatter);
        if (toTest != null) {
            to = toTest;
        }
        ProductDAO pDao = new ProductDAO();
        AccountDAO aDao = new AccountDAO();
        // Gửi label ngày cho chart
        String[] listDay = pDao.get7days(from, to);
        String[] staticsCustomer = aDao.getStaticsCustomerIn7Days(from, to);
        String[] staticsPost = aDao.getStaticsPostIn7Days(from, to);
        String[] staticsProduct = aDao.getStaticsProductIn7Days(from, to);
        String[] staticsFeedback = aDao.getStaticsFeedbackIn7Days(from, to);
        // Chuyển đổi mảng thành chuỗi JSON
        String jsonString = "[\"" + String.join("\", \"", listDay) + "\"]";
        String jsonStaticCustomerString = "[\"" + String.join("\", \"", staticsCustomer) + "\"]";
        String jsonStaticsPostString = "[\"" + String.join("\", \"", staticsPost) + "\"]";
        String jsonStaticsProductString = "[\"" + String.join("\", \"", staticsProduct) + "\"]";
        String jsonStaticsFeedbackString = "[\"" + String.join("\", \"", staticsFeedback) + "\"]";
        // Ghi dữ liệu JSON vào response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write("{\"listDay\":" + jsonString + ","
                    + " \"staticsCustomer\":" + jsonStaticCustomerString 
                    + ", \"staticsPost\":" + jsonStaticsPostString 
                    + ", \"staticsProduct\":" + jsonStaticsProductString
                    + ", \"staticsFeedback\":" + jsonStaticsFeedbackString + "}");
            out.close();
        }
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String from = request.getParameter("hidden_param1");
        String to = request.getParameter("hidden_param2");
        ProductDAO pDao = new ProductDAO();
        AccountDAO aDao = new AccountDAO();
        // Gửi label ngày cho chart
        String[] listDay = pDao.get7days(from, to);
        String[] staticsCustomer = aDao.getStaticsCustomerIn7Days(from, to);
        String[] staticsPost = aDao.getStaticsPostIn7Days(from, to);
        String[] staticsProduct = aDao.getStaticsProductIn7Days(from, to);
        String[] staticsFeedback = aDao.getStaticsFeedbackIn7Days(from, to);
        // Chuyển đổi mảng thành chuỗi JSON
        String jsonString = "[\"" + String.join("\", \"", listDay) + "\"]";
        String jsonStaticCustomerString = "[\"" + String.join("\", \"", staticsCustomer) + "\"]";
        String jsonStaticsPostString = "[\"" + String.join("\", \"", staticsPost) + "\"]";
        String jsonStaticsProductString = "[\"" + String.join("\", \"", staticsProduct) + "\"]";
        String jsonStaticsFeedbackString = "[\"" + String.join("\", \"", staticsFeedback) + "\"]";
        // Ghi dữ liệu JSON vào response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.write("{\"listDay\":" + jsonString + ","
                    + " \"staticsCustomer\":" + jsonStaticCustomerString 
                    + ", \"staticsPost\":" + jsonStaticsPostString 
                    + ", \"staticsProduct\":" + jsonStaticsProductString
                    + ", \"staticsFeedback\":" + jsonStaticsFeedbackString + "}");
            out.close();
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
