<%-- 
    Document   : CheckOut
    Created on : Jun 28, 2024, 11:55:57 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, entity.Cart, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt Hàng</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
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
            
            .button1{
                border-color: #987554; 
                color: #987554; 
                background-color: white;
            }

            .button1:hover {
                background-color: #D2B48C;
                color: white;
            }
            
        </style>
    </head>
    <body>
        <!-- menu -->
        <div class="menu container-fluid" style="background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-3 d-none d-lg-block">
                    <a href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo"></a>
                </div>

                <!-- search bar -->
                <div class="cl-lg-6 d-flex justify-content-center align-items-center">
                    <form action="BookController?service=search" method="POST">
                        <div style="display: inline-block"><input type="text" placeholder="Search Book Name" name="Name" style="width: 350px"></div>
                        <div style="display: inline-block"><input type="submit" value="Search" name="submit"></div>

                    </form>
                </div>

                <!-- menu item -->
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

        <!-- content -->
        <div class="container-fluid">
            <div class="row mt-3 mb-0">
                <a href="BookCart?service=showCart" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại Giỏ Hàng</a>
            </div>
            <div class="row">
                <div class="col-lg-5 pl-0 pr-0">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12 text-center mt-3 mb-4" style="font-size: 23px">Thông tin Liên lạc</div>
                        </div>
                        <%
                            Vector<Account> vector = (Vector<Account>) request.getAttribute("dataAddress");
                            for(Account acc : vector) {
                        %>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto pl-5" style="font-size: 17px">Tên người nhận: </div>
                            <div class="col-7 border ml-0 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=acc.getFirstName()%> <%=acc.getLastName()%></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto pl-5" style="display: inline-block; font-size: 17px">Địa chỉ: </div>
                            <div class="col-7 border ml-0 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=acc.getAddress()%></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto pl-5" style="display: inline-block; font-size: 17px">SĐT: </div>
                            <div class="col-7 border ml-0 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=acc.getPhoneNo()%></div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-lg-7 pl-0 pr-0">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12 text-center mt-3 mb-4" style="font-size: 23px"> Đơn Hàng </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 text-center" style="font-size: 20px">
                            </div>
                            <div class="col-3 text-center" style="font-size: 20px">
                                Sản Phẩm
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px">
                                Giá
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px">
                                Số Lượng
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px">
                                Thành Tiền
                            </div>
                        </div>
                        <%
                            Vector <Cart> vectorC = (Vector<Cart>) request.getAttribute("dataCart");
                            int totalPrice = 0;
                            for (Cart cart : vectorC) {
                            int total = cart.getQuantity()*cart.getPrice();
                            totalPrice += cart.getQuantity()*cart.getPrice(); 
                        %>
                        <div class="row">
                            <div class="col-3 text-center">
                                <img class="ml-auto mr-auto mb-2" src="${pageContext.request.contextPath}<%=cart.getBookImg()%>" style="height: 190px; width: 130px">
                            </div>
                            <div class="col-3 text-center mt-auto mb-auto">
                                <%=cart.getBookID()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=cart.getPrice()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=cart.getQuantity()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=total%>
                            </div>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-3">
                </div>
                <div class="col-3">
                </div>
                <div class="col-3 mt-auto mb-auto">
                    <div class="" style="font-size: 20px; font-weight: normal"> Tổng thanh toán (<%=vectorC.size()%> sản phẩm): <%=totalPrice%>&#8363</div>
                </div>
                <div class="col-2">
                    <a href="BookCart?service=payment"><button class="button1 mt-auto mb-auto ml-5" style="font-size:20px; width: 150px; height: 60px;">Đặt Hàng</button></a>
                </div>
                <div class="col-1">
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

        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
    </body>
</html>
