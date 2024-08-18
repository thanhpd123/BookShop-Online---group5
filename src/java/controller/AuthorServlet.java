package controller;

import entity.Author;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.BookManageDAO;

public class AuthorServlet extends HttpServlet {
    private BookManageDAO authorDAO;

    public void init() {
        authorDAO = new BookManageDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteAuthor(request, response);
                break;
            default:
                listAuthor(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createAuthor(request, response);
                break;
            case "update":
                updateAuthor(request, response);
                break;
        }
    }

    private void listAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Author> authors = authorDAO.getAuthor();
        request.setAttribute("authorList", authors);
        request.getRequestDispatcher("/author-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/author-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Author existingAuthor = authorDAO.getAuthorById(id);
        request.setAttribute("author", existingAuthor);
        request.getRequestDispatcher("/author-form.jsp").forward(request, response);
    }

    private void createAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        authorDAO.addAuthor(name);
        response.sendRedirect("author?action=list");
    }

    private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        authorDAO.updateAuthor(id, name);
        response.sendRedirect("author?action=list");
    }

    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        authorDAO.deleteAuthor(id);
        response.sendRedirect("author?action=list");
    }
}
