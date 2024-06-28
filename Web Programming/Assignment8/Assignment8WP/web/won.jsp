<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Bugnar12
  Date: 5/28/2024
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Won the game</title>
    <link rel="stylesheet" type="text/css" href="index-style.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    out.println("<h1> Congratulations " + user.getUsername() + " you won the game! </h1>");
    out.println("");
%>
<form action="Logout" method="get">
    <input type="submit" value="Logout" class="submit-button"/>
</form>
</body>
</html>