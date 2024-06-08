/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountDAO;

public class SetNewPass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("login").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String newpass = request.getParameter("newpass");
        String repass = request.getParameter("repass");
        AccountDAO accdao = new AccountDAO();
        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();
        int count = 0;
        String msg;
        for (int i = 0; i < newpass.length(); i++) {
            if (Character.isDigit(newpass.charAt(i))) {
                count++;
            }
        }
        if (newpass.length() < 6) {
            msg = "New password is too short!";
        } else if (newpass.contains(" ")) {
            msg = "New password not valid!";
        } else if (!newpass.equals(repass)) {
            msg = "New Pass not matches repass!";
        } else if (!Character.isUpperCase(newpass.charAt(0))) {
            msg = "New password must start with an uppercase letter!";
        } else if (count == 0) {
            msg = "Password must have at least one digit!";
        } else {
            accdao.Changepass(repass, email);
            msg = "Setting password successfuly!";
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("setnewpassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
