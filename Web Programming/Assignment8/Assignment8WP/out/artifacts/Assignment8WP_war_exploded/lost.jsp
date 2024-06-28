<%@ page import ="model.User" %>

<%--
  Created by IntelliJ IDEA.
  User: Praslea
  Date: 5/28/2024
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lost</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    out.println("<p style='font-size: 25px; font-weight: bold; color: red'>User " + user.getUsername() + " lost the tic-tac-toe!</p>");
%>
</body>
    <input type="submit" value="Logout">
</html>
