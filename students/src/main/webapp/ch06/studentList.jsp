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
    <h2>학생 목록</h2>
    <hr>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>univ</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td><a href="/students?action=info&id=${student.id}">${student.name}</a></td>
                <td>${student.univ}</td>
                <td><a href="/students?action=update&id=${student.id}">수정</a></td>
                <td><a href="/students?action=delete&id=${student.id}">삭제</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>