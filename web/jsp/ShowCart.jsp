<%-- 
    Document   : ShowCart
    Created on : Jun 10, 2024, 11:29:42 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Cart, entity.Book, entity.Account, jakarta.servlet.http.HttpSession, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BES | Cart</title>
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

            .button2{
                color: white;
                background-color: #D2B48C;
            }

            .button2:hover {
                background-color: white;
                color: #D2B48C;
            }

        </style>
    </head>
    <body>
        <%
            Account acc = (Account)session.getAttribute("acc");
        %>
        <!-- menu -->
        <div class="menu container-fluid" style="height: 90px; background-color: #E5D3B3">
            <div class="row">
                <!-- logo -->
                <div class="cl-lg-3 mt-auto mb-auto d-none d-lg-block">
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
                            <li id="item"><a href="LogOut" style="color: #664229">
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
            int totalPrice = 0;
        %>

        <!-- content -->
        <div class="container-fluid" style="background-color: #F5F5F5">
            <div class="row">
                <div class="col-lg-2">
                </div>
                <div class="col-lg-8 mt-3">
                    <h4 class="text-center pt-3 pb-3 mb-0" style="font-weight: normal; background-color: white">Giỏ Hàng của Bạn</h4>
                    <div class="container-fluid">
                        <div class="row pb-3 mb-3" style="background-color: white">
                            <div class="col-3 text-center mt-auto mb-auto" style="font-size: 17px"></div>
                            <div class="col-3 text-center mt-auto mb-auto" style="font-size: 17px">Sản Phẩm</div>
                            <div class="col-3 text-center mt-auto mb-auto" style="font-size: 17px">Số Lượng</div>
                            <div class="col-2 text-center mt-auto mb-auto" style="font-size: 17px">Giá</div>
                            <div class="col-1 text-center mt-auto mb-auto" style="font-size: 17px">Xóa</div>
                        </div>
                        <%
                            Vector<Cart> vector = (Vector<Cart>) request.getAttribute("data");
                            Vector<Cart> vectorCart = (Vector<Cart>) request.getAttribute("dataB");
                            for(Cart cart : vector) {
                            totalPrice += cart.getPrice()*cart.getQuantity();
                        %>
                        <div class="row mb-3" style="background-color: white">
                            <%
                                for(Cart cart1 : vectorCart){
                                if(cart1.getCartID() == cart.getCartID()){
                            %>
                            <div class="col-3 text-center pt-4 pb-4 mt-auto mb-auto pl-5"><img src="${pageContext.request.contextPath}<%=cart.getBookImg()%>" style="width: 50%"></div>
                            <div class="col-3 text-center mt-auto mb-auto" style="font-size: 18px"><%=cart.getBookID()%></div>
                            <div class="col-3 text-center mt-auto mb-auto" style="font-size: 18px">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-2">
                                        </div>
                                        <div class="col-2">
                                            <a href="BookCart?service=changeQuantityMinus&bookID=<%=cart1.getBookID()%>&userID=<%=cart1.getUserID()%>"><button style="background-color: white; width: 40px; height: 40px">-</button></a>
                                        </div>
                                        <div class="col-3 ">
                                            <form action="BookCart?service=updateQuantity&bookID=<%=cart1.getBookID()%>&userID=<%=cart1.getUserID()%>" name="frm" method="POST">
                                                <!--<input class="text-center justify-content-center align-items-center"  onkeypress="submitOnEnter(this, event);" style="width: 61 //px; padding-top: 5px; padding-bottom: 5px" type="text" name="quantity" value="<%=cart.getQuantity()%>">-->
                                                <input class="text-center justify-content-center align-items-center" style="width: 61px; padding-top: 5px; padding-bottom: 5px" type="text" name="quantity" value="<%=cart.getQuantity()%>">
                                                <input class="text-center justify-content-center align-items-center" style="display: none" type="submit" name="submit" value="submit">
                                            </form>
                                        </div>
                                        <div class="col-2">
                                            <a href="BookCart?service=changeQuantityPlus&bookID=<%=cart1.getBookID()%>&userID=<%=cart1.getUserID()%>"><button style="background-color: white; width: 40px; height: 40px; border-color: #664229">+</button></a>
                                        </div>
                                        <div class="col-3">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto" style="font-size: 18px"><%=cart.getPrice()*cart.getQuantity()%>&#8363;</div>
                            <div class="col-1 text-center mt-auto mb-auto" style="font-size: 18px"><a href="BookCart?service=deleteCart&bookID=<%=cart1.getBookID()%>"><i class="bi bi-x h3" style="color: #664229"></i></a></div>
                                    <%
                                            }
                                        }
                                    %>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div class="col-lg-2">
                </div>
            </div>
            <div class="row pt-3 pb-3" style="background-color: white">
                <div class="col-2">
                </div>
                <div class="col-lg-2 mt-auto mb-auto">
                    <a href="BookCart?service=deleteAll"><button class="button2 mt-auto mb-auto" style="font-size:20px; width: 150px; height: 60px;">Xóa tất cả</button></a>
                </div>
                <div class="col-lg-5 mt-auto mb-auto" style="padding-left: 350px; font-size: 20px">
                    <div class="">Tổng thanh toán (<%=vector.size()%> sản phẩm): <%=totalPrice%>&#8363</div>
                </div>
                <div class="col-lg-3" style="padding-left: 100px;">
                    <%
                        if (vector.size() != 0) {
                    %>
                    <a href="BookCart?service=checkOut&way=Payment"><button class="button1 mt-auto mb-auto" style="font-size:20px; width: 150px; height: 60px;">Thanh Toán</button></a>
                    <%
                        }
                    %>
                    <%
                        if (vector.size() == 0) {
                    %>
                    <button onclick="warn()" class="button1 mt-auto mb-auto" style="font-size:20px; width: 150px; height: 60px;">Thanh Toán</button>
                    <%
                        }
                    %>
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

        <script type="text/javascript">
            function submitOnEnter(inputElement, event) {
                if (event.keyCode == 13) {
                    inputElement.form.submit();
                }
            }

            function warn() {
                alert("Chưa có Sản Phẩm trong giỏ hàng");
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>