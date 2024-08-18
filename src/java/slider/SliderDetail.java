package slider;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import entity.*;
import model.*;

@MultipartConfig
public class SliderDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String SilderID = request.getParameter("SilderID");
        DAOSlider dao = new DAOSlider();
        Slider slider = dao.Slider(SilderID);
        request.setAttribute("slider", slider);
        request.getRequestDispatcher("./slider/sliderdetails.jsp").forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOSlider sliderdao = new DAOSlider();
        String sliderid = request.getParameter("sliID");
        String title = request.getParameter("title");
        String status = request.getParameter("status");
        String note = request.getParameter("node");
        
        Part part = request.getPart("image");
        String realPath = request.getServletContext().getRealPath("/img");
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String msg = "";
        if (!fileName.equals("")) {
            try {
                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }
                int index = fileName.lastIndexOf('.');
                String extension = (index != -1) ? fileName.substring(index) : "";
                String fileimage = "./img/imageupdate" + sliderid + extension;
                String imgName = realPath + "/imageupdate" + sliderid + extension;
                part.write(imgName);
                sliderdao.UpdateSliderImg(sliderid, title, status, note, fileimage);
            } catch (IOException ex) {
                System.out.println(ex);
                return;
            }
        } else {
            sliderdao.UpdateSlider(sliderid, title, status, note);
        }
        msg = "Successfully";
        
        Slider slider = sliderdao.Slider(sliderid);
        request.setAttribute("slider", slider);
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("./slider/sliderdetails.jsp").forward(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
