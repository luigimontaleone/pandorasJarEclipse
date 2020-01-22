package controller.profile;

import model.UserBox;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(value="/chat")
public class Chat extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int userId = (int) req.getSession().getAttribute("userId");
        ArrayList<UserBox> users = DAOFactory.getInstance().makeUserDAO().getUsersBox(userId);
        users = (ArrayList<UserBox>) users.stream().distinct().collect(Collectors.toList());
        req.setAttribute("usersBox", users);
        RequestDispatcher rd = req.getRequestDispatcher("chat.jsp");
        rd.forward(req, resp);
    }
}
