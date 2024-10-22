<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forward Page</title>
</head>
<body>
    <h1>Forward Page</h1>
    <%= request.getAttribute("data") %>
    ${data}

</body>
</html>