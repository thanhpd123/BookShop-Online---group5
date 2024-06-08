/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;//
import java.nio.file.Paths;//
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.*;

@MultipartConfig
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account customer = (Account) session.getAttribute("acc");
        if (customer != null) {
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("userprofile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDAO accdao = new AccountDAO();
        int UserID = ((Account) session.getAttribute("acc")).getUserID();
//update file anh
        Part part = request.getPart("image");
        String realPath = request.getServletContext().getRealPath("/img");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String date = request.getParameter("date");
        String msg = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(date, formatter);
        LocalDate today = LocalDate.now();
        if (fname.length() > 40 || lname.length() > 40) {
            msg = "Your first name, last name exceed 40 characters";
        } else if (!(phone.matches("[0]{1}[35798]{1}[0-9]{8}"))) {
            msg = "Phone is invalid";
        } else if (today.isBefore(dob)) {
            msg = "Invalid date of birth";
        } else {
            if (!fileName.equals("")) {
                //add file vao folder, db
            try {
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }
                int index = fileName.lastIndexOf('.');
                String extension = (index != -1) ? fileName.substring(index) : "";
                String fileimage = "./img/imageupdate" + UserID + extension;
                String imgName = realPath + "/imageupdate" + UserID + extension;
                part.write(imgName);
                accdao.UpdateProfilesImg(UserID, fname, lname, phone, address, gender, date, fileimage);
            } catch (IOException ex) {
                ex.printStackTrace();
                return;
            }
        } else {
            accdao.UpdateProfiles(UserID, fname, lname, phone, address, gender, date);
        }
        msg = "Successfully";
    }
        Account customer = accdao.loadUserProfile(UserID);
        request.setAttribute("customer", customer);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("userprofile.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
