<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>📝Product Update Page</h2>
    <hr>
    <form action="/products?action=update&id=${product.id}" method="post">
        name : <input type="text" name="name" value="${product.name}" readonly> <br>
        price : <input type="text" name="price" value=${product.price} > <br>
        maker : <input type="text" name="maker" value=${product.maker} readonly> <br>
        date : <input type="text" name="date" value=${product.date} > <br>
        <input type="submit" value="수정"/>
    </form>
    <br>
    <a href="/products?action=list">목록으로</a>

</body>
</html>