<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>뉴스 상세 보기🐯</h1>
    <hr>
    <ul>
        <li>ID : ${news.id}</li>
        <li>TITLE : ${news.title}</li>
        <li>IMAGE : ${news.img}</li>
        <li>DATE : ${news.date}</li>
        <li>CONTENT : ${news.content}</li>
    </ul>
</body>
</html>