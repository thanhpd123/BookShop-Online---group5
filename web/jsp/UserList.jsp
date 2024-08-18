<%-- 
    Document   : UserList
    Created on : Jun 28, 2024, 11:51:10 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                min-width: 150px;
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
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            String title = (String) request.getAttribute("titleTable");
            Account acc2 = (Account)session.getAttribute("acc");
            String service = (String)session.getAttribute("service");
            Vector<Account> vectorAll = (Vector<Account>)request.getAttribute("dataAll");
        %>
        <div class="container-fluid px-0">
            <div class="row pt-2 pb-2 border pr-0" style="">
                <div class="col-10">
                </div>
                <div class="col-2 pr-0">
                    <div class="dropdown">
                        Tài Khoản
                        <div class="dropdown-content">
                            <a href="userprofile"><i class="bi bi-person h5"></i> <%=acc2.getLastName()%></a>
                            <a href="LogOut">Đăng Xuất</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <h4 class="text-center mt-5" style="font-weight: 500; color: #664229;"><%=title%></h4>

        <div class="container-fluid mt-3 mb-4">
            <div class="row">
                <div class="col-1 ">
                </div>
                <div class="col-1 pl-3 pt-3 pb-3 pr-0" style="font-weight: 500; color: #664229">
                    Tìm Kiếm:
                </div>
                <div class="col-4 pl-0 pt-3 pb-3">
                    <!-- search bar -->
                    <div class="cl-lg-6 d-flex">
                        <form action="AdminController?service=search&page=1" method="POST">
                            <div style="display: inline-block"><input type="text" placeholder="Search ..." name="search" style="width: 350px"></div>
                            <div style="display: inline-block"><button type="submit" value="Search" name="submit" style="height: 35px; width: 50px; border-color: white; color: white; background-color: #E5D3B3"><i class="bi bi-search"></i></button></div>
                        </form>
                    </div>
                </div>
                <div class="col-6">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-3 pt-2 pb-2">
                            </div>
                            <div class="col-2 pt-3 pb-3"> 
                                <div class="dropdown">
                                    <div class="">Sắp Xếp</div>
                                    <div class="dropdown-content">
                                        <div class="inner-dropdown">
                                            <a href="#">ID</a>
                                            <div class="dropright-content">
                                                <a href="AdminController?service=sortIDASC&page=1">ID Tăng Dần</a>
                                                <a href="AdminController?service=sortIDDESC&page=1">ID Giảm Dần</a>
                                            </div>
                                        </div>
                                        <div class="inner-dropdown">
                                            <a href="#">Giá</a>
                                            <div class="dropright-content">
                                                <a href="AdminController?service=sort&type=Admin&page=1">Giá Tăng Dần</a>
                                                <a href="AdminController?service=sortPriceDESC&page=1">Giá Giảm Dần</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 pt-3 pb-3"> 
                                <div class="dropdown text-center">
                                    <i class="bi bi-filter"></i> Lọc
                                    <div class="dropdown-content">
                                        <div class="inner-dropdown">
                                            <a href="#">Giới Tính</a>
                                            <div class="dropright-content">
                                                <a href="AdminController?service=filter&type=GenderM&page=1">Nam</a>
                                                <a href="AdminController?service=filter&type=GenderF&page=1">Nữ</a>
                                            </div>
                                        </div>
                                        <div class="inner-dropdown">
                                            <a href="#">Vai Trò</a>
                                            <div class="dropright-content">
                                                <a href="AdminController?service=filter&type=Admin&page=1">Admin</a>
                                                <a href="AdminController?service=filter&type=Staff&page=1">Nhân Viên</a>
                                                <a href="AdminController?service=filter&type=Customer&page=1">Khách Hàng</a>
                                                <a href="AdminController?service=filter&type=Sale&page=1">Sale</a>
                                                <a href="AdminController?service=filter&type=Ship&page=1">Vận Chuyển</a>
                                            </div>
                                        </div>
                                        <div class="inner-dropdown">
                                            <a href="#">Trạng Thái</a>
                                            <div class="dropright-content" style="margin-top: 60px">
                                                <a href="AdminController?service=filter&type=Active&page=1">Hoạt Động</a>
                                                <a href="AdminController?service=filter&type=Suspended&page=1">Tạm Ngưng</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <a href="AdminController?service=add"><button class="button1 pt-2 pb-2 ml-5 mt-2 mb-4"> Thêm Khách Hàng </button></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid pl-0 mt-2 pr-0">
            <div class="row">
                <div class="col-1">
                </div>
                <div class="col-10">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3" style="font-weight: 500; color: #664229">
                                            ID
                                        </div>
                                        <div class="col-5" style="font-weight: 500; color: #664229">
                                            Ảnh
                                        </div>
                                        <div class="col-4" style="font-weight: 500; color: #664229">
                                            Loại
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 text-center" style="font-weight: 500; color: #664229">
                                Họ và Tên
                            </div>
                            <div class="col-6">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-5 text-center" style="font-weight: 500; color: #664229">
                                            Email
                                        </div>
                                        <div class="col-3 text-center pl-0 pr-0" style="font-weight: 500; color: #664229">
                                            Trạng Thái
                                        </div>
                                        <div class="col-2 text-center" style="font-weight: 500; color: #664229">
                                            Xem
                                        </div>
                                        <div class="col-2 text-center" style="font-weight: 500; color: #664229">
                                            Vô Hiệu
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
                            for (Account acc1 : vector) {
                        %>

                        <div class="row mt-3 mb-3 pb-4 pt-2 border-bottom">
                            <div class="col-4 text-center">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3 mb-auto mt-auto">
                                            <%=acc1.getUserID()%>
                                        </div>
                                        <div class="col-5">
                                            <img src="<%=acc1.getImgUser()%>" style="border-radius: 50%; width: 60px; height: 60px">
                                        </div>
                                        <div class="col-4 mb-auto mt-auto">
                                            <form class="select-form" id="myForm" action="AdminController?service=changeRole&type=userlist&userID=<%=acc1.getUserID()%>" method="post">
                                                <select name="roleID" id="mySelect">
                                                    <%
                                                        if (acc1.getStatus().equals("suspended")) {
                                                        
                                                    %>
                                                    <option><%=acc1.getRoleID()%>
                                                        <%
                                                            } else {
                                                            if(acc1.getRoleID().equals("Admin")) {
                                                        %>
                                                    <option value="1">Admin
                                                    <option value="2">Nhân viên
                                                    <option value="3">Khách Hàng
                                                    <option value="4">Sale
                                                    <option value="5">Vận Chuyển
                                                        <%
                                                            }
                                                            if(acc1.getRoleID().equals("Staff")) {
                                                        %>
                                                    <option value="2">Nhân viên
                                                    <option value="1">Admin
                                                    <option value="3">Khách Hàng
                                                    <option value="4">Sale
                                                    <option value="5">Vận Chuyển
                                                        <%
                                                            }
                                                            if(acc1.getRoleID().equals("Customer")) {
                                                        %>
                                                    <option value="3">Khách Hàng
                                                    <option value="1">Admin
                                                    <option value="2">Nhân viên
                                                    <option value="4">Sale
                                                    <option value="5">Vận Chuyển
                                                        <%
                                                            }
                                                            if(acc1.getRoleID().equals("Sale")) {
                                                        %>
                                                    <option value="4">Sale
                                                    <option value="1">Admin
                                                    <option value="2">Nhân viên
                                                    <option value="3">Khách Hàng
                                                    <option value="5">Vận Chuyển
                                                        <%
                                                            }
                                                            if(acc1.getRoleID().equals("Ship")) {
                                                        %>
                                                    <option value="5">Vận Chuyển
                                                    <option value="1">Admin
                                                    <option value="2">Nhân viên
                                                    <option value="3">Khách Hàng
                                                    <option value="4">Sale
                                                        <%
                                                            }
                                                            }
                                                        %>
                                                </select>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2 text-center mb-auto mt-auto">
                                <%=acc1.getFirstName()%> <%=acc1.getLastName()%>
                            </div>
                            <div class="col-6">
                                <div class="container-fluid" style="height: 100%">
                                    <div class="row" style="height: 100%">
                                        <div class="col-5 d-flex align-items-center">
                                            <%=acc1.getEmail()%>
                                        </div>
                                        <div class="col-3 text-center mb-auto mt-auto">
                                            <%
                                                if (acc1.getStatus().equals("active")) {
                                            %>
                                            <div class="border text-center" style="border-radius: 10px; background-color: #3BB143; color: white">
                                                <%=acc1.getStatus()%>
                                            </div>
                                            <%
                                                } if (acc1.getStatus().equals("suspended")) {
                                            %>
                                            <div class="border text-center" style="border-radius: 10px; background-color: #B22222; color: white">
                                                <%=acc1.getStatus()%>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <div class="col-2 text-center mb-auto mt-auto">
                                            <a href="AdminController?service=viewUser&userID=<%=acc1.getUserID()%>" style="color: #664229"><i class="bi bi-eye"></i></a>
                                        </div>
                                        <div class="col-2 text-center mb-auto mt-auto">
                                            <a href="AdminController?service=deactivate&userID=<%=acc1.getUserID()%>" class="h4" style="color: black" onclick="confirmNavigation(event)" style="color: #664229"><i class="bi bi-x"></i></a>
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
                                        if ( vectorAll.size() > 25 ) {
                                    %>
                                    <ul class="pagination" style="padding-left: 30%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=3">3</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=4">4</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=5">5</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <%
                                        }
                                        if ( vectorAll.size() <= 25 && vectorAll.size() > 20 ) {
                                    %>
                                    <ul class="pagination" style="padding-left: 40%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=3">3</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=4">4</a></li>
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
                                    <ul class="pagination" style="padding-left: 50%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=2">2</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=3">3</a></li>
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
                                    <ul class="pagination"  style="padding-left: 80%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=1">1</a></li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=2">2</a></li>
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
                                    <ul class="pagination"  style="padding-left: 100%">
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                        </li>
                                        <li class="page-item"><a class="page-link" href="AdminController?service=<%=service%>&page=1">1</a></li>
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
                        if (confirm('Thay Đổi Vai Trò Người Dùng?')) {
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
                if (!confirm('Bạn Có Muốn Tạm Ngưng Tài Khoản Này Không?')) {
                    event.preventDefault();
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
