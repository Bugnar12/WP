<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>X0 game</title>
  <link rel="stylesheet" href="styles/board-style.css">
</head>
<body>
<%
  String boardAttribute = (String) session.getAttribute("Board");
  String board = (boardAttribute != null) ? boardAttribute : "---------"; // default value
  User user = (User) session.getAttribute("user");
  Integer activePlayerAttribute = (Integer) session.getAttribute("activePlayer");
  int activePlayer = (activePlayerAttribute != null) ? activePlayerAttribute : 0; // default value
  out.println("<table class='game-board'>");
  for(int i=0;i<3;i++) {
    out.println("<tr>");
    for (int j = 0; j < 3; j++) {
      out.println("<td>");
      if( board.charAt(i * 3 + j) == '-')
        out.println(' ');
      else
        out.println(board.charAt(i * 3 + j));
      out.println("</td>");
    }
    out.println("</tr>");
  }
  out.println("</table>");
  out.println("<br>");
  if( activePlayer == user.getPlayerNumber())
  {
    out.println("<p class='turn-indicator'>Your turn</p>");
  }
  else
  {
    out.println("<p class='turn-indicator'>Your opponent's turn: refresh</p>");
  }
%>

<form action="Move" method="post" onsubmit="return validate();">
  Enter column : <input id="a" type="text" name="column" class="input-field"> <BR>
  Enter row : <input id="b" type="text" name="row" class="input-field"> <BR>
  <input type="submit" value="Move/Refresh" class="submit-button"/>
</form>

<script type="text/javascript">
  function validate()
  {
    var c = document.getElementById("a").value;
    var d = document.getElementById("b").value;
    var valid = true;
    if(isNaN(c) || isNaN(d)) {
      alert("Enter a number");
      valid = false;
    }
    return valid;
  };
</script>

<form action="Logout" method="get">
  <input type="submit" value="Logout" class="submit-button"/>
</form>
</body>
</html>