<%-- 
    Document   : MyOrder
    Created on : May 29, 2024, 6:02:17 AM
    Author     : ADMIN
--%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders,entity.OrderDetail, java.util.Enumeration, jakarta.servlet.http.HttpSession, java.util.Vector"%>
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
        <div class="menu container-fluid">
            <div class="row">

                <!-- logo -->
                <div class="cl-lg-4 d-none d-lg-block">
                    <a href="index.jsp"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"></a>
                </div>

                <!<!-- search bar -->
                <div class="d-flex justify-content-center align-items-center">
                    <form action="BookController?service=search" method="POST">
                        <p><input type="text" placeholder="Search Name" name="Name">
                            <input type="submit" value="Search" name="submit">
                        </p>
                    </form>
                </div>

                <!-- menu item -->
                <div class="cl-lg-4 d-flex justify-content-center align-items-center">
                    <nav>
                        <ul id="element">
                            <li id="item"><a href="" style="color: black">Account</a></li>
                            <li id="item"><a href="BookCart?service=showCart" style="color: black">Cart</a></li>
                            <li id="item"><a href="" style="color: black">Sign Out</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
                <%
                   Vector<Orders> vector=
                    (Vector<Orders>)request.getAttribute("orders");
                   Vector<OrderDetail> vectorOD=
                    (Vector<OrderDetail>)request.getAttribute("thisOrders");
                    int i = Integer.parseInt(request.getParameter("order"));

                %>
        <div class="justify-content-center">
            <form action="thisOrder?service=listAll&order=<%=i%>" method="post">
                <p class="text-center align-items-center mt-3 mb-3" style="caption-side: top; font-weight: 900; font-size: 130%">Đơn hàng của tôi</p>
                <div></div>
                <div style="border-width:3px;border-style: solid">
                    <div class="row" style="border-width:1px;border-color: gray;border-style: solid">

                        <div class="col-4">
                            <a href="thisOrder?service=listAll&order=<%=i%>">
                                ID đơn hàng: <%=vector.get(i).getOrderID()%>
                            </a>
                        </div>
                        <div class="col-4">Ngày đặt hàng: <%=vector.get(i).getOrderDate()%></div>
                        <div class="col-4">Trạng thái: <%=vector.get(i).getOrderState()%></div>
                    </div>
                    <section style="border-width:1px;border-color: gray;border-style: solid">
                        <div class="row">

                            <div class="col-6">Sản phẩm:
                                Tiền Không Từ Đâu
                                
                            </div>
                            <div class="col-6" style="text-align: right;color: red">Giá:
                                100000
                                <!<!-- comment -->
                            </div>
                            <div class="col-4">Người nhận: TruongViet Anh</div>
                            <div class="col-4">Điện thoại: 0123456789</div>
                            <div class="col-4">Email: customer1@gmail.com</div>
                        </div>
                    </section>
                    <div style="background-color: lightyellow;color: red;text-align: right;border-width:1px;border-color: gray;border-style: solid">
                        <div>
                            Trả tiền: 100000 
                        </div>
                        <div class="row">
                            <div class="col-8"></div>
                            <div class="col-2">
                                <button>Mua lại</button>
                            </div>
                            <div class="col-2">
                                <button>Đánh giá</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                    
                %>

            </form>

        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
