<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login OK</title>
</head>
<body>
    <%! String id = ""; %>
    <%
    id = session.getAttribute("id").toString();
    if (session.getAttribute("id").equals("")){
        response.sendRedirect("/login.jsp");
    } else {
        out.println("<h1>Login OK</h1><hr>");
        out.println("<h2>" + id + "님 안녕하세요</h2>");
    }
    %>
</body>
</html>