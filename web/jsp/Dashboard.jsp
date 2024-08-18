<%-- 
    Document   : Dashboard
    Created on : Jul 3, 2024, 10:16:10 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.EntityFeedback, entity.Orders, entity.Book, entity.Account, java.util.Vector"%>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Account acc1 = (Account)session.getAttribute("acc");
            Vector <EntityFeedback> vector = (Vector<EntityFeedback>) request.getAttribute("dataFB");
            Vector <Orders> vectorOr = (Vector<Orders>) request.getAttribute("dataOr");
            int a = 0, b = 0, c = 0, d = 0, e = 0;
            for (EntityFeedback fb : vector) {
                if (fb.getRate() == 1) {
                    a++;
                }
                if (fb.getRate() == 2) {
                    b++;
                }
                if (fb.getRate() == 3) {
                    c++;
                }
                if (fb.getRate() == 4) {
                    d++;
                }   
                if (fb.getRate() == 5) {
                    e++;
                }
            } 
            int x = 0, y = 0, z = 0, v = 0;
            for (Orders order1 : vectorOr) {
                if (order1.getOrderState().equals("Đang xử lý")) {
                    x++;
                }
                if (order1.getOrderState().equals("Đang giao hàng")) {
                    y++;
                }
                if (order1.getOrderState().equals("Đã giao hàng")) {
                    z++;
                }
                if (order1.getOrderState().equals("Đã hủy")) {
                    v++;
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
            <div class="row" style="background-color: #F5F5F5; height: 130vh">
                <div class="col-1">
                </div>
                <div class="col-10 mt-5">
                    <div class="container-fluid">
                        <div class="row" style="background-color: white">
                            <div class="col-4">
                                <div class="container-fluid">
                                    <div class="row mt-5 ml-5">
                                        <div class="col-2" style="font-size: 22px"><%= x + y + z + v%></div>
                                        <div class="col-10 pt-2">Tổng Đơn Hàng</div>
                                    </div>
                                    <div class="row mt-3 ml-5">
                                        <div class="col-2" style="font-size: 22px"><%=x%></div>
                                        <div class="col-10 pt-2">Đang Xử Lý</div>
                                    </div>
                                    <div class="row mt-3 ml-5">
                                        <div class="col-2" style="font-size: 22px"><%=y%></div>
                                        <div class="col-10 pt-2">Đang Giao Hàng</div>
                                    </div>
                                    <div class="row mt-3 ml-5">
                                        <div class="col-2" style="font-size: 22px"><%=z%></div>
                                        <div class="col-10 pt-2">Đã Giao Hàng</div>
                                    </div>
                                    <div class="row mt-3 ml-5">
                                        <div class="col-2" style="font-size: 22px"><%=v%></div>
                                        <div class="col-10 pt-2">Đã Hủy</div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-8 pt-3 pb-3">
                                <canvas id="OrChart" style="width:100%;max-width:600px"></canvas>
                            </div>
                        </div>
                        <div class="row mt-5 gx-5">
                            <div class="col mr-3" style="background-color: white">
                                <div id="myPlot" style="width:100%;max-width:500px; height: 100%"></div>
                            </div>
                            <div class="col ml-3 pt-3 pb-4" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-5 gx-5">
                            <div class="col mr-3 pl-5" style="background-color: white">
                                <div id="myBook" style="width:100%; height: 100%;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-1">
                </div>
            </div>
        </div>
        <script>
            var xValues = ["Đang xử lý", "Đang giao hàng", "Đã giao hàng", "Đã hủy"];
            var yValues = [<%=x%>, <%=y%>, <%=z%>, <%=v%>];
            var barColors = ["#FF5C77", "#4DD091", "#FFEC59", "#FFA23A"];
            new Chart("OrChart", {
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
                        text: "Số Lượng Đơn Đặt Hàng trong 30 ngày qua"
                    }
                }
            });
        </script>
        <%
            Vector <Orders> vectorMax = (Vector <Orders>)request.getAttribute("dataMax");
            Vector <Orders> vectorMin = (Vector <Orders>)request.getAttribute("dataMin");
            int mincount = (int)request.getAttribute("mincount");
            int maxcount = (int)request.getAttribute("maxcount");
        %>
        <script>
            const xArray = [<%=mincount%>, <%=maxcount%>];
            const yArray = ["<%=vectorMin.get(0).getOrderState()%>", "<%=vectorMax.get(0).getOrderState()%>"];
            const data = [{
                    x: xArray,
                    y: yArray,
                    type: "bar",
                    orientation: "h",
                    marker: {color: "#E97451"}
                }];
            const layout = {title: "Nhân Viên Sale Của Tháng"};
            Plotly.newPlot("myPlot", data, layout);
        </script>
        <%
            Vector<Book> max = (Vector<Book>) request.getAttribute ("Max");
            Vector<Book> min = (Vector<Book>) request.getAttribute ("Min");
        %>
        <script>
            const xArr = [<%=max.get(0).getPurchases()%>, <%=min.get(0).getPurchases()%>];
            const yArr = ["<%=max.get(0).getName()%>", "<%=min.get(0).getName()%>"];
            const datamu = [{
                    x: xArr,
                    y: yArr,
                    type: "bar",
                    orientation: "h",
                    marker: {color: "#D2B48C"},
                    text: yArr, // Add text for hover info
                    textposition: 'auto', // Auto positioning of text
                    hoverinfo: 'y+x+text'
                }];
            const layout1 = {title: "Sách bán chạy nhất", margin: {
                    l: 200, // Adjust left margin to accommodate long labels
                    r: 50,
                    b: 50,
                    t: 50,
                    pad: 10
                },
                yaxis: {
                    automargin: true, // Enable automatic margin adjustment
                    tickfont: {
                        size: 12  // Adjust font size if needed
                    }
                }};
            Plotly.newPlot("myBook", datamu, layout1);
        </script>
        <script>
            const Ox = ["1 sao", "2 sao", "3 sao", "4 sao", "5 sao"];
            const Oy = [<%=a%>, <%=b%>, <%=c%>, <%=d%>, <%=e%>];
            const barColor = [
                "#b91d47",
                "#00aba9",
                "#2b5797",
                "#e8c3b9",
                "#1e7145"
            ];
            new Chart("myChart", {
                type: "pie",
                data: {
                    labels: Ox,
                    datasets: [{
                            backgroundColor: barColor,
                            data: Oy
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: "Feedback"
                    }
                }
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
