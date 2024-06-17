<%-- 
    Document   : MyOrder
    Created on : May 29, 2024, 6:02:17 AM
    Author     : ADMIN
--%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders,entity.OrderDetail,entity.Book,entity.Account, java.util.Enumeration, jakarta.servlet.http.HttpSession, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BES | Orders</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
    </head>
    <body>
        <!-- menu -->
        <div class="menu container-fluid" style="background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-3 d-none d-lg-block">
                    <a href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"></a>
                </div>

                <!<!-- search bar -->
                <div class="cl-lg-6 d-flex justify-content-center align-items-center">
                    <form action="BookController?service=search" method="POST">
                        <div style="display: inline-block"><input type="text" placeholder="Search Book Name" name="Name" style="width: 350px"></div>
                        <div style="display: inline-block"><input type="submit" value="Search" name="submit"></div>

                    </form>
                </div>

                <!<!-- menu item -->
                <div class="cl-lg-3 d-flex justify-content-center align-items-center">
                    <nav>
                        <ul id="element">
                            <li id="item"><a href="OrderController?service=listAll" style="color: #664229">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6m2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0m4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4m-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10s-3.516.68-4.168 1.332c-.678.678-.83 1.418-.832 1.664z"/>
                                    </svg>
                                </a></li>
                            <li id="item"><a href="BookCart?service=showCart" style="color: #664229">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                                    </svg>
                                </a></li>
                            <li id="item"><a href="" style="color: #664229">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0z"/>
                                    <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
                                    </svg>
                                </a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <%
            Vector<Orders> vector=
                   (Vector<Orders>)request.getAttribute("orders");
            int i = Integer.parseInt(request.getParameter("order"));
            Vector<OrderDetail> vectorOD=
                   (Vector<OrderDetail>)request.getAttribute("thisOrders");
            OrderDetail thisOrder = new OrderDetail();
            for(OrderDetail od : vectorOD){
                    if(vector.get(i).getOrderID()==od.getOrderID()){
                        thisOrder = od;                           
                    }
                }
            Vector<Book> vectorBook = 
                   (Vector<Book>)request.getAttribute("book");
            Book thisBook = new Book();
            for(Book book: vectorBook){
                    if(thisOrder.getBookID().equals(book.getBookID())){
                        thisBook = book;
                    }
                }
            Vector<Account> vectorAcc = 
                    (Vector<Account>)request.getAttribute("acc");
            Account thisAcc = new Account();
            for(Account acc: vectorAcc){
                    if(vector.get(i).getUserID()==acc.getUserID()){
                        thisAcc = acc;
                    }
                }
        %>
        <div class="justify-content-center">
            <form action="thisOrder?service=listAll&order=<%=i%>" method="post">
                <p class="text-center align-items-center mt-3 mb-3" style="caption-side: top; font-weight: 900; font-size: 130%">Order Detail</p>
                <div></div>
                <div style="border-width:3px;border-style: solid">
                    <div class="row" style="border-width:1px;border-color: gray;border-style: solid">

                        <div class="col-4">
                            <a href="thisOrder?service=listAll&order=<%=i%>">
                                ID đơn hàng: <%=vector.get(i).getOrderID()%>
                            </a>
                        </div>
                        <div class="col-4">Ngày đặt hàng: <%=vector.get(i).getOrderDate()%></div>
                        <div class="col-4">
                            Trạng thái:
                            <select name="orderState" id="" >
                                <option value="" disabled selected >
                                    <%=vector.get(i).getOrderState()%>
                                </option>
                                <option>Đang xử lý</option>
                                <option>Đã giao hàng</option>
                                <option>Hủy</option>
                            </select>

                        </div>
                    </div>
                    <section style="border-width:1px;border-color: gray;border-style: solid">
                        <div class="row">

                            <div class="col-6">
                                Sản phẩm: <%=thisBook.getName()%> 
                                <div>
                                    <img class="pic ml-auto mr-auto mt-4 mb-4" src = "${pageContext.request.contextPath}<%=thisBook.getBookImg()%>" style="width: auto; height: 100px">
                                </div>
                                <div>    
                                    Số lượng: <%=thisOrder.getQuantity()%> 
                                </div>
                            </div>

                            <div class="col-6" style="text-align: right;color: red">Giá: <%=thisOrder.getPrice()%></div>
                        </div>
                        <div style="text-align: right;color: red">Trả tiền: <%=thisOrder.getPrice()*thisOrder.getQuantity()%></div>
                    </section>
                    <div class="row" style="border-width:1px;border-color: gray;border-style: solid">
                        <div class="col">
                            <div>

                                Thông tin khách hàng: <%=thisAcc.getFirstName()%> <%=thisAcc.getLastName()%>
                            </div>
                            <div>
                                Ngày sinh: <%=thisAcc.getDOB()%>

                            </div>
                        </div>
                        <div class="col">
                            <div>
                                Email: <%=thisAcc.getEmail()%>
                            </div>
                            <div>
                                Số điện thoại: <%=thisAcc.getPhoneNo()%>
                            </div>                           
                        </div>
                        <div class="col">Địa chỉ: <%=thisAcc.getAddress()%></div>
                    </div>   
                </div>
                <tr>
                    <td><input type="submit" value="Cập nhật trạng thái" name="submit" onclick="alert('Cập nhật trạng thái.')"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>

            </form>

        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
