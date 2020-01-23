package controller.profile;

import com.google.gson.Gson;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/SearchListFriend")
public class SearchListFriend extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idFriend = null;
        try{
            idFriend = Integer.parseInt(req.getParameter("id"));
        }
        catch(NumberFormatException e)
        {}
        ArrayList<User> friends = (ArrayList<User>) req.getSession().getAttribute("friends");
        if(friends != null && !friends.isEmpty())
        {
            if(idFriend != null)
            {
                for(User u: friends)
                {
                    if(u.getId() == idFriend)
                    {
                        String jsonSend = gson.toJson(u);
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().println(jsonSend);
                        resp.getWriter().flush();
                        break;
                    }
                }

            }
            else
            {
                String jsonSend = gson.toJson(friends);
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(jsonSend);
                resp.getWriter().flush();
            }
        }

    }
}
