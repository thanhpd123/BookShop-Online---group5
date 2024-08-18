/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;
import entity.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NewFilter2 implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        HttpSession session = httpRequest.getSession();
        boolean isAuth = true;
        Account acc = (Account)session.getAttribute("acc");

        if (session.getAttribute("acc") == null ){
//            if(!url.contains("login") && !url.contains("error.jsp") && !url.contains("Home") && !url.contains("BookController?service=viewBook")){
//                isAuth = false;
//            }
        } else if(acc.getRoleID().equals("3")){
           if(url.contains("SaleController") || url.contains("BookManagementServlet") || url.contains("jsp/SaleManage.jsp") || url.contains("jsp/OrderList.jsp") || url.contains("jsp/OrderDetail.jsp") || url.contains("jsp/SaleDashboard.jsp")) {
               isAuth = false;
           }
        } else if(acc.getRoleID().equals("5")){
           if(url.contains("SaleController") || url.contains("BookManagementServlet") || url.contains("jsp/SaleManage.jsp") || url.contains("jsp/OrderList.jsp") || url.contains("jsp/OrderDetail.jsp") || url.contains("jsp/SaleDashboard.jsp")) {
               isAuth = false;
           }
        }
        
        if(!isAuth){
            httpResponse.sendRedirect("error.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}