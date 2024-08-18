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
import model.*;
import entity.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@MultipartConfig
public class BlogdetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOBlogs blogsdao = new DAOBlogs();
        String blogID = request.getParameter("blogID");
        int id = Integer.parseInt(blogID);
        BlogResponseDTO blogResponseDTO = blogsdao.findBlogById(id);
        request.setAttribute("blogResponseDTO", blogResponseDTO);
        request.getRequestDispatcher("blog/postdetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOBlogs blogdao = new DAOBlogs();
        int blogid = Integer.parseInt(request.getParameter("blogid"));
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String content = request.getParameter("descriptionBlog");
        String status = request.getParameter("status");
        Part part = request.getPart("image");
        LocalDate today = LocalDate.now();
        String realPath = request.getServletContext().getRealPath("/img");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        if (!fileName.equals("")) {
            try {
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }
                int index = fileName.lastIndexOf('.');
                String extension = (index != -1) ? fileName.substring(index) : "";
                String fileimage = "./img/imageupdate" + blogid + extension;
                String imgName = realPath + "/imageupdate" + blogid + extension;
                part.write(imgName);
                blogdao.UpdateBlogImg(blogid, title, brief, content, status, fileimage, today);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            blogdao.UpdateBlog(blogid, title, brief, content, status, today);
        }
        BlogResponseDTO blogResponseDTO = blogdao.findBlogById(blogid);
        request.setAttribute("blogResponseDTO", blogResponseDTO);
        request.getRequestDispatcher("blog/postdetail.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
