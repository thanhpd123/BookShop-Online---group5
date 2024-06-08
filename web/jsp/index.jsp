<%-- 
    Document   : index
    Created on : May 17, 2024, 11:04:16 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="jakarta.servlet.http.HttpSession,java.util.Vector, entity.Book"%>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BeS | Book E Shopping site</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <style>
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
        </style>
    </head>
    <body>
        <!<!-- menu -->
        <div class="menu container-fluid">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-4 d-none d-lg-block">
                    <img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo">
                </div>

                <!<!-- search bar -->
                <div class="d-flex justify-content-center align-items-center">
                    <form action="BookController?service=search" method="POST">
                        <p><input type="text" placeholder="Search Name" name="Name">
                            <input type="submit" value="Search" name="submit">
                        </p>
                    </form>
                </div>

                <!<!-- menu item -->
                <div class="cl-lg-4 d-flex justify-content-center align-items-center">
                    <nav>
                        <ul id="element">
                            <li id="item"><a href="" style="color: black">Tài Khoản</a></li>
                            <li id="item"><a href="BookCart?service=showCart" style="color: black">Giỏ Hàng</a></li>
                            <li id="item"><a href="" style="color: black">Đăng Nhập</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px; background-color: #D19C97">
                        <h6 class="ml-auto mr-auto">Thể Loại</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <a href="" class="nav-item nav-link">Tiểu Thuyết</a>
                            <a href="" class="nav-item nav-link">Khoa Học</a>
                            <a href="" class="nav-item nav-link">Kinh Doanh</a>
                            <a href="" class="nav-item nav-link">Thiếu Nhi</a>
                            <a href="" class="nav-item nav-link">Lịch Sử</a>
                            <a href="" class="nav-item nav-link">Văn học Xã Hội</a>
                            <a href="" class="nav-item nav-link">Văn học Viễn Tưởng</a>
                            <a href="" class="nav-item nav-link">Tự Truyện</a>
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <div class="navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0 ml-auto justify-content-center">
                            <div class="navbar">
                                <div>
                                    <a id="navbar-list" href="index.jsp" style="color: black">Trang Chủ</a>
                                    <a id="navbar-list" href="" style="color: black">Về Chúng tôi</a>
                                    <a id="navbar-list" href="BookController?service=listAll" style="color: black">Sản phẩm</a>
                                    <a id="navbar-list" href="" style="color: black">Thể Loại</a>
                                    <a id="navbar-list" href="" style="color: black">Hỗ trợ Khách hàng</a>
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

            </div>
        </div>

        <a href="BookController?service=listAll"> Employee Manage</a>

        <!-- tieu thuyet -->
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Sách Thể Loại Tiểu Thuyết</span></h2>
        </div>
        <div class="container-fluid mt-2">


            <div class="row px-xl-5 pb-3">
                <%
                 Vector<Book> vector1 = (Vector<Book>) request.getAttribute("data1");
                 for(Book bk : vector1) {
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
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                        <a href="BookCart?service=addToCart&bookID<%--=<%=bk.getBookID()%>--%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>

                <%
                    }
                %>

            </div>
        </div>

        <!-- khoa hoc -->
        <div class="text-center mb-4">
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
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                        <a href="BookCart?service=addToCart&bookID<%--=<%=bk.getBookID()%>--%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>

                <%
                    }
                %>

            </div>


        </div>
        <!-- kinh doanh -->
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Sách Thể Loại Kinh Doanh</span></h2>
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
                        <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem sản phẩm</a>
                        <a href="BookCart?service=addToCart&bookID<%--=<%=bk.getBookID()%>--%>" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ</a>
                    </div>
                </div>

                <%
                    }
                %>

            </div>


        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark mt-5 pt-5" style="background-color: #EDF1FF">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-6 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="">
                        <h1 class="mb-4 display-5 font-weight-semi-bold" style="color: #D19C97">Book E Shop</h1>
                    </a>
                    <p>Cửa hàng sách online của chúng tôi cung cấp một loạt các đầu sách đa dạng, từ văn học cổ điển đến sách khoa học, đáp ứng mọi nhu cầu đọc của bạn. Bạn có thể dễ dàng tìm và mua những cuốn sách yêu thích từ mọi thể loại và chủ đề. Cảm ơn bạn đã ủng hộ cửa hàng sách của chúng tôi!</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Hoa Lac, Ha Noi, VietNam</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>bes@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+84 98 123 4567</p>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h5 class="font-weight-bold mb-4" style="color: #D19C97">Quick Links</h5>
                    <div class="d-flex flex-column justify-content-start">
                        <a class="text-dark mb-2" href="index.jsp"><i class="fa fa-angle-right mr-2"></i>Home</a>
                        <a class="text-dark mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                        <a class="text-dark mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                        <a class="text-dark mb-2" href="BookCart?service=showCart"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                        <a class="text-dark mb-2" href=""><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                        <a class="text-dark" href=""><i class="fa fa-angle-right mr-2"></i>Contact Us</a>

                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left text-dark">
                        &copy; 
                        <a class="text-dark font-weight-semi-bold" href="#">BeS | Book E Shop</a>. All Rights Reserved. Designed
                        by BeS
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                    <img class="img-fluid" src="img/payments.png" alt="">
                </div>
            </div>
        </div>



        <br>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
