<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>📜Product Info Page</h2>
    <hr>
    <table border="1">
         <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
            <th>maker</th>
            <th>date</th>
        </tr>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.maker}</td>
            <td>${product.date}</td>
        </tr>
    </table>
    <br>
    <a href="/products?action=list">목록으로</a>
</body>
</html>