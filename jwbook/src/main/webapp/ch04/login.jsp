<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <form action="/login" method="post">
        id : <input type="text" name="id" size="10"> <br>
        pw : <input type="password" name="pw" size="10"> <br>
        <input type="submit" value="로그인"/>
        <input type="reset" value="재입력"/>
    </form>
</body>
</html>