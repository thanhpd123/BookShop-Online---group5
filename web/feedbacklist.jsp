<%-- 
    Document   : feedbacklist
    Created on : May 22, 2024, 8:46:03 AM
    Author     : ADMIN
--%>
<%@ page import="entity.Feedback" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Feedback List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
        }
        h1 {
            text-align: center;
            color: indianred;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th {
            background-color: #E9967A;
            color: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            border: 1px solid #ddd;
            color: black;
            background-color: #f1f1f1;
        }
        .pagination a.active {
            background-color: #E9967A;
            color: white;
            border: 1px solid #E9967A;
        }
        .filter-form {
            margin-bottom: 20px;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .filter-form input, .filter-form select, .filter-form button {
            padding: 8px;
            margin: 0 5px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        .filter-form button {
            background-color: #007BFF;
            color: white;
            border: none;
        }
        .filter-form button:hover {
            background-color: #d77b6a;
        }
        .stars {
            display: inline-block;
            color: gold;
        }
        .star {
            font-size: 20px;
        }
    </style>
</head>
<body>
    <h1>Feedback List</h1>
    <form class="filter-form" method="post" action="FeedBackServlet">
        Full Name: 
            <input type="text" name="fullname" value="${fullname}">      
        Book Name:        
            <input type="text" name="bookname" value="${bookname}">
        Rate: 
        <select name="rate" value="${rate}">
            <option value="" >Tất cả</option>
            <option value="5 sao" ${rate == '5 sao' ? 'selected' : ''}>5 sao</option>
            <option value="4 sao" ${rate == '4 sao' ? 'selected' : ''}>4 sao</option>
            <option value="3 sao" ${rate == '3 sao' ? 'selected' : ''}>3 sao</option>
            <option value="2 sao" ${rate == '2 sao' ? 'selected' : ''}>2 sao</option>
            <option value="1 sao" ${rate == '1 sao' ? 'selected' : ''}>1 sao</option>
        </select>
        Sort by:
        <select name="sortBy">
            <option value="">Feedback ID</option>
            <option value="UserName" ${sortBy == 'UserName' ? 'selected' : ''} >Full Name</option>
            <option value="BookName" ${sortBy == 'BookName' ? 'selected' : ''}>Book Name</option>
            <option value="Rate" ${sortBy == 'Rate' ? 'selected' : ''}>Rate</option>
        </select>
        <button type="submit">Filter</button>
    </form>
    <table>
        <tr>
            <th>FeedbackID</th>
            <th>BookName</th>
            <th>UserName</th>
            <th>Rate</th>
            <th>FBDate</th>
            <th>FBContent</th>
            <th>FeedBack Detail</th>
        </tr>
        <%
            List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbacks");
            for (Feedback feedback : feedbacks) {
                // Extract the numeric value from the rate string
                String rateString = feedback.getRate();
                int rate = Integer.parseInt(rateString.split(" ")[0]);
        %>
        <tr>
            <td><%= feedback.getFeedbackID() %></td>
            <td><%= feedback.getBookName() %></td>
            <td><%= feedback.getUserName() %></td>
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
            <td><%= feedback.getFbDate() %></td>
            <td><%= feedback.getFbContent() %></td>
            <td>
                <a href="FeedBackDetailServlet?feedbackID=<%=feedback.getFeedbackID()%>">detail</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>