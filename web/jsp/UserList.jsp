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
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            String title = (String) request.getAttribute("titleTable");
        %>
        <h4 class="text-center mt-5" style="font-weight: normal"><%=title%></h4>
        <a href="AdminController?service=add"><button class="pt-2 pb-2 ml-5 mt-2 mb-4" style="background-color: white"> Thêm Khách Hàng </button></a>

        <div class="container-fluid mt-3 mb-4">
            <div class="row">
                <div class="col-6">
                    <!-- search bar -->
                    <div class="cl-lg-6 d-flex justify-content-center align-items-center">
                        <form action="AdminController?service=search" method="POST">
                            <div style="display: inline-block"><input type="text" placeholder="Search ..." name="search" style="width: 350px"></div>
                            <div style="display: inline-block"><input type="submit" value="Search" name="submit"></div>
                        </form>
                    </div>
                </div>
                <div class="col-3">
                </div>
                <div class="col-3">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4">

                            </div>
                            <div class="col-4"> 
                                <div class="dropdown text-center">
                                    Sắp Xếp
                                    <div class="dropdown-content">
                                        <a href="AdminController?service=sortIDASC"><i class="bi bi-sort-up"></i>ID tăng dần</a>
                                        <a href="AdminController?service=sortIDDESC"><i class="bi bi-sort-down-alt"></i>ID giảm dần</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4"> 
                                <div class="dropdown text-center">
                                    <i class="bi bi-filter"></i> Lọc
                                    <div class="dropdown-content">
                                        <a href="AdminController?service=getGender&gender=1">Giới Tính Nam</a>
                                        <a href="AdminController?service=getGender&gender=0">Giới Tính Nữ</a>
                                        <a href="AdminController?service=getRole">Vai Trò</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid pl-0 mt-2 pr-0">
            <div class="row">
                <div class="col-3 text-center">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-2">
                                STT
                            </div>
                            <div class="col-8">
                                Ảnh
                            </div>
                            <div class="col-2">
                                ID
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-1 text-center">Loại</div>
                <div class="col-1 text-center">Họ</div>
                <div class="col-1 text-center">Tên</div>
                <div class="col-3 text-center">Email</div>
                <div class="col-1 text-center">Trang Thai</div>
                <div class="col-2">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4 text-center">
                                Xem
                            </div>
                            <div class="col-4 text-center">
                                Chỉnh sửa
                            </div>
                            <div class="col-4 text-center">
                                Xóa
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
            int n = 0;
            for (Account acc1 : vector) {
            n++;
            %>
            <div class="row mt-3 mb-3 pb-3 border-bottom">
                <div class="col-3 text-center">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-2 text-center">
                                <%=n%>
                            </div>
                            <div class="col-8 text-center">
                                <img src="${pageContext.request.contextPath}<%=acc1.getImgUser()%>">
                            </div>
                            <div class="col-2 text-center">
                                <%=acc1.getUserID()%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-1 text-center"><%=acc1.getRoleID()%></div>
                <div class="col-1 text-center"><%=acc1.getFirstName()%></div>
                <div class="col-1 text-center"><%=acc1.getLastName()%></div>
                <div class="col-3 pl-3"><%=acc1.getEmail()%></div>
                <div class="col-1 text-center"><%=acc1.getStatus()%></div>
                <div class="col-2">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-4 text-center">
                                <a href="AdminController?service=viewUser&userID=<%=acc1.getUserID()%>" style="color: #664229"><i class="bi bi-eye"></i></a>
                            </div>
                            <div class="col-4 text-center">
                                <a href="AdminController?service=update&userID=<%=acc1.getUserID()%>" style="color: #664229"><i class="bi bi-pen"></i></a>
                            </div>
                            <div class="col-4 text-center">
                                <a href="" class="h4" style="color: #664229"><i class="bi bi-x"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%
            }
            %>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
