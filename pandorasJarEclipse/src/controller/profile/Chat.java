package controller.profile;

import model.UserBox;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value="/chat")
public class Chat extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int userId = 5;//(int) req.getSession().getAttribute("userId");
        ArrayList<UserBox> users = DAOFactory.getInstance().makeUserDAO().getUsersBox(userId);
        for(UserBox u : users)
        {
            System.out.println(u);
        }
    }
}
