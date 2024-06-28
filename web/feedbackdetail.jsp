<%-- 
    Document   : feedbackdetail
    Created on : May 23, 2024, 12:28:46 AM
    Author     : ADMIN
--%>
<%@ page import="entity.FeedbackDetail" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                width: 80%;
                margin: 20px auto;
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            h1 {
                text-align: center;
                color: #333;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
                color: #333;
            }
            td {
                background-color: #fafafa;
            }
            select, input[type="submit"] {
                padding: 10px;
                margin-top: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                background-color: #fff;
            }
            input[type="submit"] {
                background-color: #28a745;
                color: #fff;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #218838;
            }
            .image-container img {
                max-width: 100%;
                height: auto;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .stars {
                display: inline-block;
                color: gold;
            }
            .star {
                font-size: 20px;
            }
            .button {
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                color: #fff;
                background-color: #007bff;
                border: none;
                border-radius: 5px;
            }

            .button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Feedback Details</h1>
            <form action="FeedBackDetailServlet" method="get">
                <table>
                    <%
    List<FeedbackDetail> feedbackDetail = (List<FeedbackDetail>) request.getAttribute("feedbackDetail");
    for (FeedbackDetail o : feedbackDetail) {
        // Extract the numeric value from the rate string
        String rateString = o.getRate();
        int rate = Integer.parseInt(rateString.split(" ")[0]);
                    %>

                    <tr>
                        <th>Feedback ID</th>
                        <td><%= o.getFeedbackID() %></td>
                    </tr>
                    <tr>
                        <th>Book ID</th>
                        <td><%= o.getBookID() %></td>
                    </tr>
                    <tr>
                        <th>Rate</th>
                        <td>                
                            <div class="stars">
                                <%
                                    for (int i = 1; i <= 5; i++) {
                                        if (i <= rate) {
                                %>
                                <span class="star">&#9733;</span>
                                <%
                                        } else {
                                %>
                                <span class="star">&#9734;</span>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>FeedBack Date</th>
                        <td><%= o.getFbDate()%></td>
                    </tr>
                    <tr>
                        <th>FeedBack Content</th>
                        <td><%= o.getFbContent()%></td>
                    </tr>
                    <tr>
                        <th>User Name</th>
                        <td><%= o.getUserName()%></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><%= o.getEmail()%></td>
                    </tr>
                    <tr>
                        <th>Phone</th>
                        <td><%= o.getPhoneNo()%></td>
                    </tr>
                    <tr>
                        <th>Book Name</th>
                        <td><%= o.getBookName()%></td>
                    </tr>
                    <tr>
                        <th>Status</th>
                        <td>
                            <select name="status">
                                <option value="">HoatDong</option>
                                <option value="">KhongHoatDong</option>
                            </select>
                        </td>
                    </tr>

                </table>
                <input type="hidden" name="feedbackID" value="<%= o.getFeedbackID() %>">
                <%
    }
                %>
                <a href="FeedBackServlet" class="button">Update Status</a>
            </form>
        </div>
    </body>
</html>
