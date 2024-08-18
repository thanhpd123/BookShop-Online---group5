package controller;

import entity.Author;
import entity.BlogResponseDTO;
import entity.Categoty;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.DAOBlogs;
import entity.PageControl;
import model.ProductDAO;
import static Constant.constant.RECORD_PER_PAGE;

public class BlogListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO prodao = new ProductDAO();
        PageControl pageControl = new PageControl();
        
        List<BlogResponseDTO> blogList = pagination(request, pageControl);
        
        ArrayList<BlogResponseDTO> listAu = prodao.loadAuthor();
        request.setAttribute("listAu", listAu);
        request.setAttribute("blogList", blogList);
        request.setAttribute("pageControl", pageControl);
        request.getRequestDispatcher("./blog/postlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private List<BlogResponseDTO> pagination(HttpServletRequest request, PageControl pageControl) {
        //get page
        String pageRaw = request.getParameter("page");
        DAOBlogs blogDAO = new DAOBlogs();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<BlogResponseDTO> listBlog = null;
        String action = request.getParameter("action") == null ? "All" : request.getParameter("action");
        switch (action) {
            case "search":
                String sort = request.getParameter("sort");
                String find = request.getParameter("find").trim();
                    String author = request.getParameter("author");
                    String status = request.getParameter("status");
                if (!sort.equals("Allasc")) {
                    totalRecord = blogDAO.findTotalRecord();
                    listBlog = blogDAO.findBlogByPageASC(page, sort);
                    request.setAttribute("sort", sort);
                } else {
                    request.setAttribute("find", find);
                    request.setAttribute("author", author);
                    request.setAttribute("status", status);
                    totalRecord = blogDAO.findTotalFilter(author, status, find);
                    listBlog = blogDAO.findBlogByPageFilter(page, author, status, find);
                }
                pageControl.setUrlPattern("bloglistcontroller?author=" + author + "&status=" + status + "&find=" + find + "&action=" + action + "&sort=" + sort + "&");
                break;
            default:
                totalRecord = blogDAO.findTotalRecord();
                listBlog = blogDAO.findBlogByPage(page);
                pageControl.setUrlPattern("bloglistcontroller?");
        }
        //tinh total page
        int totalPage = (totalRecord % RECORD_PER_PAGE) == 0
                ? (totalRecord / RECORD_PER_PAGE)
                : (totalRecord / RECORD_PER_PAGE) + 1;
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);
        return listBlog;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
