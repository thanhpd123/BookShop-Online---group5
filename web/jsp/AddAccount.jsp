<%-- 
    Document   : AddAccount
    Created on : Jun 28, 2024, 11:51:02 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.Account, java.util.Vector"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="/jsp/AdminManage.jsp" />
        <%
            Vector<Account> vector = (Vector<Account>) request.getAttribute("data");
        %>
        <form action="AdminController" method="post">
            <table>
                <tr>
                    <td>Họ:</td>
                    <td><input type="text"  name="FirstName"></td>
                </tr>
                <tr>
                    <td>Tên:</td>
                    <td><input type="text"  name="LastName"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text"  name="Email"></td>
                </tr>
                <tr>
                    <td>Mật Khẩu:</td>
                    <td><input type="text"  name="Password"></td>
                </tr>
                <tr>
                    <td>Loai</td>
                    <td>
                        <select name="RoleID">
                            <%
                                for(Account acc : vector) {
                            %>
                            <option><%= acc.getRoleID()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>SĐT:</td>
                    <td><input type="text"  name="PhoneNo"></td>
                </tr>
                <tr>
                    <td>Địa Chỉ:</td>
                    <td><input type="text"  name="Address"></td>
                </tr>
                <tr>
                    <td>Giới Tính</td>
                    <td>
                        <input type="radio" name="Gender" value="1" id="">Nam
                        <input type="radio" name="Gender" value="2"  id="">Nữ
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" style="background-color: white" value="Thêm Tài Khoản" name="submit"></td>
                    <td><input type="reset" style="background-color: white" value="Xóa"></td>
                </tr>


            </table>
            <input type="hidden" name="service" value="add">
        </form>
    </body>
</html>
