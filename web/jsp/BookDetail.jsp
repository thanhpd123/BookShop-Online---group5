<%-- 
    Document   : BookDetail
    Created on : Jun 10, 2024, 11:27:36 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Book, entity.EntityFeedback, java.util.Vector, entity.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BeS | Book Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
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

            .checked {
                color: orange;
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
                margin-left: 100px;
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

            .button1{
                border-color: white;
                color: white;
                background-color: #E5D3B3;
            }

            .button1:hover {
                border-color: #D2B48C;
                background-color: white;
                color: #E5D3B3;
            }

            .button2{
                border-color: white;
                color: white;
                background-color: #E5D3B3;
            }

            .button2:hover {
                border-color: #D2B48C;
                background-color: white;
                color: #E5D3B3;
            }

        </style>
    </head>
    <body>
        <%
                    String logged = (String)session.getAttribute("msg1");
        %>
        <%
            if (logged != null) {
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
                                    <div><a href="OrderController" style="color: #664229"><i class="bi bi-wallet2"></i> Đơn Hàng</a></div>
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

        <!-- content -->
        <div class="container-fluid mb-0 pb-0" style="background-color: #F5F5F5">
            <%
                Vector<Book> vector = (Vector<Book>) request.getAttribute("dataBook");
                Vector<Book> vector2 = (Vector<Book>) request.getAttribute("data");
                Vector<Book> vectorP = (Vector<Book>) request.getAttribute("dataPur");
                for(Book bk : vector) {
            %>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 mt-3 ml-3">
                    <%
                        for (Book bk1 : vector2) {
                    %>
                    <h6 style="font-weight: normal"><a href="Home?service=listAll">Home</a> > <a href="BookController?service=bookByCat&cat=<%=bk1.getCategoryID()%>&page=1"><%=bk.getCategoryID()%></a> > <%=bk.getName()%></h6>
                    <%
                        }
                    %>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-3 pt-5 pb-4" style="background-color: white; margin-top: 10px;">
                    <div class="container-fluid position-relative">
                        <div class="row">
                            <div class="col pt-3">
                                <div class="d-flex justify-content-center align-items-center mb-4">
                                    <img src="${pageContext.request.contextPath}<%=bk.getBookImg()%>" alt="Book Image" style="width: 180px; height: 270px; margin-left: auto; margin-right: auto;">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="mt-3 pt-3">
                                    <h6 style="font-weight: normal; position: absolute; top: 0; left: 50px">Lượt Mua: <%=vectorP.get(0).getPurchases()%></h6>
                                </div>
                            </div>
                            <div class="col">
                                <div class="mt-3 pt-3">
                                    <h6 style="font-weight: normal; position: absolute; top: 0; right: 40px">Số lượng: <%=bk.getQuantity()%></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 pt-5 pl-xl-4" style="background-color: white; margin-top: 10px;">
                    <div>
                        <div class="justify-content-center text-center">
                            <h3 style="font-weight: normal; margin-bottom: 20px"> <%=bk.getName()%> </h3>
                        </div>
                        <div>
                            <h4 class="myDIV" style="font-weight: normal; margin-bottom: 20px"> <%=bk.getPrice()%> </h4>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Tác giả: <%=bk.getAuthorID()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Thể Loại: <%=bk.getCategoryID()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Xuất bản ngày: <%=bk.getPublicationDate()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Tái bản lần thứ: <%=bk.getEdition()%> </h6>
                        </div>
                        <%
                            Vector<Book> vectorS = (Vector<Book>) request.getAttribute("dataS");
                            String status = vectorS.get(0).getStatus();
                            String flag = vectorS.get(0).getFlag();
                            if (!status.equals("available") || !flag.equals("1") || bk.getQuantity() == 0) {
                        %>
                        <div class="mt-4">
                            <a href="BookCart?service=addToCart&userID=<%=acc.getUserID()%>&bookID=<%=bk.getBookID()%>"><button class="d-flex ml-auto mr-auto justify-content-center align-items-center" onclick="no()" style="width: 80%; height: 40px; background-color: #664229; color: white; border-radius: 5px"><i class="bi bi-cart-plus"></i> &nbsp Thêm Vào Giỏ</button></a>
                        </div>
                        <%
                            } else {
                        %>
                        <div class="mt-4">
                            <a href="BookCart?service=addToCart&userID=<%=acc.getUserID()%>&bookID=<%=bk.getBookID()%>"><button class="d-flex ml-auto mr-auto justify-content-center align-items-center" style="width: 80%; height: 40px; background-color: #664229; color: white; border-radius: 5px"><i class="bi bi-cart-plus"></i> &nbsp Thêm Vào Giỏ</button></a>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>

            </div>

            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 ml-auto mr-auto pt-5 pb-4" style="background-color: white; padding-left: 100px;padding-right: 100px">
                    <p><%=bk.getDescription()%></p>
                </div>
                <div class="col-lg-2">
                </div>
                <%
                    }
                %>
            </div>

            <div class="row mt-3">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 text-center mb-0 pb-5" style="background-color: white;">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col text-center mt-4">
                                <h3> Sách Cùng Thể Loại </h3>
                            </div>
                        </div>
                        <%
                            int pagination = (int)request.getAttribute("page");
                            int pre = 0, next = 0; 
                            if (pagination == 1){
                            pre = 1;
                            next = 2; 
                            } else {
                            pre = pagination - 1;
                            next = pagination + 1;
                            }
                        %>
                        <div class="row">
                            <div class="col-1 d-flex align-items-center justify-content-center" style="height: 100%">
                                <div class=" d-flex align-items-center justify-content-center" style="height: 100px; width: 60px; background-color: #E5D3B3; margin-top: 80px">
                                    <a href="BookController?service=viewBook&bookID=<%=vector.get(0).getBookID()%>&page=<%=pre%>"><i class="bi bi-chevron-left h1" style="color: white"></i></a>
                                </div>
                            </div>
                            <div class="col-10">
                                <div class="container-fluid">
                                    <div class="row">
                                        <%
                                            Vector<Book> vector1 = (Vector<Book>) request.getAttribute("dataCate");
                                            for(Book bk : vector1) {
                                        %>
                                        <div class="col-lg-3 pt-4 d-flex justify-content-center align-items-center" style="margin-top: 10px;">
                                            <a href="BookController?service=viewBook&bookID=<%=bk.getBookID()%>&page=1"><img src="${pageContext.request.contextPath}<%=bk.getBookImg()%>" alt="Book Image" style="width: 150px; height: 220px; margin-left: auto; margin-right: auto"></a>
                                        </div>

                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                            <div class="col-1 d-flex align-items-center justify-content-center" style="height: 100%">
                                <div class=" d-flex align-items-center justify-content-center" style="height: 100px; width: 60px; background-color: #E5D3B3; margin-top: 80px">
                                    <a href="BookController?service=viewBook&bookID=<%=vector.get(0).getBookID()%>&page=<%=next%>"><i class="bi bi-chevron-right h1" style="color: white"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
                                
            <div class="row">
                <div class="col-lg-2">
                </div>
                <!-- Feedback -->
                <div class="col-lg-8 mt-3 mb-3" style="background-color: white">
                    <div class="pt-3 pl-5">
                        <h4 style="font-weight: normal"> Đánh giá sản phẩm </h4>
                    </div>
                    <div>
                        <div class="container-fluid">
                            <%
                            Vector<EntityFeedback> vectorfb = (Vector<EntityFeedback>) request.getAttribute("dataFB");
                            Vector<EntityFeedback> vectorAll = (Vector<EntityFeedback>) request.getAttribute("dataAll");
                            for(EntityFeedback fb : vectorfb) {
                            %>
                            <% 
                                if (vectorfb.get(0) != null) {
                            %>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div style="font-size: 20px; font-weight: 350"><%=fb.getUserIDStr()%></div>
                                    <p style="font-size:17px"><%=fb.getFBDate()%> &nbsp; | &nbsp; <%=fb.getBookID()%></p>
                                    <%
                                        int star = fb.getRate();
                                        if(star == 5){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <%
                                        }
                                        if(star == 4){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 3){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 2){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 1){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 0){
                                    %>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <%
                                        }
                                    %>
                                    <div style="font-size: 20px; font-weight: 350"><%=fb.getFBContent()%></div>
                                    <hr>
                                </div>
                            </div>

                            <%
                                    }
                                else {
                            %>
                            <h5 style="font-weight: normal"> Sản phẩm chưa có đánh giá </h5>
                            <%
                                }
                                }
                            %>
                            <div class="row pb-3 pt-2 mb-5" style="background-color: white">
                                <div class="col-3" style="margin-right: 15px">
                                </div>
                                <div class="col-4">
                                    <nav aria-label="Page navigation example">
                                        <%
                                            if ( vectorAll.size() > 20 ) {
                                        %>
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=1">1</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=2">2</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=3">3</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=4">4</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=5">5</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <%
                                            }
                                            if ( vectorAll.size() <= 20 && vectorAll.size() > 15 ) {
                                        %>
                                        <ul class="pagination" style="padding-right: 19px">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=1">1</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=2">2</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=3">3</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=4">4</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <%
                                            }
                                            if ( vectorAll.size() <= 15 && vectorAll.size() > 10  ) {
                                        %>
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=1">1</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=2">2</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=3">3</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <%
                                            }
                                            if ( vectorAll.size() <= 10 && vectorAll.size() > 5 ) {
                                        %>
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=1">1</a></li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=2">2</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <%
                                            }
                                            if ( vectorAll.size() <= 5 ) {
                                        %>
                                        <ul class="pagination" style="padding-right: 67px">
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                    <span class="sr-only">Previous</span>
                                                </a>
                                            </li>
                                            <li class="page-item"><a class="page-link" href="BookController?service=FB&bookID=<%=vector.get(0).getBookID()%>&page=1">1</a></li>
                                            <li class="page-item">
                                                <a class="page-link" href="#" aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                    <span class="sr-only">Next</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <%
                                            }
                                        %>
                                    </nav>
                                </div>
                                <div class="col-4">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>
            </div>

        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark pt-5" style="background-color: #D2B48C">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-6 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="">
                        <h1 class="mb-4 display-5 font-weight-semi-bold" style="color: white">Book E Shop</h1>
                    </a>
                    <p style="color: white">Cửa hàng sách online của chúng tôi cung cấp một loạt các đầu sách đa dạng, từ văn học cổ điển đến sách khoa học, đáp ứng mọi nhu cầu đọc của bạn. Bạn có thể dễ dàng tìm và mua những cuốn sách yêu thích từ mọi thể loại và chủ đề. Cảm ơn bạn đã ủng hộ cửa hàng sách của chúng tôi!</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Hoa Lac, Ha Noi, VietNam</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-envelope text-primary mr-3"></i>bes@gmail.com</p>
                    <p class="mb-0" style="color: white"><i class="fa fa-phone-alt text-primary mr-3"></i>+84 98 123 4567</p>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h5 class="font-weight-bold mb-4" style="color: white">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-white mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                        <a class="text-white mb-2" href="BookCart?service=showCart"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-white" href=""><i class="fa fa-angle-right mr-2"></i>Contact Us</a>

                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left" style="color: white">
                        &copy; 
                        <a class="text-white font-weight-semi-bold" href="Home?service=listAll" >BeS | Book E Shop</a>. All Rights Reserved. Designed
                        by BeS
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                </div>
            </div>
        </div>

        <%
                    
                }
        %>

        <!-- chua dang nhap -->
        <%
            if (logged == null) {
        %>
        <!-- menu -->
        <div class="menu container-fluid" style="height: 90px; background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-4 mt-auto mb-auto d-none d-lg-block">
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
                <div class="cl-lg-4 d-flex justify-content-center align-items-center">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4">
                            </div>
                            <div class="col-3">
                                <a href="register"><button class="button1 mt-3 mb-3 ml-5" style="font-size:18px; width: 120px; height: 50px;">Đăng Ký</button></a>
                            </div>
                            <div class="col-1">
                            </div>
                            <div class="col-3">
                                <a href="login"><button class="button2 mt-3 mb-3 ml-5" style="font-size:18px; width: 120px; height: 50px;">Đăng Nhập</button></a>
                            </div>
                            <div class="col-1">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- content -->
        <div class="container-fluid mb-0 pb-0" style="background-color: #F5F5F5">
            <%
                Vector<Book> vector = (Vector<Book>) request.getAttribute("dataBook");
                Vector<Book> vector2 = (Vector<Book>) request.getAttribute("data");
                Vector<Book> vectorP = (Vector<Book>) request.getAttribute("dataPur");
                for(Book bk : vector) {
                
            %>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 mt-3 ml-3">
                    <%
                        for (Book bk1 : vector2) {
                    %>
                    <h6 style="font-weight: normal"><a href="Home?service=listAll">Home</a> > <a href="BookController?service=bookByCat&cat=<%=bk1.getCategoryID()%>&page=1"><%=bk.getCategoryID()%></a> > <%=bk.getName()%></h6>
                    <%
                        }
                    %>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-3 pt-5 pb-4" style="background-color: white; margin-top: 10px;">
                    <div class="container-fluid position-relative">
                        <div class="row">
                            <div class="col">
                                <div class="d-flex justify-content-center align-items-center mb-4">
                                    <img src="${pageContext.request.contextPath}<%=bk.getBookImg()%>" alt="Book Image" style="width: 180px; height: 270px; margin-left: auto; margin-right: auto;">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="mt-3 pt-3">
                                    <h6 style="font-weight: normal; position: absolute; top: 0; left: 50px">Lượt Mua: <%=vectorP.get(0).getPurchases()%></h6>
                                </div>
                            </div>
                            <div class="col">
                                <div class="mt-3">
                                    <h6 style="font-weight: normal; position: absolute; top: 0; right: 40px">Số lượng: <%=bk.getQuantity()%></h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 pt-4 pl-xl-4" style="background-color: white; margin-top: 10px;">
                    <div>
                        <div class="justify-content-center text-center">
                            <h3 style="font-weight: normal; margin-bottom: 20px"> <%=bk.getName()%> </h3>
                        </div>
                        <div>
                            <h4 class="myDIV" style="font-weight: normal; margin-bottom: 20px"> <%=bk.getPrice()%> </h4>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Tác giả: <%=bk.getAuthorID()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Thể Loại: <%=bk.getCategoryID()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Xuất bản ngày: <%=bk.getPublicationDate()%> </h6>
                        </div>
                        <div>
                            <h6 style="font-weight: normal; margin-bottom: 20px"> Tái bản lần thứ: <%=bk.getEdition()%> </h6>
                        </div>
                        <div class="mt-4">
                            <a href="login"><button class="d-flex ml-auto mr-auto justify-content-center align-items-center" style="width: 80%; height: 40px; background-color: #664229; color: white; border-radius: 5px"><i class="bi bi-cart-plus"></i> &nbsp Thêm Vào Giỏ</button></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>

            </div>

            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 ml-auto mr-auto pb-5 pt-5" style="background-color: white; padding-left: 100px;padding-right: 100px">
                    <p><%=bk.getDescription()%></p>
                </div>
                <div class="col-lg-2">
                </div>
                <%
                                    }
                %>
            </div>

            <div class="row mt-3">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 text-center mb-0 pb-5" style="background-color: white;">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col text-center mt-4">
                                <h3> Sách Cùng Thể Loại </h3>
                            </div>
                        </div>
                        <%
                            int pagination = (int)request.getAttribute("page");
                            int pre = 0, next = 0; 
                            if (pagination == 1){
                            pre = 1;
                            next = 2; 
                            } else {
                            pre = pagination - 1;
                            next = pagination + 1;
                            }
                        %>
                        <div class="row">
                            <div class="col-1 d-flex align-items-center justify-content-center" style="height: 100%">
                                <div class=" d-flex align-items-center justify-content-center" style="height: 100px; width: 60px; background-color: #E5D3B3; margin-top: 80px">
                                    <a href="BookController?service=viewBook&bookID=<%=vector.get(0).getBookID()%>&page=<%=pre%>"><i class="bi bi-chevron-left h1" style="color: white"></i></a>
                                </div>
                            </div>
                            <div class="col-10">
                                <div class="container-fluid">
                                    <div class="row">
                                        <%
                                            Vector<Book> vector1 = (Vector<Book>) request.getAttribute("dataCate");
                                            for(Book bk : vector1) {
                                        %>
                                        <div class="col-lg-3 pt-4 d-flex justify-content-center align-items-center" style="margin-top: 10px;">
                                            <a href="BookController?service=viewBook&bookID=<%=bk.getBookID()%>&page=1"><img src="${pageContext.request.contextPath}<%=bk.getBookImg()%>" alt="Book Image" style="width: 150px; height: 220px; margin-left: auto; margin-right: auto"></a>
                                        </div>

                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                            <div class="col-1 d-flex align-items-center justify-content-center" style="height: 100%">
                                <div class=" d-flex align-items-center justify-content-center" style="height: 100px; width: 60px; background-color: #E5D3B3; margin-top: 80px">
                                    <a href="BookController?service=viewBook&bookID=<%=vector.get(0).getBookID()%>&page=<%=next%>"><i class="bi bi-chevron-right h1" style="color: white"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
                                
            <div class="row">
                <div class="col-lg-2">
                </div>
                <!-- Feedback -->
                <div class="col-lg-8 mt-3 mb-3" style="background-color: white">
                    <div class="pt-3 pl-5">
                        <h4 style="font-weight: normal"> Đánh giá sản phẩm </h4>
                    </div>
                    <div>
                        <%
                            Vector<EntityFeedback> vectorfb = (Vector<EntityFeedback>) request.getAttribute("dataFB");
                            for(EntityFeedback fb : vectorfb) {
                        %>
                        <% 
                            if (vectorfb.get(0) != null) {
                        %>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div style="font-size: 20px; font-weight: 350"><%=fb.getUserIDStr()%></div>
                                    <p style="font-size:17px"><%=fb.getFBDate()%> &nbsp; | &nbsp; <%=fb.getBookID()%></p>
                                    <%
                                        int star = fb.getRate();
                                        if(star == 5){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <%
                                        }
                                        if(star == 4){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 3){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 2){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 1){
                                    %>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <% 
                                        }
                                        if(star == 0){
                                    %>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                    <%
                                        }
                                    %>
                                    <div style="font-size: 20px; font-weight: 350"><%=fb.getFBContent()%></div>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class>
                                </div>
                            </div>
                        </div>
                        <%
                                }
                            else {
                        %>
                        <h5 style="font-weight: normal"> Sản phẩm chưa có đánh giá </h5>
                        <%
                            }
                            }
                        %>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>

            </div>
        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark mt-0 pt-5" style="background-color: #D2B48C">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-6 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="">
                        <h1 class="mb-4 display-5 font-weight-semi-bold" style="color: white">Book E Shop</h1>
                    </a>
                    <p style="color: white">Cửa hàng sách online của chúng tôi cung cấp một loạt các đầu sách đa dạng, từ văn học cổ điển đến sách khoa học, đáp ứng mọi nhu cầu đọc của bạn. Bạn có thể dễ dàng tìm và mua những cuốn sách yêu thích từ mọi thể loại và chủ đề. Cảm ơn bạn đã ủng hộ cửa hàng sách của chúng tôi!</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Hoa Lac, Ha Noi, VietNam</p>
                    <p class="mb-2" style="color: white"><i class="fa fa-envelope text-primary mr-3"></i>bes@gmail.com</p>
                    <p class="mb-0" style="color: white"><i class="fa fa-phone-alt text-primary mr-3"></i>+84 98 123 4567</p>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h5 class="font-weight-bold mb-4" style="color: white">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-white mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                        <a class="text-white mb-2" href="BookCart?service=showCart"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                        <a class="text-white mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-white" href=""><i class="fa fa-angle-right mr-2"></i>Contact Us</a>

                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left" style="color: white">
                        &copy; 
                        <a class="text-white font-weight-semi-bold" href="Home?service=listAll" >BeS | Book E Shop</a>. All Rights Reserved. Designed
                        by BeS
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                </div>
            </div>
        </div>
        <%
            }
        %>
        <br>
        <script>
            let x = document.querySelectorAll(".myDIV");
            for (let i = 0, len = x.length; i < len; i++) {
                let num = Number(x[i].innerHTML)
                        .toLocaleString('en');
                x[i].innerHTML = num;
            }
        </script>
        <script>
            function no(){
                alert("Hiện sản phẩm này không thể thêm vào giỏ hàng!");
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
