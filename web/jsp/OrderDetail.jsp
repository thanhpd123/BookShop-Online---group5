<%-- 
    Document   : OrderDetail
    Created on : Jul 12, 2024, 3:18:21 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Orders, entity.OrderDetail, entity.Account, entity.ReceiverInfo, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <%
            Account acc1 = (Account)session.getAttribute("acc");
        %>
        <c:import url="/jsp/SaleManage.jsp" />
        <div class="container-fluid">
            <div class="row pt-2 pb-2 border-bottom">
                <div class="col-10">
                </div>
                <div class="col-2">
                    <div class="dropdown">
                        Tài Khoản
                        <div class="dropdown-content">
                            <a ><i class="bi bi-person h5"></i> <%=acc1.getLastName()%></a>
                            <a href="LogOut">Đăng Xuất</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-3 mb-3">
                <a href="SaleController?service=orderList" style="color: black; margin-left: 5%"><i class="bi bi-arrow-90deg-left"></i>  Quay lại</a>
            </div>
            <%
                Vector<Account> vectorUser = (Vector<Account>)request.getAttribute("dataUser");
                Vector<ReceiverInfo> vectorRcv = (Vector<ReceiverInfo>)request.getAttribute("dataRcv");
            %>
            <div class="row" style="background-color: #F5F5F5; min-height: 100vh">
                <div class="col-1">
                </div>
                <div class="col-10 mt-4">
                    <div class="container-fluid pl-0 pr-0">
                        <div class="row">
                            <div class="col mr-2 pl-0 pr-0" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pt-3 pb-3">
                                        <div class="col-3">
                                            <div class="d-flex align-items-center" style="width: 100%; height: 100%">
                                                <div class="pl-3 d-flex flex-column">
                                                    <div style="font-weight: 500; color: #664229">
                                                        Khách Hàng
                                                    </div>
                                                    <div class="d-flex">
                                                        <div style="font-weight: 500; color: #664229">
                                                            ID:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorUser.get(0).getUserID()%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <div class="border-left pl-4 d-flex align-items-center">
                                                <div class="flex-column">
                                                    <div class="d-flex pb-2">
                                                        <div style="font-weight: 500; color: #664229">
                                                            Họ Và Tên:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorUser.get(0).getFirstName()%> <%=vectorUser.get(0).getLastName()%>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex pb-2">
                                                        <div class="" style="font-weight: 500; color: #664229">
                                                            Địa Chỉ: &nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorUser.get(0).getAddress()%>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex">
                                                        <div style="font-weight: 500; color: #664229">
                                                            Số Điện Thoại:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorUser.get(0).getPhoneNo()%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col ml-2" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pt-3 pb-3">
                                        <div class="col-3">
                                            <div class="d-flex align-items-center" style="width: 100%; height: 100%">
                                                <div class="d-flex flex-column">
                                                    <div style="font-weight: 500; color: #664229">
                                                        Người Nhận
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-9">
                                            <div class="border-left pl-4 d-flex align-items-center">
                                                <div class="flex-column">
                                                    <div class="d-flex pb-2">
                                                        <div style="font-weight: 500; color: #664229">
                                                            Họ Và Tên:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorRcv.get(0).getName()%>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex pb-2">
                                                        <div class="" style="font-weight: 500; color: #664229">
                                                            Địa Chỉ:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorRcv.get(0).getAddress()%>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex">
                                                        <div style="font-weight: 500; color: #664229">
                                                            Số Điện Thoại:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=vectorRcv.get(0).getPhoneNo()%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            Vector<Orders> vectorOrd = (Vector<Orders>)request.getAttribute("dataOrd");
                            for (Orders or : vectorOrd) {
                        %>
                        <div class="row mt-3" style="background-color: white">
                            <div class="col mr-2 pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row pt-4 pb-4">
                                        <div class="col-3">
                                            <div class="d-flex pl-3 align-items-center" style="width: 100%; height: 100%">
                                                <div class="d-flex flex-column">
                                                    <div style="font-weight: 500; color: #664229">
                                                        Đơn Hàng
                                                    </div>
                                                    <div class="d-flex">
                                                        <div style="font-weight: 500; color: #664229">
                                                            ID:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=or.getID()%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-9 border-right">
                                            <div class="border-left pl-4 d-flex align-items-center">
                                                <div class="flex-column">
                                                    <div class="d-flex pb-2">
                                                        <div style="font-weight: 500; color: #664229">
                                                            Ngày Đặt Hàng:&nbsp;
                                                        </div>
                                                        <div>
                                                            <%=or.getOrderDate()%>
                                                        </div>
                                                    </div>
                                                    <div class="d-flex pb-2">
                                                        <div class="" style="font-weight: 500; color: #664229">
                                                            Phương Thức Thanh Toán: &nbsp;
                                                        </div>
                                                        <div>
                                                            <%
                                                                if(or.getPayment().equals("ck")){
                                                            %>
                                                            Chuyển Khoản
                                                            <%
                                                                }
                                                                if(or.getPayment().equals("cod")){
                                                            %>
                                                            COD
                                                            <%
                                                                }
                                                            %>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                int iduser = or.getUserID();
                if (iduser >= 1) {
                            %>
                            <div class="col ml-2">
                                <div class="d-flex pl-3 align-items-center" style="width: 100%; height: 100%">
                                    <div class="d-flex flex-column">
                                        <div class="d-flex">
                                            <div style="font-weight: 500; color: #664229">
                                                Trạng Thái Đơn Hàng:&nbsp;&nbsp;
                                            </div>
                                            <div>
                                                <form id="myForm" action="SaleController?service=changeStatus&id=<%=or.getID()%>&order=orderdetail" method="post">
                                                    <select id="mySelect" name="state" style="width: 150px; height: 30px">
                                                        <%
                                                            if (or.getOrderState().equals("Đang xử lý")) {
                                                        %>
                                                        <option value="1"><%=or.getOrderState()%>
                                                        <option value="2">Đang giao hàng
                                                        <option value="3">Đã giao hàng
                                                        <option value="4">Đã hủy
                                                            <%
                                                                }
                                                                if (or.getOrderState().equals("Đang giao hàng")) {
                                                            %>
                                                        <option value="2"><%=or.getOrderState()%>
                                                        <option value="1">Đang xử lý
                                                        <option value="3">Đã giao hàng
                                                        <option value="4">Đã hủy
                                                            <%
                                                                }
                                                                if (or.getOrderState().equals("Đã giao hàng")) {
                                                            %>
                                                        <option value="3"><%=or.getOrderState()%>
                                                        <option value="1">Đang xử lý
                                                        <option value="2">Đang giao hàng
                                                        <option onclick="confirm()" value="4">Đã hủy
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
                                        <div class="d-flex">
                                            <div style="font-weight: 500; color: #664229">
                                                ID Nhân Viên:&nbsp;
                                            </div>
                                            <div>
                                                <%=or.getUserID()%> - <%=or.getLName()%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
                                } else {
                            %>
                            m
                            <div class="col ml-2">
                                <div class="d-flex pl-3 align-items-center" style="width: 100%; height: 100%">
                                    <div class="d-flex flex-column">
                                        <div class="d-flex">
                                            <div style="font-weight: 500; color: #664229">
                                                Trạng Thái Đơn Hàng:&nbsp;&nbsp;
                                            </div>
                                            <div>
                                                <form id="myForm" action="SaleController?service=changeStatus&id=<%=or.getID()%>&order=orderdetail" method="post">
                                                    <select id="mySelect" name="state" style="width: 150px; height: 30px">
                                                        <%
                                                            if (or.getOrderState().equals("Đang xử lý")) {
                                                        %>
                                                        <option value="1"><%=or.getOrderState()%>
                                                        <option value="2">Đang giao hàng
                                                        <option value="3">Đã giao hàng
                                                        <option value="4">Đã hủy
                                                            <%
                                                                }
                                                                if (or.getOrderState().equals("Đang giao hàng")) {
                                                            %>
                                                        <option value="2"><%=or.getOrderState()%>
                                                        <option value="1">Đang xử lý
                                                        <option value="3">Đã giao hàng
                                                        <option value="4">Đã hủy
                                                            <%
                                                                }
                                                                if (or.getOrderState().equals("Đã giao hàng")) {
                                                            %>
                                                        <option value="3"><%=or.getOrderState()%>
                                                        <option value="1">Đang xử lý
                                                        <option value="2">Đang giao hàng
                                                        <option onclick="confirm()" value="4">Đã hủy
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
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            }
                        %>
                        <div class="row mt-3 pt-3 pb-2" style="background-color: white">
                            <div class="col-3 pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4 text-center" style="font-weight: 500; color: #664229">
                                            STT
                                        </div>
                                        <div class="col-8 text-center">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">         
                                        <div class="col-7 text-center" style="font-weight: 500; color: #664229">
                                            Sản Phẩm
                                        </div>
                                        <div class="col-5 text-center" style="font-weight: 500; color: #664229">
                                            Tác Giả
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-5 pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4 text-center" style="font-weight: 500; color: #664229">
                                            Thể Loại
                                        </div>
                                        <div class="col-3 text-center" style="font-weight: 500; color: #664229">
                                            Số Lượng
                                        </div>
                                        <div class="col-2 text-center" style="font-weight: 500; color: #664229">
                                            Giá
                                        </div>
                                        <div class="col-3 text-center" style="font-weight: 500; color: #664229">
                                            Tổng Tiền
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            Vector<OrderDetail> vectorOrdDtl = (Vector<OrderDetail>)request.getAttribute("dataOrdDtl");
                            int i = 0;
                            int price = 0;
                            int totalPrice = 0;
                            for (OrderDetail ord : vectorOrdDtl) {
                            i++;
                            price = ord.getQuantity()*ord.getPrice();
                            totalPrice += price;
                        %>
                        <div class="row pt-3" style="background-color: white">
                            <div class="col-3 pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4 mt-auto mb-auto text-center">
                                            <%=i%>
                                        </div>
                                        <div class="col-8 mt-auto mb-auto text-center">
                                            <img src="${pageContext.request.contextPath}<%=ord.getBookImg()%>" style="width: 120px; height: 160px">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4 mt-auto mb-auto pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">    
                                        <div class="col-7 mt-auto mb-auto text-center d-flex align-items-center justify-content-center">
                                            <%=ord.getName()%>
                                        </div>
                                        <div class="col-5 text-center mt-auto mb-auto d-flex align-items-center justify-content-center">
                                            <%=ord.getAuthorName()%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-5 mt-auto mb-auto pl-0 pr-0">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4 text-center d-flex align-items-center justify-content-center">
                                            <%=ord.getCategoryName()%>
                                        </div>
                                        <div class="col-3 text-center">
                                            <%=ord.getQuantity()%>
                                        </div>
                                        <div class="myDIV col-2 text-center">
                                            <%=ord.getPrice()%>
                                        </div>
                                        <div class="myDIV col-3 text-center">
                                            <%=price%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                        <div class="row mb-4 pb-4 pt-3" style="background-color: white">
                            <div class="col-8">
                            </div>
                            <div class="col-2 pl-auto pr-0" style="font-weight: 500; color: #664229; font-size: 18px">
                                Tổng Thanh Toán:
                            </div>
                            <div class="col-2 myDIV pl-0 pr-auto" style="font-size: 18px">
                                <%=totalPrice%>
                            </div>
                        </div>
                    </div>
                    <div class="col-1">
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var select = document.getElementById('mySelect');
                var form = document.getElementById('myForm');
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
