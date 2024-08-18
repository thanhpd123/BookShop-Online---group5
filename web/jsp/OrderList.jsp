<%-- 
    Document   : OrderList
    Created on : Jul 17, 2024, 12:41:19 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Orders, entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
        <style>
            .dropdown {
                position: absolute;
                display: inline-block;
            }

            .dropbtn {
                background-color: #4CAF50;
                color: greenyellow;
                padding: 15px;
                border-radius: 5px;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 150px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #ddd;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropright-content {
                position: absolute;
                top: 0;
                left: 100%;
                min-width: 160px;
                color: black;
                text-decoration: none;
                display: none;
            }

            .dropright-content a {
                background-color: #f1f1f1;
            }

            .dropright-content a:hover {
                background-color: #ddd;
            }

            .inner-dropdown:hover .dropright-content {
                display: block;
            }
        </style>
    </head>
    <body>
        <c:import url="/jsp/SaleManage.jsp" />
        <%
            Account acc1 = (Account)session.getAttribute("acc");
            String service = (String)session.getAttribute("service");
        %>
        <div class="container-fluid">
            <div class="row pt-2 pb-2 border-bottom">
                <div class="col-10">
                </div>
                <div class="col-2">
                    <div class="dropdown">
                        Tài Khoản
                        <div class="dropdown-content">
                            <a href="userprofile"><i class="bi bi-person h5"></i> <%=acc1.getLastName()%></a>
                            <a href="LogOut">Đăng Xuất</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row pt-3">
                <div class="col-2">
                </div>
                <div class="col" style="font-weight: 500; color: #664229; font-size: 22px">
                    Danh Sách Đơn Hàng
                </div>
            </div>
            <div class="row mt-3 mb-3">
                <div class="col-2">
                </div>
                <div class="cl-lg-4 d-flex justify-content-center align-items-center">
                    <form action="SaleController?service=search&page=1" method="POST">
                        <div style="display: inline-block"><input type="text" placeholder="Search" name="search" style="width: 350px"></div>
                        <div style="display: inline-block"><button type="submit" value="Search" name="submit" style="height: 35px; width: 50px; border-color: white; color: white; background-color: #E5D3B3"><i class="bi bi-search"></i></button></div>
                    </form>
                </div>
                <div class="col-2">
                </div>
                <div class="col-2">
                    <div class="dropdown">
                        <div class="">Sắp Xếp</div>
                        <div class="dropdown-content">
                            <div class="inner-dropdown">
                                <a href="#">ID</a>
                                <div class="dropright-content">
                                    <a href="SaleController?service=sortIDASC&page=1">ID Tăng Dần</a>
                                    <a href="SaleController?service=sortIDDESC&page=1">ID Giảm Dần</a>
                                </div>
                            </div>
                            <div class="inner-dropdown">
                                <a href="#">Giá</a>
                                <div class="dropright-content">
                                    <a href="SaleController?service=sortPriceASC&page=1">Giá Tăng Dần</a>
                                    <a href="SaleController?service=sortPriceDESC&page=1">Giá Giảm Dần</a>
                                </div>
                            </div>
                            <div class="inner-dropdown">
                                <a href="#">Trạng Thái</a>
                                <div class="dropright-content" style="margin-top: 50px">
                                    <a href="SaleController?service=sortStateASC&page=1">Trạng Thái từ A-Z</a>
                                    <a href="SaleController?service=sortStateDESC&page=1">Trạng Thái từ Z-A</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
<!--                <div class="col-2">
                    <div class="dropdown">
                        <div class=""><i class="bi bi-filter"></i> Lọc</div>
                        <div class="dropdown-content">
                            <div class="inner-dropdown">
                                <a href="#">Trạng Thái</a>
                                <div class="dropright-content">
                                    <a href="SaleController?service=filter&type=1&page=1">Đang xử lý</a>
                                    <a href="SaleController?service=filter&type=2&page=1">Đang giao hàng</a>
                                    <a href="SaleController?service=filter&type=3&page=1">Đã giao hàng</a>
                                    <a href="SaleController?service=filter&type=4&page=1">Đã hủy</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>-->
            </div>
            <div class="row">
                <div class="col-1">
                </div>
                <div class="col-10">
                    <div class="container-fluid">
                        <div class="row pt-3 pb-4">
                            <div class="col-3 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4" style="font-weight: 500; color: #664229">
                                            ID
                                        </div>
                                        <div class="col-8" style="font-weight: 500; color: #664229">
                                            Ngày Đặt Hàng
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 text-center" style="font-weight: 500; color: #664229">
                                Khách Hàng
                            </div>
                            <div class="col-3 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-5" style="font-weight: 500; color: #664229">
                                            Tổng Tiền
                                        </div>
                                        <div class="col-7" style="font-weight: 500; color: #664229">
                                            Trạng Thái
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-6" style="font-weight: 500; color: #664229">
                                            Mã Nhân Viên
                                        </div>
                                        <div class="col-3" style="font-weight: 500; color: #664229">
                                            Xem
                                        </div>
                                        <div class="col-3" style="font-weight: 500; color: #664229">
                                            Hủy
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            Vector<Orders> vectorList = (Vector<Orders>)request.getAttribute("dataList");
                            Vector<Orders> vectorAll = (Vector<Orders>)request.getAttribute("dataAll");
                            Vector<Orders> vector = (Vector<Orders>)request.getAttribute("data");
                            for(Orders or : vectorList) {
                        %>
                        <div class="row pt-3 pb-3 border mb-4" style="border-radius: 10px">
                            <div class="col-3 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4">
                                            <%=or.getID()%>
                                        </div>
                                        <div class="col-8">
                                            <%=or.getOrderDate()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 text-center">
                                <%=or.getFName()%> <%=or.getLName()%>
                            </div>
                            <div class="col-3 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-5 myDIV">
                                            <%=or.getPrice()%>
                                        </div>
                                        <div class="col-7">
                                            <form class="select-form" id="myForm" action="SaleController?service=changeStatus&id=<%=or.getID()%>&order=orderlist" method="post">
                                                <select name="state" id="mySelect" style="width: 150px; height: 30px"> 
                                                    <%
                                                        if (or.getOrderState().equals("Đang xử lý")) {
                                                    %>
                                                    <option value="1"><%=or.getOrderState()%>
                                                    <option value="2">Đang giao hàng
                                                    <option value="3">Đã giao hàng
                                                        <%
                                                            }
                                                            if (or.getOrderState().equals("Đang giao hàng")) {
                                                        %>
                                                    <option value="2"><%=or.getOrderState()%>
                                                    <option value="1">Đang xử lý
                                                    <option value="3">Đã giao hàng
                                                        <%
                                                            }
                                                            if (or.getOrderState().equals("Đã giao hàng")) {
                                                        %>
                                                    <option value="3"><%=or.getOrderState()%>
                                                    <option value="1">Đang xử lý
                                                    <option value="2">Đang giao hàng
                                                        <%
                                                            }
                                                            if (or.getOrderState().equals("Đã hủy")) {
                                                        %>
                                                    <option value="4"><%=or.getOrderState()%>
                                                        <%
                                                            }
                                                        %>
                                                </select>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-6">
                                            <%
                                                if (or.getOrderState().equals("Đang xử lý")) {
                                            %>
                                            -
                                            <%
                                                } else {
                                            %>
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-workspace" viewBox="0 0 16 16">
                                            <path d="M4 16s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1zm4-5.95a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5"/>
                                            <path d="M2 1a2 2 0 0 0-2 2v9.5A1.5 1.5 0 0 0 1.5 14h.653a5.4 5.4 0 0 1 1.066-2H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v9h-2.219c.554.654.89 1.373 1.066 2h.653a1.5 1.5 0 0 0 1.5-1.5V3a2 2 0 0 0-2-2z"/>
                                            </svg>
                                            <%
                                                } 
                                            %>
                                        </div>
                                        <div class="col-3">
                                            <%
                                                for (Orders ord : vector) {
                                                    int id = ord.getID();
                                                    if(id == or.getID()) {
                                            %>
                                            <a href="SaleController?service=orderDetail&id=<%=or.getID()%>&userID=<%=ord.getUserID()%>" style="color: black"><i class="bi bi-eye"></i></a>
                                                <%
                                                        }
                                                    }
                                                %>
                                        </div>
                                        <div class="col-3">
                                            <a style="color: black" onclick="confirmNavigation(event)" href="SaleController?service=deactivate&id=<%=or.getID()%>&order=orderlist"><i class="bi bi-x h4"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row mt-5">
                            <div class="col-4">
                            </div>
                            <div class="col-4 d-flex">
                                <nav aria-label="Page navigation example">
                                    <%
                                        if ( vectorAll.size() > 35 ) {
                                    %>
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=3">3</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=4">4</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=5">5</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <%
                                        }
                                        if ( vectorAll.size() <= 28 && vectorAll.size() > 21 ) {
                                    %>
                                    <ul class="pagination" style="padding-right: 19px">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=3">3</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=4">4</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <%
                                        }
                                        if ( vectorAll.size() <= 21 && vectorAll.size() > 14  ) {
                                    %>
                                    <ul class="pagination">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=3">3</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <%
                                        }
                                        if ( vectorAll.size() <= 14 && vectorAll.size() > 7 ) {
                                    %>
                                    <ul class="pagination" style="margin-left: 110%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <%
                                        }
                                        if ( vectorAll.size() <= 7 ) {
                                    %>
                                    <ul class="pagination" style="padding-right: 67px">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="SaleController?service=<%=service%>&page=1">1</a></li>
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
                        </div>
                    </div>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var forms = document.querySelectorAll('.select-form');

                forms.forEach(function (form) {
                    var select = form.querySelector('select');
                    var previousValue = select.value;

                    select.addEventListener('change', function () {
                        if (confirm('Thay Đổi Trạng Thái Đơn Hàng?')) {
                            form.submit();
                        } else {
                            select.value = previousValue;
                        }
                        previousValue = select.value;
                    });
                });
            });
        </script>
        <script>
            function confirmNavigation(event) {
                if (!confirm('Bạn Có Muốn Hủy Đơn Hàng Này Không?')) {
                    event.preventDefault();
                }
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
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
