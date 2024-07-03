<%-- 
    Document   : AddAccount
    Created on : Jun 28, 2024, 11:51:02 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector, entity.Roles"%>
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
            Vector<Roles> vectorRole = (Vector<Roles>) request.getAttribute("dataRole");
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
                        <div class="row mb-3">
                            <div class="col-12 text-center">
                                <h4>Thêm Người Dùng</h4>
                            </div>
                        </div>
                        <form action="AdminController" method="post">
                            <div class="row">
                                <div class="col-5 pl-5">
                                    Họ:
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-5">
                                    Tên:
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-5 pl-4">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 100%; color: gray;" value="Nhập Họ" name="FirstName">
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-4">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 100%; color: gray;" value="Nhập Tên" name="LastName">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-5 pl-5">
                                    Email:
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-5">
                                    Mật Khẩu:
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-5 pl-4">
                                    <input class="pt-1 pl-3 pb-1" type="text"  name="Email" value="Nhập Email" style="width: 100%; color: gray;">
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-4">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 100%; color: gray;" value="Nhập Mật Khẩu" name="Password">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2 pl-5">
                                    Loại:
                                </div>
                                <div class="col-3">
                                    <select name="RoleID">
                                        <%
                                            for(Roles role : vectorRole) {
                                        %>
                                        <option value="<%= role.getRoleID()%>"><%= role.getRoleName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-5">
                                    Số Điện Thoại:
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2 pl-5">
                                    Giới Tính:
                                </div>
                                <div class="col-3">
                                    <input type="radio" name="Gender" value="1" id="">Nam
                                    <input type="radio" name="Gender" value="2"  id="">Nữ
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-5 pl-4">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 100%; color: gray;" value="Nhập SĐT" name="PhoneNo">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-2 pl-5 mt-auto mb-auto">
                                    Địa Chỉ:
                                </div>
                                <div class="col-9">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 100%; color: gray;" value="Nhập Địa Chỉ" name="Address">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-3">
                                </div>
                                <div class="col-4">
                                    <input type="reset" style="background-color: white" value="Xóa">
                                </div>
                                <div class="col-1">
                                </div>
                                <div class="col-4">
                                    <input type="submit" class="pt-2 pb-2 mr-0 ml-5" style="background-color: white" value="Thêm Tài Khoản" name="submit">
                                </div>
                            </div>
                            <input type="hidden" name="service" value="add">
                        </form>
                    </div>
                </div>
                <div class="col-2">
                </div>
            </div>
        </div>
    </body>
</html>
