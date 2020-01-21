package controller.profile;

import com.google.gson.Gson;
import model.User;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/DeleteFriend")
public class DeleteFriend extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        ArrayList<User> friends = (ArrayList<User>) req.getSession().getAttribute("friends");
        if(id != null)
        {
            DAOFactory.getInstance().makeUserDAO().deleteUserFriend((int)req.getSession().getAttribute("userId"), id);
            for(User u: friends){
                if(u.getId() == id)
                {
                    friends.remove(u);
                    break;
                }
            }
            String jsonSend = gson.toJson(friends);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(jsonSend);
            resp.getWriter().flush();

        }
    }
}
