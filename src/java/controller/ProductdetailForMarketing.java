package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.*;
import entity.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;

@MultipartConfig

public class ProductdetailForMarketing extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String BookID = request.getParameter("BookID");
        ProductDAO prodao = new ProductDAO();
        Book book = prodao.loadBook(BookID);
        ArrayList<Categoty> listCa = prodao.loadCategory();
        ArrayList<Author> listAu = prodao.loadAuthorProduct();
        request.setAttribute("listAu", listAu);
        request.setAttribute("listCa", listCa);
        request.setAttribute("book", book);
        request.getRequestDispatcher("productdetailsformarketing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String BookID = request.getParameter("BookID");

        Part part = request.getPart("image");
        String realPath = request.getServletContext().getRealPath("/book");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String author = request.getParameter("author");
        String quantity = request.getParameter("quantity");   
        String listprice = request.getParameter("listprice");
        String saleprice = request.getParameter("saleprice");
        String status = request.getParameter("status");
        String flagtoturn = request.getParameter("flagtoturn");
        String publishername = request.getParameter("publishername");

        int quantityStock = Integer.parseInt(quantity);
        Long price = Long.valueOf(listprice);
        Long salePrice = Long.valueOf(saleprice);
        String msg = "";

        ProductDAO prodao = new ProductDAO();

        if (quantityStock > 1000 || quantityStock < 0) {
            msg = "The quantity in stock must greater than 0 and less than 1000";
        } else if (price > 2100000000 || price < 0) {
            msg = "The price must be lower than the discounted price and must be within the range";
        } else if (salePrice > 2100000000 || salePrice < 0 || salePrice > price) {
            msg = "The reduced price must be lower than the original price and must be within range";
        } else {
            if (!fileName.equals("")) {
                try {
                    if (!Files.exists(Paths.get(realPath))) {
                        Files.createDirectory(Paths.get(realPath));
                    }
                    int index = fileName.lastIndexOf('.');
                    String extension = (index != -1) ? fileName.substring(index) : "";
                    String fileimage = "/book/imageupdate" + BookID + extension;
                    String imgName = realPath + "/imageupdate" + BookID + extension;
                    part.write(imgName);
                    prodao.UpdateProductImg(BookID, fileimage, title, category, author, quantityStock, price, salePrice, status, flagtoturn, publishername);
//                    prodao.UpdateProductImg(BookID, fileimage, title, category, author, 1000, 100, 100, status, flagtoturn, "thong");
                } catch (IOException ex) {
                    System.out.println(ex);
                    return;
                }
            } else {
                prodao.UpdateProduct(BookID, title, category, author, quantityStock, price, salePrice, status, flagtoturn, publishername);
            }
            msg = "Successfully";
        }
        Book book = prodao.loadBook(BookID);
        String img = book.getBookImg();
        ArrayList<Categoty> listCa = prodao.loadCategory();
        ArrayList<Author> listAu = prodao.loadAuthorProduct();
        request.setAttribute("listAu", listAu);
        request.setAttribute("listCa", listCa);
        request.setAttribute("book", book);
        request.setAttribute("img", img);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("productdetailsformarketing.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
