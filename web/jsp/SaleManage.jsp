<%-- 
    Document   : SaleManage
    Created on : Jul 3, 2024, 10:38:09 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Dashboard</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <style>
            

            a{
                text-decoration: none;
            }


            .side-bar{
                float: left;
                height: 100vh;
                width: 20vw;
            }
        </style>
    </head>
    <body>
        <div class="side-bar border-right">
            <div class="mt-3 ml-3">
                <a class="ml-0 mr-0" href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo" style="width: auto; height: 50px"></a>
                <p style="display: inline-block; font-size: 20px; color: #664229"> Book E Shop </p>
            </div>
            <div>
                <div class="mt-5 border-top" style="width: 100%;">
                    <a href="Home?service=listAll" style="color: black;"><div class="pt-3 pb-3" style="padding-left: 12%"><i class="bi bi-house"></i> &nbsp;Trang Chủ</div></a>
                </div>
                <div class="border-top" style="width: 100%;">
                    <a href="SaleController?service=dashboard" style="color: black;"><div class="pt-3 pb-3" style="padding-left: 12%"><i class="bi bi-clipboard-data"></i> &nbsp;Thống Kê</div></a>
                </div>
                <div class="dropdown dropright border-top" style="width:100%; padding-top: 3%; padding-bottom: 3%">
                    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown" style="width:100%; padding-right: 48%">
                        <i class="bi bi-list"></i> &nbsp;Order
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="BookController?service=listAll">Danh Sách Sản Phẩm</a>
                        <a class="dropdown-item" href="AdminController?service=listUser">Danh Sách Khách Hàng</a>
                        <a class="dropdown-item" href="#">Danh Sách Nhân Viên</a>
                    </div>
                </div>
                <div class="border-top border-bottom pt-3 pb-3">
                    <a href="BookController?service=bookByCat&cat=CAT2" style="width: 100%; color: black"><div style="padding-left: 12%"><i class="bi bi-card-text"></i> &nbsp;Quản Lý Blog</div></a>
                </div>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
