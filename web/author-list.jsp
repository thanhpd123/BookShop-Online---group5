<%-- 
    Document   : author-list
    Created on : Jul 26, 2024, 3:37:36 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <a href="BookManagementServlet">Return Book Manage</a>
        <title style="align-content: center">Author List</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            table, th, td {
                border: 1px solid black;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <h2>Author List</h2>
        <a href="author?action=new" style="align-content: center">Add New Author</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authorList}">
                    <tr>
                        <td>${author.getAuthorID()}</td>
                        <td>${author.getAuthorName()}</td>
                        <td>
                            <a href="author?action=edit&id=${author.getAuthorID()}">Edit</a>
                            <a href="author?action=delete&id=${author.getAuthorID()}" onclick="return confirm('Are you sure you want to delete this author?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
