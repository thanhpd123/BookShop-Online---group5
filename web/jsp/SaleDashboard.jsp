<%-- 
    Document   : SaleDashboard
    Created on : Jul 3, 2024, 10:38:02 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Orders, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thống Kê</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
                min-width: 220px;
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <body>
        <c:import url="/jsp/SaleManage.jsp" />
        <%
            Vector<Orders> vectorOr = (Vector<Orders>)request.getAttribute("dataOrder");
            Vector<Orders> vector = (Vector<Orders>)request.getAttribute("dataOr");
            String day = (String)request.getAttribute("day");
            String select = (String)request.getAttribute("select");
            int a = 0, b = 0, c = 0;
            for (Orders or : vectorOr) {
                if (or.getOrderState().equals("Đã giao hàng")) {
                    a++;
                }
                if (or.getOrderState().equals("Đang giao hàng")) {
                    b++;
                }
                if (or.getOrderState().equals("Đang xử lý")) {
                    c++;
                }
            }
        %>
        <div class="container-fluid">
            <div class="row" style="background-color: #F5F5F5; height: 100vh">
                <div class="col-1">
                </div>
                <div class="col-10 mt-5">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col mr-3" style="background-color: white">
                                <%=vector.get(0).getOrderDate()%>
                            </div>
                            <div class="col pt-4 pb-4 ml-3" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pl-5">
                                        Tổng Số Đơn Hàng: <%=a + b + c%>
                                    </div>
                                    <div class="row pt-3">
                                        <canvas id="myChart" style="width:100%;max-width:500px"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4" style="background-color: white">
                            <div class="col-5">
                                <div class="container-fluid">
                                    <div class="row pt-5 pl-5">
                                        <div class="col-6">
                                            Lợi Nhuận Cửa Hàng:
                                        </div>
                                        <div class="col-6">
                                            <div class="dropdown text-center" style="display: inline-block">
                                                <%=select%>
                                                <div class="dropdown-content">
                                                    <a href="SaleController?service=dashboard&day=7">7 Ngày Gần Nhất</a>
                                                    <a href="SaleController?service=dashboard&day=30">30 Ngày Gần Nhất</a>
                                                    <a href="SaleController?service=dashboard&day=365">365 Ngày Gần Nhất</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-7 pt-3 pb-5">
                                <div class="container-fluid">
                                    <div class="row pt-3 pl-5 pb-3">
                                        Lợi Nhuận <%=day%> Ngày Gần Nhất:
                                    </div>
                                    <div class="row">
                                        <canvas id="LineChart" style="width:100%;max-width:600px"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>
        <script>
            const x = [<%
                            for (Orders or : vector) {
            %>
            "<%=or.getOrderDate()%>",
            <%
                 }
            %>
            ];
            const y = [<%
                            for (Orders or1 : vector) {
            %>
            <%=or1.getPrice() * or1.getQuantity()%>,
            <%
                }
            %>
            ];
            new Chart("LineChart", {
                type: "line",
                data: {
                    labels: x,
                    datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: y
                        }]
                },
                options: {
                    legend: {display: false},
                    scales: {
                        y: [{ticks: {min: 0, max: 10000000}}]

                    }
                }
            });
        </script>

        <script>
            var xValues = ["Đang xử lý", "Đang giao hàng", "Đã giao hàng"];
            var yValues = [<%=c%>, <%=b%>, <%=a%>];
            var barColors = ["red", "yellow", "orange"];

            new Chart("myChart", {
                type: "bar",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "Tỷ Lệ Đơn Đặt Hàng"
                    }
                }
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
