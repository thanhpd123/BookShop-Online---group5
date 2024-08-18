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
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Account acc2 = (Account)session.getAttribute("acc");
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
            Vector<Roles> vectorRole = (Vector<Roles>) request.getAttribute("dataRole");
        %>
        <div class="container-fluid">
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
            <div class="row mt-3 mb-3">
                <a href="AdminController?service=listUser" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại</a>
            </div>
            <div class="row" style="background-color: #F5F5F5; height: 100vh">
                <div class="col-2">
                </div>
                <div class="col-8 pt-5 pl-5" style="background-color: white; margin-top: 5%; margin-bottom: 5%; height: 60vh">
                    <div class="container-fluid">
                        <div class="row mb-3">
                            <div class="col-12 text-center">
                                <h4 style="font-weight: 500; color: #664229;">Thêm Người Dùng</h4>
                            </div>
                        </div>
                        <form action="AdminController" onsubmit="return validateForm()" method="post">
                            <div class="row">
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 10%">
                                    Họ:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 80%; color: gray;" id="fname" placeholder="Nhập Họ" name="FirstName">
                                </div>
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 10%">
                                    Tên:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input class="pt-1 pl-3 pb-1" type="text" style="width: 80%; color: gray;" id="lname" placeholder="Nhập Tên" name="LastName">
                                </div>
                            </div>
                            <div class="row mt-4">
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 8%">
                                    Email:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input class="pt-1 pl-3 pb-1" type="text"  name="Email" id="email" placeholder="Nhập Email" style="width: 150%; color: gray;">
                                </div>
                            </div>
                            <div class="row mt-4">
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 4.5%">
                                    Mật Khẩu:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input class="pt-1 pl-3 pb-1" type="password" id="pw" style="width: 80%; color: gray;" placeholder="Nhập Mật Khẩu" name="Password">
                                </div>
                                <div class="col-2 mt-auto mb-auto">
                                    Số Điện Thoại:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input class="pt-1 pl-3 pb-1" type="text" id="sdt" style="width: 80%; color: gray;" placeholder="Nhập SĐT" name="PhoneNo">
                                </div>
                            </div>
                            <div class="row mt-4">
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 9%">
                                    Loại:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <select name="RoleID" class="pl-3" style="width: 50%; height: 140%">
                                        <%
                                            for(Roles role : vectorRole) {
                                        %>
                                        <option value="<%= role.getRoleID()%>"><%= role.getRoleName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 5.6%">
                                    Giới Tính:
                                </div>
                                <div class="col-4 pl-0 pr-0">
                                    <input type="radio" name="Gender" value="1" id="">  Nam
                                    <input class="ml-4" type="radio" name="Gender" value="2"  id="">  Nữ
                                </div>
                            </div>
                            <div class="row mt-4 pt-2 pb-2">
                                <div class="col-2 mt-auto mb-auto" style="padding-left: 6.5%">
                                    Địa Chỉ:
                                </div>
                                <div class="col-9 pl-0 pr-0">
                                    <textarea class="pl-3" placeholder="Nhập Địa Chỉ" id="adr" name="Address" rows="2" cols="52"></textarea>
                                </div>
                            </div>
                            <div class="row mt-4">
                                <div class="col-3">
                                </div>
                                <div class="col-2">
                                </div>
                                <div class="col-3">
                                    <input type="reset" class="pt-2 pb-2 " style="background-color: white; width: 50%; margin-left: 70%; color: #664229; border-color: #664229" value="Xóa">
                                </div>
                                <div class="col-4">
                                    <input type="submit" class="pt-2 pb-2 mr-0 ml-5" style="background-color: white; border-color: #664229; color: #664229" value="Thêm Tài Khoản" name="submit">
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
        <script type="text/javascript">
            function validateForm() {
                var sdt = document.getElementById("sdt").value;
                var fname = document.getElementById("fname").value;
                var lname = document.getElementById("lname").value;
                var email = document.getElementById("email").value;
                var adr = document.getElementById("adr").value;
                var pw = document.getElementById("pw").value;
                if (sdt.trim().length > 10) {
                    alert("Số điện thoại không được quá 10 số!");
                    return false;
                }
                if (fname.trim() === "" || lname.trim() === "" || adr.trim() === "" || email.trim() === "" || pw.trim() === "") {
                    alert("Vui lòng điền đầy đủ thông tin!");
                    return false;
                }
                return true;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
