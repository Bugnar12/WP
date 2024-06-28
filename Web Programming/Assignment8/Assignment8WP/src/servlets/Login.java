package servlets;

import DB.DBManager;
import controller.UserController;
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

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String result = controller.AuthController.authenticate(username, password);

        if(result.equals("Login successful.")) {
            int playersLogged = checkNoOfPlayersLogged();

            User user = login(request.getSession(), username, playersLogged);
            if(user == null) {
                response.sendRedirect("error.jsp");
            }
            response.sendRedirect("success.jsp");
        }
        else {
            response.sendRedirect("index.jsp");
        }
    }

    public int checkNoOfPlayersLogged(){
        String stmt = "select * from playerslogged";
        try {
            PreparedStatement preparedStmt = DBManager.getConnection().prepareStatement(stmt);
            ResultSet rs = preparedStmt.executeQuery();
            int playersLoggedIn = -1;
            if (rs.next()) ///while we still have logged in players
            {
                playersLoggedIn = rs.getInt("NoOfPlayers");
                int newNoOfPlayers = playersLoggedIn + 1;
                String updateStmt = "update playerslogged set NoOfPlayers = ?";
                PreparedStatement updatePreparedStmt = DBManager.getConnection().prepareStatement(updateStmt);
                updatePreparedStmt.setInt(1, newNoOfPlayers);
                updatePreparedStmt.execute();
            }
            return playersLoggedIn;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    private User login(HttpSession session, String username, int playersLogged) {
        User user = UserController.createUserWithLoggedPlayers(username, playersLogged);
        session.setAttribute("user", user);
        return user;
    }
}
