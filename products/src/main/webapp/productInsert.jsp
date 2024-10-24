<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>✏️Product Insert Page</h2>
    <hr>
    <form action="/products?action=insert" method="post">
        name : <input type="text" name="name" > <br>
        price : <input type="text" name="price" > <br>
        maker : <input type="text" name="maker" > <br>
        date : <input type="date" name="date" > <br>
        <input type="submit" value="등록"/>
    </form>
    <br>
    <a href="/products?action=list">목록으로</a>

</body>
</html>