<%-- 
    Document   : ShipList
    Created on : Jul 19, 2024, 1:31:14 AM
    Author     : Dung Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đơn hàng đang giao</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        Hello: ${acc.getFirstName()} ${acc.getLastName()}
        <h1>Đơn hàng đang giao</h1>
        <form action="ShipServlet" method="post">
            <table>
                <tr>

                    <th>Order ID</th>

                    <th>Tên sách</th>
                    <th>ID đơn hàng</th>
                    <th>Ngày đặt hàng</th>
                    <th>Trạng thái</th>
                    <th>Tên người nhận</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Số lượng</th>
                    <th>Đơn giá</th>
                    <th>Tổng tiền thu</th>
                </tr>
                <c:forEach var="i" items="${listShip}">
                    <tr>

                        <td>${i.getOrderID()}</td>

                        <td>${i.getBookName()}</td>
                        <td>${i.getID()}</td>
                        <td>${i.getOrderDate()}</td>
                        <td>
                            <select name="orderState_${i.getOrderID()}">
                                <option value="Đang giao hàng" ${i.getOrderState() == 'Đang giao hàng' ? 'selected' : ''}>Đang giao hàng</option>
                                <option value="Đã giao hàng" ${i.getOrderState() == 'Đã giao hàng' ? 'selected' : ''}>Đã giao hàng</option>
                            </select>
                        </td>
                        <td>${i.getRname()}</td>
                        <td>${i.getPHoneNo()}</td>
                        <td>${i.getAddress()}</td>
                        <td>${i.getQuantity()}</td>
                        <td>${i.getPrice()}</td>
                        <td>${i.getSum()}</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Lưu lại">
        </form>
    </body>
</html>
