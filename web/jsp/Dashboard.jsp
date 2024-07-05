<%-- 
    Document   : Dashboard
    Created on : Jul 3, 2024, 10:16:10 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.EntityFeedback, entity.Book, entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thống Kê</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="CSS/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />

        <div class="container-fluid">
            <div class="row" style="background-color: #F5F5F5; height: 100vh">
                <div class="col-1">
                </div>
                <div class="col-10 mt-5">
                    <div class="container-fluid">
                        <div class="row" style="background-color: white">
                            <div class="col-4">

                            </div>
                            <div class="col-8 pt-3 pb-3">
                                <canvas id="PointChart" style="width:100%;max-width:700px"></canvas>
                            </div>
                        </div>
                        <div class="row mt-5 gx-5">
                            <div class="col mr-3" style="background-color: white">
                                m
                            </div>
                            <div class="col ml-3 pt-3 pb-4" style="background-color: white">
                                <div class="container-fluid">
                                    <div class="row">
                                        <div class="col-1">
                                        </div>
                                        <div class="col-3">
                                            Chọn sách:
                                        </div>
                                        <div class="col-7 pl-0">
                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <canvas id="myChart" style="width:100%;max-width:600px"></canvas>
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
        <%
            Vector <EntityFeedback> vector = (Vector<EntityFeedback>) request.getAttribute("dataFB");
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
        %>
        <script>
            const xValues = ["1 sao", "2 sao", "3 sao", "4 sao", "5 sao"];
            const yValues = [<%=a%>, <%=b%>, <%=c%>, <%=d%>, <%=e%>];
            const barColors = [
                "#b91d47",
                "#00aba9",
                "#2b5797",
                "#e8c3b9",
                "#1e7145"
            ];
            new Chart("myChart", {
                type: "pie",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
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
