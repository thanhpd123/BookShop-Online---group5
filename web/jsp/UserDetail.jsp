<%-- 
    Document   : UserDetail
    Created on : Jun 28, 2024, 11:51:17 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector, entity.Orders, entity.OrderDetail"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
            Account acc2 = (Account)session.getAttribute("acc");
            for (Account acc1 : vector) {
        %>
        <div class="container-fluid px-0">
            <div class="row pt-2 pb-2 border">
                <div class="col-10">
                </div>
                <div class="col-2">
                    <i class="bi bi-person h5"></i> <%=acc2.getLastName()%>
                </div>
            </div>
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
                                            <img style="border-radius: 50%; width: 70px; height: 70px" src="<%=acc1.getImgUser()%>">
                                        </div>
                                        <div class="col-6 mt-auto mb-auto pt-3 pb-3">
                                            <%=acc1.getFirstName()%> <%=acc1.getLastName()%>
                                            <form class="select-form pt-2" id="myForm" action="AdminController?service=changeRole&type=userdetail&userID=<%=acc1.getUserID()%>" method="post">
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
                            <div class="col-8 mt-auto mb-auto">
                                <div class="container-fluid">
                                    <div class="row border-left pt-3">
                                        <div class="col-3 pl-5" style="font-weight: 500; color: #664229;">
                                            Mã Khách Hàng:
                                        </div>
                                        <div class="col-2">
                                            <%=acc1.getUserID()%>
                                        </div>
                                        <div class="col-1">
                                        </div>
                                        <div class="col-1" style="font-weight: 500; color: #664229;">
                                            Email:
                                        </div>
                                        <div class="col-4">
                                            <%=acc1.getEmail()%>
                                        </div>
                                        <div class="col-1">
                                        </div>
                                    </div>
                                    <div class="row border-left pt-2 pb-3">
                                        <div class="col-3 pl-5" style="font-weight: 500; color: #664229;">
                                            Trạng Thái:
                                        </div>
                                        <div class="col-2 pl-0 pr-0">
                                            <%
                                                if (acc1.getStatus().equals("active")) {
                                            %>
                                            <div class="border text-center" style="border-radius: 10px; background-color: #3BB143; color: white; width: 80%">
                                                <%=acc1.getStatus()%>
                                            </div>
                                            <%
                                                } if (acc1.getStatus().equals("suspended")) {
                                            %>
                                            <div class="border text-center" style="border-radius: 10px; background-color: #B22222; color: white; width: 80%">
                                                <%=acc1.getStatus()%>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <div class="col-1">
                                        </div>
                                        <div class="col-1" style="font-weight: 500; color: #664229;">
                                            SĐT:
                                        </div>
                                        <div class="col-4">
                                            <%=acc1.getPhoneNo()%>
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
                                        <div class="border-bottom pt-3 pb-3 pl-4 w-100" style="font-size: 18px; font-weight: 500; color: #664229;">Thông Tin Cá Nhân</div>
                                    </div>
                                    <div class="row pt-3">
                                        <div class="col-3" style="font-weight: 500; color: #664229;">
                                            Giới Tính:
                                        </div>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-3" style="font-weight: 500; color: #664229;">
                                            Tuổi:
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-3">
                                            <h6 style="font-weight: normal"><%=(acc1.isGender()== true ? "Nam" : "Nữ")%></h6>
                                        </div>
                                        <%
                                            int age = (int)request.getAttribute("age");
                                        %>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-3">
                                            <h6 style="font-weight: normal"><%=age%></h6>
                                        </div>
                                    </div>
                                    <div class="row pt-4">
                                        <div class="col-4" style="font-weight: 500; color: #664229;">
                                            Ngày Đăng Ký:
                                        </div>
                                        <div class="col-2">
                                        </div>
                                        <div class="col-6"  style="font-weight: 500; color: #664229;">
                                            Ngày Tháng Năm Sinh:
                                        </div>
                                    </div>
                                    <div class="row pb-4">
                                        <div class="col-3">
                                            <h6 style="font-weight: normal"><%=acc1.getRegisterDate()%></h6>
                                        </div>
                                        <div class="col-3">
                                        </div>
                                        <div class="col-6">
                                            <h6 style="font-weight: normal"><%=acc1.getDOB()%></h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12" style="font-weight: 500; color: #664229;">
                                            Địa Chỉ:
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12">
                                            <h6 style="font-weight: normal"><%=acc1.getAddress()%></h6>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col pb-4" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pl-0 pr-0">
                                        <div class="border-bottom pt-3 pb-3 pl-4 w-100" style="font-size: 18px; font-weight: 500; color: #664229;">Lịch Sử Mua Hàng</div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-2 text-center pl-0 pr-0" style="font-weight: 500; color: #664229;">
                                            ID
                                        </div>
                                        <div class="col-3 text-center pl-0 pr-0" style="font-weight: 500; color: #664229;">
                                            Ngày Đặt Hàng
                                        </div>
                                        <div class="col-4 text-center pl-0 pr-0" style="font-weight: 500; color: #664229;">
                                            Trạng Thái
                                        </div>
                                        <div class="col-3 text-center pl-0 pr-0" style="font-weight: 500; color: #664229;">
                                            Tổng Thanh Toán
                                        </div>
                                    </div>
                                    <%
                                        Vector<Orders> vectorNew = (Vector<Orders>)request.getAttribute("dataNew");
                                        Vector<OrderDetail> vectorOrder = (Vector<OrderDetail>)request.getAttribute("dataOrder");
                                        int price = 0;
                                        for (Orders or : vectorNew) {
                                    %>
                                    <div class="row mt-3 mb-3 pt-3 pb-3 border" style="border-radius: 10px">
                                        <div class="col-2 text-center pl-0 pr-0">
                                            <%=or.getID()%>
                                        </div>
                                        <div class="col-3 text-center pl-0 pr-0">
                                            <%=or.getOrderDate()%>
                                        </div>
                                        <div class="col-4 text-center pl-0 pr-0">
                                            <%
                                                if (or.getOrderState().equals("Đang xử lý")) {
                                            %>
                                            <div class="border ml-auto mr-auto" style="border-radius: 10px; width: 80%; background-color: #e69b00; color: white">
                                                <%=or.getOrderState()%>
                                            </div>
                                            <%
                                                }
                                                if (or.getOrderState().equals("Đang giao hàng")) {
                                            %>
                                            <div class="border ml-auto mr-auto" style="border-radius: 10px; width: 80%; background-color: #00B4D8; color: white">
                                                <%=or.getOrderState()%>
                                            </div>
                                            <%
                                                }
                                                if (or.getOrderState().equals("Đã nhận hàng")) {
                                            %>
                                            <div class="border ml-auto mr-auto" style="border-radius: 10px; width: 80%; background-color: #3BB143; color: white">
                                                <%=or.getOrderState()%>
                                            </div>
                                            <%
                                                }
                                                if (or.getOrderState().equals("Đã hủy")) {
                                            %>
                                            <div class="border ml-auto mr-auto" style="border-radius: 10px; width: 80%; background-color: #B22222; color: white">
                                                <%=or.getOrderState()%>
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                        <%
                                            for (OrderDetail ord : vectorOrder) {
                                            price += ord.getPrice();
                                            }
                                        %>
                                        <div class="col-3 myDIV text-center pl-0 pr-0">
                                            <%=price%>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
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
            let x = document.querySelectorAll(".myDIV");
            for (let i = 0, len = x.length; i < len; i++) {
                let num = Number(x[i].innerHTML)
                        .toLocaleString('en');
                x[i].innerHTML = num;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
