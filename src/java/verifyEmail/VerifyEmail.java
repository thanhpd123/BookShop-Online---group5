package verifyEmail;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDAO;
import entity.Account;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class VerifyEmail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String key = request.getParameter("key");
        String time = request.getParameter("checktime");

//        AccountDAO dao = new AccountDAO();
//        Account customer = dao.verifyAccount(key);
        long currentTime = Long.parseLong(time);
        long expirationTime = currentTime + 60000;
        long currentTimeError = System.currentTimeMillis();
        if (currentTimeError < expirationTime) {
            session.setAttribute("email", key);
            request.getRequestDispatcher("setnewpassword.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "The link has been disabled, you can only access it for 1 minutes.");
            request.getRequestDispatcher("PasswordRetrieval.jsp").forward(request, response);
        }
//        PrintWriter out = response.getWriter();
//        out.println(currentTimeError);
//        out.print(expirationTime);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Email verification servlet";
    }
}
