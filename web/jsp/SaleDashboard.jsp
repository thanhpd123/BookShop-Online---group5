<%-- 
    Document   : SaleDashboard
    Created on : Jul 3, 2024, 10:38:02 PM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Orders, java.util.Vector, entity.Account, entity.OrderDetail"%>
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
            Account acc1 = (Account)session.getAttribute("acc");
            Vector<Orders> vectorOr = (Vector<Orders>)request.getAttribute("dataOrder");
            Vector<Orders> vectorNew = (Vector<Orders>)request.getAttribute("vectorNew");
            Vector<OrderDetail> vectorOrder = (Vector<OrderDetail>)request.getAttribute("vectorOrder");
            Vector<Orders> vector = (Vector<Orders>)request.getAttribute("dataOr");
            Vector<Orders> vectorByUser = (Vector<Orders>)request.getAttribute("dataOrByUser");
            Vector<Account> vectorAcc = (Vector<Account>)request.getAttribute("data");
            String day = (String)request.getAttribute("day");
            String startDate = (String)request.getAttribute("startDate");
            String endDate = (String)request.getAttribute("endDate");
            String select = (String)request.getAttribute("select");
            int a = 0, b = 0, c = 0, d = 0;
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
                if (or.getOrderState().equals("Đã hủy")) {
                    d++;
                }
            }
        %>
        <div class="container-fluid">
            <div class="row pt-2 pb-2">
                <div class="col-10">
                </div>
                <div class="col-2">
                    <div class="dropdown">
                        Tài Khoản
                        <div class="dropdown-content">
                            <a href="userprofile"><i class="bi bi-person h5"></i> <%=acc1.getLastName()%></a>
                            <a href="LogOut">Đăng Xuất</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="background-color: #F5F5F5; height: 110vh;">
                <div class="col-1">
                </div>
                <div class="col-10 mt-3">
                    <div class="container-fluid">
                        <div class="row mb-4" style="background-color: #F5F5F5;">
                            <div class="col mr-3" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3">
                                            <div class="mt-4 mb-4 text-center" style="width: 60px; height: 60px; background-color: #A52A2A; padding-top: 5%">
                                                <i class="bi bi-cart-plus h1" style="width: 100%; color: white;"></i>
                                            </div>
                                        </div>
                                        <div class="col-9 pt-4">
                                            <div class="container-fluid">
                                                <%
                                                    int i = 0;
                                                    for (Orders order : vectorNew) {
                                                        i++;
                                                    }
                                                %>
                                                <div class="row">
                                                    <h4 class="pl-0"><%=i%></h4>
                                                </div>
                                                <div class="row">
                                                    Đơn Hàng Mới trong 7 Ngày
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col mr-3" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-3">
                                            <div class="mt-4 mb-4 text-center" style="width: 60px; height: 60px; background-color: #D2691E; padding-top: 5%">
                                                <i class="bi bi-collection h1" style="width: 100%; color: white;"></i>
                                            </div>
                                        </div>
                                        <div class="col-9 pt-4">
                                            <div class="container-fluid">
                                                <div class="row">
                                                    <h4 class="pl-0"><%=a + b + c + d%></h4>
                                                </div>
                                                <div class="row">
                                                    Tổng Số Đơn Hàng
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-4">
                                            <div class="mt-4 mb-4 text-center" style="width: 60px; height: 60px; background-color: #F4A460; padding-top: 5%">
                                                <i class="bi bi-currency-dollar h1" style="width: 100%; color: white;"></i>
                                            </div>
                                        </div>
                                        <div class="col-8 pt-4">
                                            <div class="container-fluid">
                                                <%
                                                    int price = 0;
                                                    for (OrderDetail orderinfo : vectorOrder) {
                                                        price += orderinfo.getPrice();
                                                    }
                                                %>
                                                <div class="row">
                                                    <h4 class="myDIV pl-0"><%=price%></h4>
                                                </div>
                                                <div class="row">
                                                    Tổng Doanh Thu
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col pt-4 pb-4 mr-3" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pl-5">
                                    </div>
                                    <div class="row pt-3">
                                        <canvas id="myChart" style="width:100%;max-width:500px"></canvas>
                                    </div>
                                </div>
                            </div>
                            <div class="col ml-2 pt-4" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row pb-4">
                                        <div class="col-5" style="padding-left: 8%">
                                            Lợi Nhuận Cửa Hàng:
                                        </div>
                                        <div class="col-4">
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
                                    <div class="row pb-2 text-center">
                                        <div style="width: 100%">Lợi Nhuận <%=day%> Ngày Gần Nhất:</div>
                                    </div>
                                    <div class="row pb-4">
                                        <canvas id="Chart" style="width:100%;max-width:600px"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4" style="background-color: white; padding-top: 50px">
                            <div class="col-5">
                                <div class="container-fluid">
                                    <form action="SaleController?service=dashboard" method="post" onsubmit="return validateForm()">
                                        <div class="row pt-4">
                                            <div class="col-4" style="padding-left: 40px">
                                                Người Dùng:
                                            </div>
                                            <div class="col-7">
                                                <select name="userID" id="userID" style="background-color: white; padding: 6px;">
                                                    <option></option>
                                                    <%
                                                        for (Account acc : vectorAcc) {
                                                    %>
                                                    <option value="<%=acc.getUserID()%>"><%=acc.getFirstName()%> <%=acc.getLastName()%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                            <div class="col-1">
                                            </div>
                                        </div>
                                        <div class="row pt-3">
                                            <div class="col-4" style="padding-left: 70px">
                                                Bắt Đầu:
                                            </div>
                                            <div class="col-7">
                                                <input class="text-center" type="date" id="start" name="start" style="width: 170px; padding: 2px">
                                            </div>
                                            <div class="col-1">
                                            </div>
                                        </div>
                                        <div class="row pt-3">
                                            <div class="col-4" style="padding-left: 62px">
                                                Kết Thúc:
                                            </div>
                                            <div class="col-7">
                                                <input class="text-center" type="date" id="end" name="end" style="width: 170px; padding: 2px">
                                            </div>
                                            <div class="col-1">
                                            </div>
                                        </div>
                                        <div class="row pt-3">
                                            <div class="col-4">
                                            </div>
                                            <div class="col-7">
                                                <input type="submit" class="p-2" style="background-color: white; width: 100px; border-color: #664229" value="Tra Cứu" name="submit">

                                                <div class="col-1">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-7 pt-3 pb-5">
                                <div class="container-fluid">
                                    <div class="row pt-3 pl-5 pb-3">
                                        <%
                                            if (startDate != null && endDate != null) {
                                        %>
                                        Lợi Nhuận từ ngày <%=startDate%> đến <%=endDate%>:
                                        <%
                                            }
                                        %>
                                    </div>
                                    <div class="row">
                                        <canvas id="ChartUser" style="width:100%;max-width:600px"></canvas>
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
            const Ox = [<%
                            for (Orders or2 : vectorByUser) {
            %>
            "<%=or2.getOrderDate()%>",
            <%
                 }
            %>
            ];
            const Oy = [<%
                            for (Orders or3 : vectorByUser) {
            %>
            <%=or3.getPrice() * or3.getQuantity()%>,
            <%
                }
            %>
            ];
            new Chart("ChartUser", {
                type: "line",
                data: {
                    labels: Ox,
                    datasets: [{
                            fill: false,
                            lineTension: 0,
                            backgroundColor: "rgba(0,0,255,1.0)",
                            borderColor: "rgba(0,0,255,0.1)",
                            data: Oy
                        }]
                },
                options: {
                    legend: {display: false},
                    scales: {
                        Oy: [{ticks: {min: 0, max: 1000000000}}]

                    }
                }
            });
        </script>
        <script>
            let x1 = document.querySelectorAll(".myDIV");
            for (let i = 0, len = x1.length; i < len; i++) {
                let num = Number(x1[i].innerHTML)
                        .toLocaleString('en');
                x1[i].innerHTML = num;
            }
        </script>
        <script type="text/javascript">
            function validateForm() {
                var start = document.getElementById("start").value;
                var userID = document.getElementById("userID").value;
                var end = document.getElementById("end").value;
                if (start.trim() === "" && userID !== "" && end.trim() !== "") {
                    alert("Vui lòng nhập ngày Bắt Đầu!");
                    return false; // Prevent the form from being submitted
                }
                if (end.trim() === "" && start.trim() !== "" && userID !== "") {
                    alert("Vui lòng nhập ngày Kết Thúc!");
                    return false; // Prevent the form from being submitted
                }
                if (start.trim() === "" && end.trim() === "" && userID !== "") {
                    alert("Vui lòng nhập ngày Bắt Đầu và ngày Kết Thúc!");
                    return false; // Prevent the form from being submitted
                }
                if (start.trim() === "" && end.trim() === "" && userID.trim() === "") {
                    alert("Vui lòng nhập đầy đủ thông tin!");
                    return false; // Prevent the form from being submitted
                }
                if (userID.trim() === "") {
                    alert("Vui lòng chọn Người Dùng!");
                    return false; // Prevent the form from being submitted
                }
                return true; // Allow the form to be submitted
            }
        </script>
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
            new Chart("Chart", {
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
                        y: [{ticks: {min: 0, max: 1000000000}}]

                    }
                }
            });
        </script>
        <script>
            var xValues = ["Đang xử lý", "Đang giao hàng", "Đã giao hàng", "Đã hủy"];
            var yValues = [<%=c%>, <%=b%>, <%=a%>, <%=d%>];
            var barColors = ["#FF5C77", "#4DD091", "#FFEC59", "#FFA23A"];

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
                        text: "Số Lượng Đơn Đặt Hàng"
                    }
                }
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
