<%-- 
    Document   : MyOrders
    Created on : May 27, 2024, 11:56:54 PM
    Author     : ADMIN
--%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account,entity.OrderDetail,entity.Orders, java.util.Enumeration, jakarta.servlet.http.HttpSession, java.util.Vector"%>
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
        <!-- main content -->
        <div class="container mt-5" style="min-height: 50vh">
            <div class="row">
                <!-- user info -->
                <div class="col-md-3 user-info">
                    <a href="userprofile"><img src="${acc.getImgUser()}" alt="User Image" width="200" height="200"></a>
                    <h3><a href="userprofile" style="color: red"><c:if test="${acc != null}">${acc.getFirstName()} ${acc.getLastName()}</c:if></a></h3>
                        <a href="OrderController" style="color: #D2B48C; font-size: 20px;">My Order</a>
                        <br>
                        <a href="FeedBackServlet" style="color: #D2B48C; font-size: 20px;">Feedback</a>
                    </div>


                    <!-- order table and pagination -->
                    <div class="col-md-9">
                        <!-- Search box -->
                        <div class="search-box mb-3">
                            <form action="" method="GET">
                                <div class="input-group">
                                    <select class="form-control mr-2" name="searchQuery" style="width: auto;">
                                        <option value="" ${searchQuery == '' ? 'selected' : ''}>All Orders</option>
                                    <option value="Đang giao hàng" ${searchQuery == 'Đang giao hàng' ? 'selected' : ''}>Đang giao hàng</option>
                                    <option value="Đã giao hàng" ${searchQuery == 'Đã giao hàng' ? 'selected' : ''}>Đã giao hàng</option>
                                    <option value="Đang xử lý" ${searchQuery == 'Đang xử lý' ? 'selected' : ''}>Đang xử lý</option>
                                </select>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="submit">Filter</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!-- order table -->
                    <div class="order-table">
                        <h1 style="font-size: 24px; font-family: 'Georgia', sans-serif; color: #333; text-align: center; padding: 10px;">My Order</h1>
                        <table class="table table-bordered">
                            <input type="hidden" name="UserID" value="${acc.getUserID()}" />
                            <thead class="thead-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Ngày Đặt Hàng</th>
                                    <th>Trạng Thái</th>
                                    <th>Tổng Tiền</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    Vector<Orders> vector = (Vector<Orders>) request.getAttribute("dataList");
                                    for(Orders or: vector) {
                                %>
                                <%--<c:forEach items="${ListOrders}" var="a">--%>
                                <tr>
                                    <td><%=or.getOrderID()%></td>
                                    <td><%=or.getOrderDate()%></td>
                                    <td><%=or.getOrderState()%></td>
                                    <td><%=or.getReceiverID()%></td>
                                    <td><a href="ThisOrderServlet?ID=<%=or.getOrderID()%>">details</a></td>
                                </tr>
                                <%
                                    }
                                %>
<%--                                <tr>
                                    <td>${a.getOrderID()}</td>
                                    <td>${a.getOrderDate()}</td>
                                    <td>${a.getOrderState()}</td>
                                    <td>${a.getID()}</td>
                                    <td><a href="ThisOrderServlet?OrderID=${a.getOrderID()}">details</a></td>
                                </tr>--%>
                                <%--</c:forEach>--%>
                            </tbody>
                        </table>
                    </div>

                    <!-- pagination -->

                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <c:if test="${currentPage != 1}">
                                <li class="page-item">
                                    <a class="page-link" href="OrderController?page=${currentPage - 1}&searchQuery=${searchQuery}">Previous</a>
                                </li>
                            </c:if>

                            <c:forEach begin="1" end="${totalPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="page-item active"><a class="page-link" href="#">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="OrderController?page=${i}&searchQuery=${searchQuery}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                            <c:if test="${currentPage lt totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="OrderController?page=${currentPage + 1}&searchQuery=${searchQuery}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
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
