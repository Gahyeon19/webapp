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
    <h1>뉴스 목록👽</h1>
    <hr>
    <ul>
        <c:forEach var="n" varStatus="i" items="${news}">
            <li>
                [${i.count}]
                <a href="/news?action=info&id=${n.id}">${n.title}</a>
                ${n.date}
            </li>
        </c:forEach>
    </ul>
    <hr>
    <form action="/news?action=create" method="post">
        TITLE : <input type="text" name="title"> <br>
        IMAGE : <input type="text" name="img"> <br>
        CONTENT : <textarea name="content" cols="20" rows="10"></textarea> <br>
        <input type="submit" value="등록">
    </form>
</body>
</html>