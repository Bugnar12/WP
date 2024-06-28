<%@ page import="model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Praslea
  Date: 5/28/2024
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Success</title>
    <link rel="stylesheet" type="text/css" href="./styles/success-style.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user.getPlayerNumber()==0) {
        out.println("<p>Welcome " + user.getUsername()+"</p>");
    }
    else if(user.getPlayerNumber()==1) {
        out.println("<p>Start Game</p>");
    }
    else {
        out.println("<p>Sorry " + user.getUsername() + " there are already " + user.getPlayerNumber() + " players playing this game</p>");
    }
%>
<form action="StartGame" method="get">
    <input type="submit" value="Try to Start Game" class="submit-button"/>
</form>
<form action="Logout" method="get">
    <input type="submit" value="Logout" class="submit-button"/>
</form>
</body>
</html>