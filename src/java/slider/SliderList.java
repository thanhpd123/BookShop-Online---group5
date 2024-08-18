package slider;

import static Constant.constant.RECORD_PER_PAGE;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.*;
import java.util.List;
import model.DAOSlider;

public class SliderList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int check = 0;
        DAOSlider dao = new DAOSlider();
//        String value = request.getParameter("value");
        String buttonHide = request.getParameter("buttonHide");
        String buttonShow = request.getParameter("buttonShow");
        PageControl pageControl = new PageControl();
        
        if(buttonHide != null){
            check = 1;
            dao.UpdateStatus(check, buttonHide);
        }
        if(buttonShow != null){
            check = 2;
            dao.UpdateStatus(check, buttonShow);
        }
        List<Slider> sliderlist = pagination(request, pageControl);
        request.setAttribute("sliderlist", sliderlist);
        request.setAttribute("pageControl", pageControl);
        request.getRequestDispatcher("./slider/sliderlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private List<Slider> pagination(HttpServletRequest request, PageControl pageControl) {
        //get page
        String pageRaw = request.getParameter("page");
        DAOSlider dao = new DAOSlider();
        //valid page
        int page;
        try {
            page = Integer.parseInt(pageRaw);
        } catch (Exception e) {
            page = 1;
        }
        int totalRecord = 0;
        List<Slider> listSlider = null;
        String action = request.getParameter("action") == null ? "norequest" : request.getParameter("action");
        switch (action) {
            case "search":
                String find = request.getParameter("find").trim();
                String status = request.getParameter("status");
                totalRecord = dao.findTotalRecord(status, find);
                listSlider = dao.findSliderByPage(page, status, find);
                pageControl.setUrlPattern("sliderlist?status=" + status + "&find=" + find + "&action=" + action + "&");
                request.setAttribute("page", page);
                request.setAttribute("status", status);
                request.setAttribute("find", find);
                request.setAttribute("action", action);
                break;
            default:
                totalRecord = dao.findTotalRecord("All", "");
                listSlider = dao.findSliderByPage(page, "All", "");
                request.setAttribute("page", page);
                pageControl.setUrlPattern("sliderlist?");
        }
        int totalPage = (totalRecord % RECORD_PER_PAGE) == 0
                ? (totalRecord / RECORD_PER_PAGE)
                : (totalRecord / RECORD_PER_PAGE) + 1;
        pageControl.setPage(page);
        pageControl.setTotalPage(totalPage);
        pageControl.setTotalRecord(totalRecord);        
        return listSlider;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
