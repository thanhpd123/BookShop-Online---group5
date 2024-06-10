<%-- 
    Document   : index
    Created on : May 17, 2024, 11:04:16 PM
    Author     : Dung Dinh
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jakarta.servlet.http.HttpSession,java.util.Vector, entity.Book"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
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
        </style>
    </head>
    <body>

        <!<!-- menu -->
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
                            <li id="item"><a href="" style="color: #664229">
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

        <div class="container-fluid mb-3">
            <div class="row border-top px-xl-5">
                <div class="col-2"></div>
                <div class="col-lg-8">
                    <div class="navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0 ml-auto justify-content-center">
                            <div class="navbar">
                                <div>
                                    <a id="navbar-list" href="Home?service=listAll" style="color: black">Trang Chủ</a>
                                    <a id="navbar-list" href="" style="color: black">Về Chúng tôi</a>
                                    <a id="navbar-list" href="BookController?service=listAll" style="color: black">Sách</a>
                                    <div class="dropdown">
                                        <p id="navbar-list" href="" style="color: black">Thể Loại</p>
                                        <div class="dropdown-content">
                                            <a href="BookController?service=bookByCat&cat=CAT1">Tiểu Thuyết</a>
                                            <a href="BookController?service=bookByCat&cat=CAT2">Khoa Học</a>
                                            <a href="BookController?service=bookByCat&cat=CAT3">Tài Chính</a>
                                            <a href="BookController?service=bookByCat&cat=CAT4">Tự Lực</a>
                                            <a href="BookController?service=bookByCat&cat=CAT5">Pháp Luật</a>
                                            <a href="BookController?service=bookByCat&cat=CAT6">Lập Trình</a>
                                            <a href="BookController?service=bookByCat&cat=CAT7">Tâm Lý Học</a>
                                        </div>
                                    </div>
                                    <a id="navbar-list" href="" style="color: black">Liên Hệ</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img class="img-fluid" src="${pageContext.request.contextPath}/assets/featured1.PNG" style="width: 100%" alt="Image">
                            </div>
                            <div class="carousel-item">
                                <img class="img-fluid" src="${pageContext.request.contextPath}/assets/featured2.PNG" style="width: 100%" alt="Image">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-prev-icon mb-n2"></span>
                            </div>
                        </a>
                        <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-next-icon mb-n2"></span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
        </div>

        <!-- tieu thuyet -->
        <div class="text-center mb-4 mt-3" >
            <h2 class="section-title px-5"><span class="px-2">Sách Thể Loại Tiểu Thuyết</span></h2>
        </div>
        <div class="container-fluid mt-2" style="background-color: #F5F5F5" >
            <div class="row pb-3">

                <div class="col-lg-2"></div>
                <div class="col-lg-8 pb-1" style="background-color: white; ">
                    <div class="container-fluid mt-2">
                        <div class="row pb-3">
                            <%
                 Vector<Book> vector1 = (Vector<Book>) request.getAttribute("data1");
                 for(Book bk : vector1) {
                            %>
                            <div class="col-3">
                                <div class="card product-item border-left border-right border-top mb-4">

                                    <img class="pic ml-auto mr-auto mt-4 mb-4" src = "${pageContext.request.contextPath}<%=bk.getBookImg()%>" style="width: auto; height: 300px">
                                    <h6 class="text-truncate mb-3 text-center"> <%=bk.getName()%> </h6>
                                    <div class="d-flex justify-content-center p-0 pb-3">
                                        <h6><%=bk.getPrice()%>&#8363;</h6>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between bg-light border">
                                    <a href="BookController?service=viewBook&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                                    <a href="BookCart?service=addToCart&userID=1&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                                </div>
                            </div>

                            <%
                                }
                            %>

                        </div>
                    </div>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </div>

        <div class="container-fluid mb-2 pb-3" style="background-color: #F5F5F5">
            <div class="row">
                <div class="col-1">
                </div>
                <div class="col-10" style="background-color: white">
                    <p>djfndvrmvvmkomvmvfmkvokfvkfmvfkmkfmmbokr</p>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>

        <!-- khoa hoc -->
        <div class="text-center mb-4 mt-3">
            <h2 class="section-title px-5"><span class="px-2">Sách Thể Loại Khoa Học</span></h2>
        </div>
        <div class="container-fluid mt-2">
            <div class="row px-xl-5 pb-3">
                <%
                 Vector<Book> vector2 = (Vector<Book>) request.getAttribute("data2");
                 for(Book bk : vector2) {
                %>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">

                    <div class="card product-item border-left border-right border-top mb-4">

                        <img class="pic ml-auto mr-auto mt-4 mb-4" src = "${pageContext.request.contextPath}<%=bk.getBookImg()%>" style="width: auto; height: 300px">
                        <h6 class="text-truncate mb-3 text-center"> <%=bk.getName()%> </h6>
                        <div class="d-flex justify-content-center p-0 pb-3">
                            <h6><%=bk.getPrice()%>&#8363;</h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="BookController?service=viewBook&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                        <a href="BookCart?service=addToCart&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>

                <%
                    }
                %>

            </div>


        </div>

        <!-- kinh doanh -->
        <div class="text-center mb-4 mt-3">
            <h2 class="section-title px-5"><span class="px-2">Sách Thể Loại Tài Chính</span></h2>
        </div>
        <div class="container-fluid mt-2">
            <div class="row px-xl-5 pb-3">
                <%
                 Vector<Book> vector3 = (Vector<Book>) request.getAttribute("data3");
                 for(Book bk : vector3) {
                %>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">

                    <div class="card product-item border-left border-right border-top mb-4">

                        <img class="pic ml-auto mr-auto mt-4 mb-4" src = "${pageContext.request.contextPath}<%=bk.getBookImg()%>" style="width: auto; height: 300px">
                        <h6 class="text-truncate mb-3 text-center"> <%=bk.getName()%> </h6>
                        <div class="d-flex justify-content-center p-0 pb-3">
                            <h6><%=bk.getPrice()%>&#8363;</h6>
                        </div>
                    </div>
                    <div class="card-footer d-flex justify-content-between bg-light border">
                        <a href="BookController?service=viewBook&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                        <a href="BookCart?service=addToCart&bookID=<%=bk.getBookID()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>

                <%
                    }
                %>

            </div>


        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark mt-5 pt-5" style="background-color: #D2B48C">
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



        <br>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
