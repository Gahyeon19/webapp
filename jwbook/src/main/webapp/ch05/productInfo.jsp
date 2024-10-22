<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>상품 정보 조회</h2>
    <hr>
    <ul>
        <li>상품코드 : ${product.id}</li>
        <li>상품명 : ${product.name}</li>
        <li>제조사 : ${product.maker}</li>
        <li>가격 : ${product.price}</li>
        <li>제조일 : ${product.date}</li>
    </ul>

</body>
</html>