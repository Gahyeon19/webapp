<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>학생 정보 수정</h2>
    <hr>
    <form action="/students?action=update&id=${student.id}" method="post">
        name : <input type="text" name="name" value=${student.name} readonly> <br>
        univ : <input type="text" name="univ" value=${student.univ}> <br>
        birth : <input type="text" name="birth" value=${student.birth} readonly> <br>
        email : <input type="email" name="email" value=${student.email}> <br>
        <input type="submit" value="수정"/>
    </form>
</body>
</html>