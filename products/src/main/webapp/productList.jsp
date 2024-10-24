<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>📜Product List Page</h2>
    <hr>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>maker</th>
            <th>date</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td><a href="/products?action=info&id=${product.id}">${product.name}</a></td>
                <td>${product.price}</td>
                <td>${product.maker}</td>
                <td>${product.date}</td>
                <td><a href="/products?action=update&id=${product.id}">수정</a></td>
                <td><a href="/products?action=delete&id=${product.id}">삭제</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="/products?action=insert">상품 추가</a></p>
</body>
</html>