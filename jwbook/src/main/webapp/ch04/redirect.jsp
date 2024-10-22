<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Redirect Page</title>
</head>
<body>
    <h1>Redirect Page</h1>
    <%= request.getAttribute("data") %>
    ${data}
</body>
</html>