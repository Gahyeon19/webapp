<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>학생 정보 입력</h2>
    <hr>
    <form action="/students?action=insert" method="post">
        name : <input type="text" name="name" size="10"> <br>
        univ : <input type="text" name="univ" size="10"> <br>
        birth : <input type="text" name="birth" size="10"> <br>
        email : <input type="email" name="email" size="10"> <br>
        <input type="submit" value="등록"/>
    </form>
</body>
</html>