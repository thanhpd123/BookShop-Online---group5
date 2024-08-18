<%-- 
    Document   : AdminManage
    Created on : Jun 28, 2024, 11:50:53 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
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
                min-height: 135vh;
                width: 20vw;
            }
        </style>
    </head>
    <body>
        <%
            Account acc = (Account)session.getAttribute("acc");
        %>
        <div class="side-bar border-right" style="background-color: #E5D3B3">
            <div class="mt-3 ml-3">
                <a class="ml-0 mr-0" href="Home?service=listAll"><img class="logo" src = "${pageContext.request.contextPath}/assets/logo.PNG" alt="Logo" style="width: auto; height: 50px"></a>
                <p style="display: inline-block; font-size: 20px; color: #664229"> Book E Shop </p>
            </div>
            <div>
                <div class="container-fluid mt-4" style="width: 100%">
                    <div class="row">
                        <div class="col-3 pb-3 pl-4">
                            <img style="border-radius: 50%; width: 50px; height: 50px" src="<%=acc.getImgUser()%>">
                        </div>
                        <div class="col-9 pt-3" style="color: #664229">
                            <%=acc.getLastName()%> <%=acc.getFirstName()%>
                        </div>
                    </div>
                </div>
                <div class="border-top" style="width: 100%;">
                    <a href="Home?service=listAll" style="color: black;"><div class="pt-3 pb-3" style="padding-left: 12%"><i class="bi bi-house"></i> &nbsp;Trang Chủ</div></a>
                </div>
                <div class="border-top" style="width: 100%;">
                    <a href="AdminController?service=dashboard" style="color: black;"><div class="pt-3 pb-3" style="padding-left: 12%"><i class="bi bi-clipboard-data"></i> &nbsp;Thống Kê</div></a>
                </div>
                <div class="border-top" style="width: 100%;">
                    <a href="AdminController?service=listUser" style="color: black;"><div class="pt-3 pb-3" style="padding-left: 12%"><i class="bi bi-list"></i> &nbsp;Danh Sách Khách Hàng</div></a>
                </div>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
