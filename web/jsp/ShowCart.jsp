<%-- 
    Document   : ShowCart
    Created on : May 22, 2024, 10:43:22 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.CartItem, java.util.Enumeration, jakarta.servlet.http.HttpSession, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BES | Cart</title>
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
                            <li id="item"><a href="" style="color: black">Đăng Xuất</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <%
//            int totalPrice = 0;
//            Enumeration<String> attributeNames = request.getSession().getAttributeNames();
            Vector<CartItem> vector = (Vector<CartItem>) request.getAttribute("data");
        %>
        <div class="justify-content-center">
            <form action="BookCart?service=updateQuantity" method="post">
                <table class="ml-auto mr-auto" style="width: 80%">
                    <caption class="text-center align-items-center mt-3 mb-3" style="caption-side: top; font-weight: 900; font-size: 130%">Giỏ Hàng của Bạn</caption>
                    <colgroup>
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 25%;">
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                    </colgroup>
                    <thead>
                        <tr class="text-center">
                            <th></th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                            <th>Delete</a></th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
//                            while (attributeNames.hasMoreElements()) {
//                                String attributeName = attributeNames.nextElement();
//        //                        if(attributeName.equals("userInfo") || attributeName.equals("logged")) {
//        //                                 continue;
//        //                        }
//                                CartItem citem = (CartItem) session.getAttribute(attributeName);
//                                totalPrice+=citem.getPrice()*citem.getQuantity();
                                for(CartItem citem : vector) {
                        %>

                        <tr class="text-center">
                            <td><img src="${pageContext.request.contextPath}<%=citem.getBookImg()%>" style="width: 50%"></td>
                            <td><%=citem.getBookID()%></td>
                            <td><input type="text" name="<%=citem.getBookID()%>" value="<%=citem.getQuantity()%>"></td>
                            <td><%=citem.getPrice()%></td>
                            <td><%=citem.getPrice()*citem.getQuantity()%></td>      
                            <td><a href="BookCart?service=deleteCart&bookID=<%=citem.getBookID()%>">Xóa</a></td>
                        </tr>
                        <%
                                
                            }
                        %>
                        <tr class="text-center">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>Total</td>
                            <td></td>
                            <td><a href="BookCart?service=deleteAll">Xóa tất cả</a></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <a href="BookController?service=listAll">Quay Lại</a>
            <div>
            </div>
        </div>

        <!-- footer -->
        <div class="footer container-fluid text-dark mt-5 pt-5" style="background-color: #DEDFE0">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-6 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="">
                        <h1 class="mb-4 display-5 font-weight-semi-bold">Book E Shop</h1>
                    </a>
                    <p>Cửa hàng sách online của chúng tôi cung cấp một loạt các đầu sách đa dạng, từ văn học cổ điển đến sách khoa học, đáp ứng mọi nhu cầu đọc của bạn. Bạn có thể dễ dàng tìm và mua những cuốn sách yêu thích từ mọi thể loại và chủ đề. Cảm ơn bạn đã ủng hộ cửa hàng sách của chúng tôi!</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Hoa Lac, Ha Noi, VietNam</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>bes@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>+84 98 123 4567</p>
                </div>
                <div class="col-lg-6 col-md-12">
                    <h5 class="font-weight-bold text-dark mb-4">Quick Links</h5>
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

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
