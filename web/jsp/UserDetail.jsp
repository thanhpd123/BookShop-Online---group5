<%-- 
    Document   : UserDetail
    Created on : Jun 28, 2024, 11:51:17 AM
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <link href=
              "https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
              rel="stylesheet">
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
            for (Account acc1 : vector) {
        %>
        <div class="container-fluid">
            <div class="row mt-3 mb-3">
                <a href="AdminController?service=listUser" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại</a>
            </div>
            <div class="row" style="background-color: #F5F5F5; height: 100vh">
                <div class="col-1">
                </div>
                <div class="col-10 mt-4">
                    <div class="container-fluid">
                        <div class="row mb-4" style="background-color: white">
                            <div class="col-4">
                                <div class="container-fluid">
                                    <div class="row pt-3 pb-3">
                                        <div class="col-4 ml-2">
                                            <img style="border-radius: 50%; width: 70px; height: 70px" src="${pageContext.request.contextPath}/assets/qr.jfif<%--<%=acc1.getImgUser()%>--%>">
                                        </div>
                                        <div class="col-6 mt-auto mb-auto pt-3 pb-3">
                                            <%=acc1.getFirstName()%> <%=acc1.getLastName()%><br>
                                            <%=acc1.getRoleID()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-8 mt-auto mb-auto">
                                <div class="container-fluid">
                                    <div class="row border-left pt-4 pb-4">
                                        <div class="col-3 pl-5">
                                            Mã Khách Hàng:
                                        </div>
                                        <div class="col-2">
                                            <%=acc1.getUserID()%>
                                        </div>
                                        <div class="col-1">
                                        </div>
                                        <div class="col-1">
                                            Email:
                                        </div>
                                        <div class="col-4">
                                            <%=acc1.getEmail()%>
                                        </div>
                                        <div class="col-1">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row gap-4 mb-4">         
                            <div class="col pb-4" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="border-bottom pt-3 pb-3 pl-4 w-100" style="font-size: 18px">Thông Tin Cá Nhân</div>
                                    </div>
                                    <div class="row pt-3">
                                        <div class="col-3" style="color: gray">
                                            Giới Tính:
                                        </div>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-3" style="color: gray">
                                            Tuổi:
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-3">
                                            <h6><%=(acc1.isGender()== true ? "Nam" : "Nữ")%></h6>
                                        </div>
                                        <%
                                            int age = (int)request.getAttribute("age");
                                        %>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-3">
                                            <h6><%=age%></h6>
                                        </div>
                                    </div>
                                    <div class="row pt-4">
                                        <div class="col-3" style="color: gray">
                                            SĐT:
                                        </div>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-6" style="color: gray">
                                            Ngày Tháng Năm Sinh:
                                        </div>
                                    </div>
                                    <div class="row pb-4">
                                        <div class="col-3">
                                            <h6><%=acc1.getPhoneNo()%></h6>
                                        </div>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-6">
                                            <h6><%=acc1.getDOB()%></h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12" style="color: gray">
                                            Địa Chỉ:
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            <h6><%=acc1.getAddress()%></h6>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col" style="background-color: white">
                                g
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>
        <%
            }
        %>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
