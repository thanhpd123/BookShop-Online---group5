package controller;

import entity.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.BlogDAO;

public class BlogListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numberPagingString = request.getParameter("numberPaging");
        if (numberPagingString == null){
            numberPagingString = "1";
        }
        int numberPaging = Integer.parseInt(numberPagingString);
        BlogDAO daoB = new BlogDAO();
        int count = daoB.getTotalBlog();
        int endPage = count/6;
        if(count % 6 != 0){
            endPage++;
        }
        List<Blog> listB = daoB.getAllBlog();
        List<Blog> listP = daoB.pagingBlogs(numberPaging);
        request.setAttribute("listP", listP);
        request.setAttribute("blogs", listB);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", numberPaging);
        request.getRequestDispatcher("BlogList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
