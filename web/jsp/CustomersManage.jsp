<%-- 
    Document   : CustomersManage
    Created on : May 28, 2024, 11:29:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="entity.Customers, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Customers List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
            }
            h1 {
                text-align: center;
                color: #333;
            }
            .filter-form {
                margin-bottom: 20px;
                background: #fff;
                padding: 15px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .filter-form label {
                margin-right: 10px;
            }
            .filter-form input[type="text"] {
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-right: 10px;
            }
            .filter-form button {
                padding: 5px 10px;
                background-color: #007BFF;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .filter-form button:hover {
                background-color: #0056b3;
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
                background-color:#D19C97;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            tr:hover {
                background-color: #ddd;
            }
            .actions a {
                margin-right: 10px;
                color: #007BFF;
                text-decoration: none;
            }
            .actions a:hover {
                text-decoration: underline;
            }
            .add-new {
                display: inline-block;
                padding: 10px 20px;
                background-color: #28a745;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .add-new:hover {
                background-color: #218838;
            }
        </style>
    </head>
    <body>
        <h1>Customers List</h1>
        <!-- Filter and Search Form -->

        <!-- Table for displaying customers -->
        <table border="1">
            <%
                  Vector<Customers> vector = (Vector<Customers>) request.getAttribute("data");
            %>
           
        <form action="CustomersController?service=search" method="POST">
                               <p><input type="text" placeholder="Search First Name" name="FirstName">
                               <input type="submit" value="Search First Name" name="submit">
                               </p>                           
        </form>
            <tr>
                <th>UserID</a></th>
                <th>RoleID</a></th>
                <th>Full Name</a></th>
                <th>Email</a></th>
                <th>PhoneNo</a></th>
                <th>Address</th>
                <th>Gender</th>
                <th>DOB</th>
            </tr>
            <% 
                for(Customers cus : vector) {   
            %>
            <tr>
                <td><%=cus.getUserID()%></td>
                <td><%=cus.getRoleID()%></td>
                <td><%=cus.getFirstName() + " " + cus.getLastName()%></td>
                <td><%=cus.getEmail()%></td>
                <td><%=cus.getPhoneNo()%></td>
                <td><%=cus.getAddress()%></td>
                <td><%=cus.isGender() ? "Male" : "Female" %></td>
                <td><%=cus.getDOB()%></td>
                
            </tr>
            <%
                }
            %>
        </table>


       
    </body>
</html>
