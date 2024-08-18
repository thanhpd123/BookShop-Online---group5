<%-- 
    Document   : CheckOut
    Created on : Jun 28, 2024, 11:55:57 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, entity.Cart, entity.Address, entity.ReceiverInfo, java.util.Vector"%>
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
                min-width: 650px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown1-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown1-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
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

            .dropdown1-content a:hover {
                background-color: #f1f1f1
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropdown1-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #3e8e41;
            }

            .modal-second {
                z-index: 1052;
            }

            .modal-backdrop.fade {
                & + .modal-backdrop.fade{
                    z-index: 1051;
                }
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
                                <div class="dropdown1-content" style="background-color: #E5D3B3">
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
        <div class="container-fluid">
            <div class="row mt-3 mb-0">
                <a href="BookCart?service=showCart" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại Giỏ Hàng</a>
            </div>
            <div class="row">
                <div class="col-lg-5 pl-0 pr-0">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12 text-center mt-3 mb-4" style="font-size: 23px; font-weight: 500; color: #664229">Thông tin Liên lạc</div>
                        </div>
                        <%
                            Vector<ReceiverInfo> vectorReceiverAdr = (Vector<ReceiverInfo>)request.getAttribute("dataReceiverAdr");
                            String name = (String) request.getAttribute("name");
                            String payment = (String) request.getAttribute("payment");
                            String ReceiverAdr = (String) request.getAttribute("ReceiverAdr");
                            for(ReceiverInfo rv : vectorReceiverAdr) {
                        %>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto pl-5" style="font-size: 17px;">Tên người nhận: </div>
                            <div class="col-7 border ml-0 pl-3 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=rv.getName()%></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto" style="display: inline-block; font-size: 17px; padding-left: 15%">Địa chỉ: </div>
                            <div class="col-7 border ml-0 pl-3 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=rv.getAddress()%></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 mt-auto mb-auto" style="display: inline-block; font-size: 17px; padding-left: 18%">SĐT: </div>
                            <div class="col-7 border ml-0 pl-3 pt-2 pb-2" style="width:100%; font-weight: normal; font-size: 18px"><%=rv.getPhoneNo()%></div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row">
                            <div class="col-9">
                            </div>
                            <div class="col-3" data-toggle="modal" data-target="#exampleModal">
                                <button type="button" class="btn" style="background-color: white; border-color: #664229; color: #664229" data-toggle="modal" data-target="#exampleModal">
                                    Thay Đổi
                                </button>
                            </div>
                        </div>
                        <%
                            Vector<ReceiverInfo> vectorReceiverInfo = (Vector<ReceiverInfo>)request.getAttribute("dataReceiverInfo");
                        %>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title pl-5" id="exampleModalLabel" style="color: #664229">Chọn Địa Chỉ Nhận Hàng</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body ml-4">
                                        <form action="BookCart?service=address&way=<%=payment%>" method="post">
                                            <div class="container-fluid">
                                                <%
                                                    if(!vectorReceiverInfo.isEmpty()) {
                                                    for(ReceiverInfo rcv : vectorReceiverInfo) {
                                                %>
                                                <div class="row pt-3 pb-3 border-bottom">
                                                    <input type="radio" id="receiver" value="<%=rcv.getID()%>" name="address">
                                                    <label class="pl-3" for="receiver">
                                                        <div class="" style="display: inline-block">
                                                            <%=rcv.getName()%>  &nbsp;|&nbsp;
                                                        </div>
                                                        <div class="" style="display: inline-block">
                                                            <%=rcv.getPhoneNo()%>
                                                        </div>
                                                        <div>
                                                            <%=rcv.getAddress()%>
                                                        </div>
                                                    </label>
                                                </div>
                                                <%
                                                    }
                                                    }
                                                %>

                                                <button type="button" class="btn mt-3 pt-2" style="background-color: white; border-color: #D2B48C; color: #987554" data-toggle="modal" data-dismiss="modal" data-target="#exampleModal1">
                                                    <i class="bi bi-plus"></i>  Thêm Địa Chỉ
                                                </button>

                                                <div class="row pt-4 pb-3">
                                                    <div class="col-3">
                                                    </div>
                                                    <div class="col-3">
                                                        <input class="px-3 py-2" style="background-color: white; color: #987554" type="button" value="Hủy" data-dismiss="modal">
                                                    </div>
                                                    <div class="col-3">
                                                        <input class="px-3 py-2" style="background-color: white; color: #987554" type="submit" name="submit" value="Thay Đổi">
                                                    </div>
                                                    <div class="col-3">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal -->
                        <div class="modal fade modal-second" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title pl-5" id="exampleModalLabel" style="color: #664229">Thêm Địa Chỉ</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="BookCart?service=addAddress" method="post">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <div class="col-4" style="padding-left: 12%">
                                                        Họ Và Tên:
                                                    </div>
                                                    <div class="col-8 pl-0">
                                                        <input type="text" style="width: 92%" placeholder="Nhập Họ Và Tên" name="Name">
                                                    </div>
                                                </div>
                                                <div class="row pt-3">
                                                    <div class="col-4" style="padding-left: 6%">
                                                        Số Điện Thoại:
                                                    </div>
                                                    <div class="col-8 pl-0">
                                                        <input type="text" style="width: 92%" placeholder="Nhập Số Điện Thoại" name="PhoneNo">
                                                    </div>
                                                </div>
                                                <div class="row pt-3">
                                                    <div class="col-4" style="padding-left: 16%">
                                                        Địa Chỉ:
                                                    </div>
                                                    <div class="col-8 pl-0">
                                                        <textarea placeholder="Nhập Địa Chỉ" name="Address" rows="3" cols="33"></textarea>
                                                    </div>
                                                </div>
                                                <div class="row pt-4 pb-3">
                                                    <div class="col-3">
                                                    </div>
                                                    <div class="col-3">
                                                        <input class="px-3 py-2" style="background-color: white; color: #987554" type="button" value="Quay Lại" data-dismiss="modal" data-toggle="modal" data-target="#exampleModal">
                                                    </div>
                                                    <div class="col-3">
                                                        <input class="px-3 py-2" style="background-color: white; color: #987554" type="submit" name="submit" onclick="sucess()" value="Thêm Địa Chỉ" >
                                                    </div>
                                                    <div class="col-3">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-lg-7 pl-0 pr-0">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12 text-center mt-3 mb-4" style="font-size: 23px; font-weight: 500; color: #664229"> Đơn Hàng </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-3 text-center" style="font-size: 20px; font-weight: 500; color: #664229">
                            </div>
                            <div class="col-3 text-center" style="font-size: 20px; font-weight: 500; color: #664229">
                                Sản Phẩm
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px; font-weight: 500; color: #664229">
                                Giá
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px; font-weight: 500; color: #664229">
                                Số Lượng
                            </div>
                            <div class="col-2 text-center" style="font-size: 20px; font-weight: 500; color: #664229">
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
                                <img class="ml-auto mr-auto mb-2" src="${pageContext.request.contextPath}<%=cart.getBookImg()%>" style="height: 160px; width: 120px">
                            </div>
                            <div class="col-3 text-center mt-auto mb-auto">
                                <%=cart.getBookID()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto myDIV">
                                <%=cart.getPrice()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto">
                                <%=cart.getQuantity()%>
                            </div>
                            <div class="col-2 text-center mt-auto mb-auto myDIV">
                                <%=total%>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row mt-4">
                            <div class="col-2">
                            </div>
                            <div class="col-8 border" style="border-radius: 10px">
                                <div class="dropdown text-center pt-3 pb-3" style="width: 100%">
                                    <%=name%>
                                    <div class="dropdown-content">
                                        <div class="pt-2 pb-2" onclick="card()"> Thẻ Tín Dụng / Ghi Nợ</a></div>
                                        <div><a href="BookCart?service=checkOut&way=ck"> Chuyển Khoản </a></div>
                                        <div><a href="BookCart?service=checkOut&way=cod"> Thanh Toán Khi Nhận Hàng </a></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                            </div>
                        </div>
                        <div class="row mt-5">
                            <div class="col-3">
                            </div>
                            <%
                                int ID = (int)request.getAttribute("ID");
                            %>
                            <div class="col-6 mt-5">
                                    <%
                                        if (name.equals("<i class=\"bi bi-credit-card-2-front\"></i> Chuyển Khoản")) {
                                    %>
                                    <img style="width: 100%; height: auto" src="https://img.vietqr.io/image/970423-03847457501-UIPvhPS.png?amount=<%=totalPrice%>&addInfo=BeS%20Ma%20Don%20Hang%20<%=ID%>">
                                    <%
                                        }
                                    %>
                            </div>
                            <div class="col-3">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-4 mt-3">
                <div class="col-5">
                </div>
                <div class="col-4 mt-auto mb-auto">
                    <div class="" style="font-size: 20px; font-weight: normal; display: inline-block; color: #664229"> Tổng thanh toán (<%=vectorC.size()%> sản phẩm): &nbsp;</div><div class="myDIV" style="font-size: 20px; display: inline-block"> <%=totalPrice%></div>
                </div>
                <%
                    if (name.equals("<i class=\"bi bi-cart-check\"></i> Phương Thức Thanh Toán")) {
                %>
                <div class="col-2">
                    <button onclick="pay()" class="button1 mt-auto mb-auto ml-5" style="font-size:20px; width: 150px; height: 60px;">Đặt Hàng</button>
                </div>
                <%
                    } else {
                %>
                <div class="col-2">
                    <a href="BookCart?service=payment&payment=<%=payment%>&address=<%=ReceiverAdr%>"><button class="button1 mt-auto mb-auto ml-5" style="font-size:20px; width: 150px; height: 60px;">Đặt Hàng</button></a>
                </div>
                <%
                    }
                %>
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

        <script>
            function card() {
                alert("Hiện tại, Cửa Hàng chưa hỗ trợ thanh toán qua thẻ\nVui Lòng chọn phương thức thanh toán khác!");
            }
            function pay() {
                alert("Vui Lòng chọn một phương thức thanh toán!");
            }
            function sucess() {
                alert("Thêm Địa Chỉ Thành Công!");
            }
        </script>
        <script>
            let x = document.querySelectorAll(".myDIV");
            for (let i = 0, len = x.length; i < len; i++) {
                let num = Number(x[i].innerHTML)
                        .toLocaleString('en');
                x[i].innerHTML = num;
            }
        </script>
        <script>
            $(document).on('hidden.bs.modal', function () {
                if ($('.modal:visible').length) {
                    $('body').addClass('modal-open');
                }
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
