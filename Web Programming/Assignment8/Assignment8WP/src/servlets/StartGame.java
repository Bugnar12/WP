package servlets;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/StartGame")
public class StartGame extends HttpServlet {
    @Override
    public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user.getPlayerNumber() < 2) {
            String stmt = "select * from playerslogged";
            try {
                PreparedStatement preparedStmt = DB.DBManager.getConnection().prepareStatement(stmt);
                ResultSet rs = preparedStmt.executeQuery();
                int playersLoggedIn = -1;
                if (rs.next()) ///while we still have logged in players
                {
                    playersLoggedIn = rs.getInt("NoOfPlayers");
                }
                request.getSession().setAttribute("NoOfPlayers", playersLoggedIn);
                if (playersLoggedIn >= 2) {
                    CreateBoard(request.getSession());
                    response.sendRedirect("board.jsp");
                } else {
                    response.sendRedirect("success.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("success.jsp");
            }
        }
        else{
            response.sendRedirect("success.jsp");
        }
    }

    public void CreateBoard(HttpSession session) {
        String stmt = "select * from boardstate";
        try{
            PreparedStatement preparedStmt = DB.DBManager.getConnection().prepareStatement(stmt);
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next()) {
                String board = rs.getString("BoardState");
                int activePlayer = rs.getInt("ActivePlayer");
                session.setAttribute("board", board);
                session.setAttribute("activePlayer", activePlayer);
            } else {
                // If there's no boardstate in the database, create a new one with activePlayer set to 0
                session.setAttribute("board", "---------"); // Empty board
                session.setAttribute("activePlayer", 0); // First player
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

