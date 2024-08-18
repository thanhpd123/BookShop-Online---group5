<%-- 
    Document   : MyOrder
    Created on : May 29, 2024, 6:02:17 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders, entity.OrderDetail, entity.ReceiverInfo, entity.Account, java.util.Enumeration, jakarta.servlet.http.HttpSession, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BES | Orders</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <style>
            .dropbtn {
                background-color: #4CAF50;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
            }

            .logo{
                width: 15%;
                height: auto;
                display: flex;
                justify-content: center;
                margin-left: auto;
                margin-right: auto;
            }

            .searchbar{
                margin-top: auto;
                margin-bottom: auto;
            }

            #mybar{
                width: 1300px;
                display: inline;

            }

            .sidebar{
                width: 200px;
                margin-left: 30%;
            }

            #cate{
                padding-top: 20px;
                padding-bottom: 20px;
                margin: 0 auto;
                text-align: center;
                border-bottom: solid 1px;
                border-color: #D3D3D3;
            }

            #sidebar{
                float: left;
                height: 540px;
                border-bottom: solid 1px;
                border-right: solid 1px;

            }

            #sidebar-list{
                list-style-type: none;
                padding-top: 10px;
                margin-bottom: 10px;
            }

            .menu{
                display: flex;
                padding: 0;
                border-bottom: solid 1px;
                border-color: #D3D3D3;
            }

            #element{
                list-style-type: none;
                display: flex;
            }

            #item{
                margin-top: 5px;
                padding-right: 15px;
                margin: 0 40px;
                font-size: 110%;

            }

            a{
                text-decoration: none;
                color: #A9A9A9;
            }

            nav{
                position: absolute;
                right: 0;
                display: flex;
                justify-content: flex-end;
            }

            .navbar{
                padding-top: 20px;
                padding-bottom: 20px;
                margin: 0 auto;
                text-align: center;
                border-bottom: solid 1px;
                border-color: #D3D3D3;
            }

            #navbar-list{

                margin: 0 50px;
            }

        </style>
    </head>
    <body>
        <%
            Account acc = (Account)session.getAttribute("acc");
        %>
        <!-- menu -->
        <div class="menu container-fluid" style="height: 90px; width: 100%; background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-3 mt-auto mb-auto">
                    <a href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"></a>
                </div>

                <!-- search bar -->
                <div class="cl-lg-4 d-flex justify-content-center align-items-center ml-5">
                    <form action="BookController?service=search&page=1" method="POST">
                        <div style="display: inline-block"><input type="text" placeholder="Search Book Name" name="Name" style="width: 380px; height: 35px; color: #664229"></div>
                        <div style="display: inline-block"><button type="submit" class="" style="height: 35px; width: 50px; border-color: white; color: white; background-color: #E5D3B3"><i class="bi bi-search"></i></button></div>
                    </form>
                </div>

                <!-- menu item -->
                <div class="cl-lg-3 d-flex mt-3 mb-3 align-items-center">
                    <nav>
                        <ul id="element">
                            <div class="dropdown">
                                <li id="item"><a href="userprofile" style="color: #664229">
                                        <i class="bi bi-person h3"></i>
                                    </a></li>
                                <div class="dropdown-content" style="background-color: #E5D3B3">
                                    <div><a href="userprofile" style="color: #664229"><i class="bi bi-person h5"></i> <%=acc.getLastName()%></a></div>
                                    <div><a href="OrderController" style="color: #664229"><i class="bi bi-box-arrow-right h5"></i> Đơn Hàng</a></div>
                                    <div><a href="LogOut" style="color: #664229"><i class="bi bi-box-arrow-right h5"></i> Đăng Xuất</a></div>
                                </div>
                            </div>
                            <li id="item"><a href="BookCart?service=showCart&userID=<%=acc.getUserID()%>" style="color: #664229">
                                    <i class="bi bi-cart h4"></i>
                                </a></li>
                            <li id="item" style="color: #E5D3B3">m
                                </a></li>
                            <li id="item" style="color: #E5D3B3">m
                                </a></li>
                        </ul>
                    </nav>
                </div>
                <div class="cl-lg-2">
                </div>
            </div>
        </div>
        <%
            Vector<Orders> vectorList = (Vector<Orders>)request.getAttribute("dataList");
            Vector<OrderDetail> vectorDetail = (Vector<OrderDetail>)request.getAttribute("dataDetail");
            Vector<ReceiverInfo> vectorRcv = (Vector<ReceiverInfo>)request.getAttribute("dataRcv");
        %>
        <div class="container-fluid">
            <div class="row mt-3 mb-3">
                <a href="OrderController" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại</a>
            </div>
            <div class="row d-flex justify-content-center align-items-center mt-4 mb-4" style="font-weight: 500; font-size: 20px">
                Order Detail
            </div>
            <div class="row">
                <div class="col-5">
                    <div class="container-fluid">
                        <div class="row d-flex justify-content-center align-items-center pr-5 mb-3" style="font-weight: 500; font-size: 20px">
                            Thông Tin Đơn Hàng
                        </div>
                        <%
                            for(Orders or : vectorList) {
                        %>
                        <div class="row mb-2">
                            <div class="col-4">
                                ID đơn hàng: <%=or.getOrderID()%>
                            </div>
                            <div class="col-4">Ngày đặt hàng: <%=or.getOrderDate()%></div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-4">
                                Trạng thái:
                                <%=or.getOrderState()%>
                            </div>
                            <div class="col-4">

                                <%
                                    String payment = vectorDetail.get(0).getPayment();
                                    if (payment.equals("ck")) {
                                        payment = "Chuyển Khoản";
                                    }
                                    if (payment.equals("cod")) {
                                        payment = "Thanh Toán Khi Nhận Hàng";
                                    }
                                %>
                                Phương Thức Thanh Toán: <%=payment%>
                            </div>
                        </div>
                        <%
                            }
                            for (ReceiverInfo rcv : vectorRcv) {
                        %>
                        <div class="row" style="border-width:1px;border-color: gray;border-style: solid">
                            <div class="">
                                <div>

                                    Thông tin khách hàng: <%=rcv.getName()%>
                                </div>
                                <div>
                                    Ngày sinh: <%=rcv.getPhoneNo()%>

                                </div>
                            </div>
                            <div class="">
                                <div>
                                    Email: <%=rcv.getAddress()%>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-7">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3 text-center">
                                            STT
                                        </div>
                                        <div class="col-9">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 text-center">
                                Sản Phẩm
                            </div>
                            <div class="col-2 text-center">
                                Số Lượng
                            </div>
                            <div class="col-2 text-center">
                                Giá Tiền
                            </div>
                        </div>
                        <%
                            int n = 0;
                            int total = 0;
                            for (OrderDetail ord : vectorDetail) {
                            n++;
                            total += ord.getPrice();
                        %>
                        <div class="row mb-3">
                            <div class="col-4">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3 text-center mt-auto mb-auto">
                                            <%=n%>
                                        </div>
                                        <div class="col-9 text-center mt-auto mb-auto">
                                            <img src="${pageContext.request.contextPath}<%=ord.getBookImg()%>" style="width: 120px; height: 170px">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 text-center mt-auto mb-auto">
                                <%=ord.getName()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=ord.getQuantity()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=ord.getPrice()%>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row">
                            <div class="col-3">
                                <%
                                    int ID = (int)request.getAttribute("ID");
                                    if (vectorList.get(0).getOrderState().equals("Đang xử lý")) {
                                %>
                                <a href="BookCart?service=cancel&ID=<%=ID%>"><button class="mt-2 mb-2" style="background-color: white; border-color: #E5D3B3; color: #664229"> Hủy Đơn Hàng </button></a>
                                <%
                                    }
                                %>
                            </div>
                            <div class="col-3">
                            </div>
                            <div class="col-3 pl-0 pr-0">
                                Tổng Thanh Toán (<%=n%> sản phẩm):
                            </div>
                            <div class="col-3 pl-0 pr-0">
                                <%=total%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
