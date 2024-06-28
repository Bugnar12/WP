package servlets;

import DB.DBManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Move")
public class Move extends HttpServlet {
    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String column = req.getParameter("column");
        String row = req.getParameter("row");
        System.out.println("Column: " + column + "\nRow: " + row + "\n");
        User user = (User) req.getSession().getAttribute("user");

        Integer activePlayerAttribute = (Integer) req.getSession().getAttribute("activePlayer");
        int activeUser = (activePlayerAttribute != null) ? activePlayerAttribute : 0;

        char play = 'x';
        int value;
        if (user.getPlayerNumber() == 1)
            play = '0';
        int position = 0;
        if (!column.equals("") && !row.equals("")) {
            int columnValue = Integer.parseInt(column);
            int rowValue = Integer.parseInt(row);

            position = rowValue * 3 + columnValue - 4;

        }
        System.out.println("Position: " + position);
        int ActivePlayer = -1;
        String Board = "";
        String sql = "SELECT * FROM boardstate";
        try {
            PreparedStatement stmt = DBManager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Board = result.getString("boardstate");
                ActivePlayer = result.getInt("ActivePlayer");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("BoardState: " + Board);
        System.out.println("ActivePlayer: " + ActivePlayer);
        System.out.println("User.getPlayerNumber: " + user.getPlayerNumber() + ", activeUser: " + activeUser);
        if (user.getPlayerNumber() == activeUser) {
            if (position < 0 || position > 8) {
                resp.sendRedirect("board.jsp");
                return;
            }
            if (Board.charAt(position) != '-') {
                resp.sendRedirect("board.jsp");
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();

            for (int index = 0; index < 9; index++) {
                if (position == index) {
                    stringBuilder.append(play);
                } else {
                    stringBuilder.append(Board.charAt(index));
                }
            }
            value = CheckEnd(play, stringBuilder.toString());
            req.getSession().setAttribute("Board", stringBuilder.toString());
            if (ActivePlayer == 0) {
                req.getSession().setAttribute("activePlayer", 1);
                String update = "UPDATE  boardstate SET BoardState='" + stringBuilder.toString() + "', ActivePlayer='" + 1 + "';";
                try {
                    PreparedStatement stmt = DBManager.getConnection().prepareStatement(update);
                    stmt.execute();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();

                }
            } else {
                req.getSession().setAttribute("activePlayer", 0);
                String update = "UPDATE boardstate SET BoardState='" + stringBuilder.toString() + "', ActivePlayer='" + 0 + "';";
                try {
                    PreparedStatement stmt = DBManager.getConnection().prepareStatement(update);
                    stmt.execute();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();

                }
            }
            if (value == 0) {
                resp.sendRedirect("board.jsp");
            } else if (value == 1) {
                resp.sendRedirect("won.jsp");
            } else {
                resp.sendRedirect("lost.jsp");
            }
        } else {
            value = CheckEnd(play, Board);
            req.getSession().setAttribute("Board", Board);
            req.getSession().setAttribute("activePlayer", ActivePlayer);
            if (value == 0) {
                resp.sendRedirect("board.jsp");
            } else if (value == 1) {
                resp.sendRedirect("won.jsp");
            } else {
                resp.sendRedirect("lost.jsp");
            }

        }
    }

    private int CheckEnd(char play, String Board) {
        if (checkCond(0, 1, 2, play, Board) != 0) {
            return checkCond(0, 1, 2, play, Board);
        }
        if (checkCond(3, 4, 5, play, Board) != 0) {
            return checkCond(3, 4, 5, play, Board);
        }
        if (checkCond(6, 7, 8, play, Board) != 0) {
            return checkCond(6, 7, 8, play, Board);
        }
        if (checkCond(0, 3, 6, play, Board) != 0) {
            return checkCond(0, 3, 6, play, Board);
        }
        if (checkCond(1, 4, 7, play, Board) != 0) {
            return checkCond(1, 4, 7, play, Board);
        }
        if (checkCond(2, 5, 8, play, Board) != 0) {
            return checkCond(2, 5, 8, play, Board);
        }
        if (checkCond(0, 4, 8, play, Board) != 0) {
            return checkCond(0, 4, 8, play, Board);
        }
        if (checkCond(2, 4, 6, play, Board) != 0) {
            return checkCond(2, 4, 6, play, Board);
        }
        if (Board.indexOf('-') == -1) {
            return 2;
        }
        return 0;
    }

    private int checkCond(int position1, int position2, int position3, char play, String Board) {
        if (Board.charAt(position1) == play && Board.charAt(position2) == play && Board.charAt(position3) == play) {
            if (Board.charAt(position1) == play)
                return 1;
            else if (Board.charAt(position1) != '-')
                return 2;
        }
        return 0;
    }
}