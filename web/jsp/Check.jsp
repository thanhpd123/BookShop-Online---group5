<%-- 
    Document   : Check
    Created on : Jul 4, 2024, 10:15:41 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Orders, entity.Cart, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="refresh" content="7;url=Home?service=listAll"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <style>
            .button1{
                border-color: #664229;
                color: #664229;
                background-color: white;
            }

            .button1:hover {
                border-color: #D2B48C;
                background-color: #D2B48C;
                color: white;
            }

            .button2{
                border-color: #664229;
                color: #664229;
                background-color: white;
            }

            .button2:hover {
                border-color: #D2B48C;
                background-color: #D2B48C;
                color: white;
            }

        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row text-center" style="margin-top: 20%">
                <div class="" style="width: 100%;">
                    <h2>Đang Chờ Xác Nhận Của Nhân Viên Bán Hàng</h2>
                </div>
            </div>
            <div class="row text-center mt-2">
                <div style="width: 100%; font-size: 20px">Tự động chuyển tới trang chủ sau <p id="countdown"></p></div>
            </div>
            <div class="row mt-5">
                <div class="col-3">
                </div>
                <div class="col-3 text-center">
                    <a href="Home?service=listAll"><button class="button1 py-2 px-3" style="">Trang Chủ</button></a>
                </div>
                <div class="col-3 text-center">
                    <a href="OrderController"><button class="button2 py-2 px-3" style="">Đơn Hàng</button></a>
                </div>
                <div class="col-3">
                </div>
            </div>
        </div>
        <script>
            var timeleft = 6;
            var downloadTimer = setInterval(function () {
                if (timeleft <= 0) {
                    clearInterval(downloadTimer);
                    document.getElementById("countdown").innerHTML = "0 giây";
                } else {
                    document.getElementById("countdown").innerHTML = timeleft + " giây";
                }
                timeleft -= 1;
            }, 1000);
        </script>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>
