package controller;

import entity.BlogComments;
import entity.BlogDetail;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BlogDAO;
import java.util.Vector;
import model.AccountDAO;

public class BlogDetailServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int blogID = Integer.parseInt(request.getParameter("blogID"));
        BlogDAO bdao = new BlogDAO();
        AccountDAO adao = new AccountDAO();
        BlogDetail blogData = bdao.getBlogDetailbyID(blogID);
        Vector<BlogComments> comments = bdao.getAllCommentsByBlog(blogID);
        request.setAttribute("blogData",blogData);
        request.setAttribute("commentData", comments);
        request.setAttribute("commentDataLength", comments.size());
        request.getRequestDispatcher("BlogDetail.jsp").forward(request, response);
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
        int blogID = Integer.parseInt(request.getParameter("blogID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String commentText = request.getParameter("commentText");
        BlogDAO dao = new BlogDAO();
        dao.insertBlogComments(blogID, userID, commentText);
        doGet(request, response);
    }
}
