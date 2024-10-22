<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h2>상세 정보 조회</h2>
    <hr>
    <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>univ</th>
                <th>birth</th>
                <th>email</th>
            </tr>
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.univ}</td>
                <td>${student.birth}</td>
                <td>${student.email}</td>
            </tr>
        </table>
</body>
</html>