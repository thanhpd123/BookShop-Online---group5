<%-- 
    Document   : AddAccount
    Created on : Jun 28, 2024, 11:51:02 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Tài Khoản</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
        %>
        <div class="container-fluid">
            <div class="row mt-3 mb-3">
                <a href="AdminController?service=listUser" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại</a>
            </div>
            <div class="row" style="background-color: #F5F5F5; height: 100vh">
                <div class="col-2">
                </div>
                <div class="col-8 pt-5 pl-5" style="background-color: white; margin-top: 5%; margin-bottom: 5%">
                    <div class="container-fluid">
                        <form action="AdminController" method="post">
                            <div class="row">
                                <div class="col">
                                </div>
                                <div class="col">
                                    <div class="row">
                                        <div class="col-2">
                                            Họ:
                                        </div>
                                        <div class="col-4">
                                            <input type="text"  name="FirstName">
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-2">
                                            Tên:
                                        </div>
                                        <div class="col-4">
                                            <input type="text"  name="LastName">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2">
                                    Email:
                                </div>
                                <div class="col-4">
                                    <input type="text"  name="Email" style="width: 100%">
                                </div>
                                <div class="col-2">
                                    Mật Khẩu:
                                </div>
                                <div class="col-4">
                                    <input type="text"  name="Password">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2">
                                    Loai
                                </div>
                                <div class="col-4">
                                    <select name="RoleID">
                                        <%
                                            for(Account acc : vector) {
                                        %>
                                        <option><%= acc.getRoleID()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-2">
                                    Giới Tính
                                </div>
                                <div class="col-4">
                                    <input type="radio" name="Gender" value="1" id="">Nam
                                    <input type="radio" name="Gender" value="2"  id="">Nữ
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2">
                                    SĐT:
                                </div>
                                <div class="col-4">
                                    <input type="text"  name="PhoneNo">
                                </div>
                                <div class="col-2">
                                    Địa Chỉ:
                                </div>
                                <div class="col-4">
                                    <input type="text"  name="Address">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <input type="submit" style="background-color: white" value="Thêm Tài Khoản" name="submit">
                                <input type="reset" style="background-color: white" value="Xóa">
                            </div>
                            <input type="hidden" name="service" value="add">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-2">
                </div>
            </div>
        </div>
    </body>
</html>
