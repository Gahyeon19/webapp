<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>seq</th>
            <th>상품명</th>
            <th>가격</th>
        </tr>
        <c:forEach var="p" varStatus="i" items="${products}">
            <tr>
                <td>${i.count}</td>
                <td><a href="/products?action=info&id=${p.id}">${p.name}</a></td>
                <td>${p.price}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>