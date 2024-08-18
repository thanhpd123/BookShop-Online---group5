<%-- 
    Document   : author-form
    Created on : Jul 26, 2024, 3:37:57 PM
    Author     : ADMIN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author Form</title>
    </head>
    <body>
        <h2>${author != null ? "Edit Author" : "New Author"}</h2>
        <form action="author" method="post">
            <input type="hidden" name="action" value="${author != null ? 'update' : 'create'}">
            <c:if test="${author != null}">
                <input type="hidden" name="id" value="${author.getAuthorID()}">
            </c:if>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${author != null ? author.getAuthorName() : ''}" required>
            <br><br>
            <input type="submit" value="${author != null ? 'Update' : 'Create'}">
        </form>
        <br>
        <a href="author?action=list">Back to Author List</a>
    </body>
</html>
