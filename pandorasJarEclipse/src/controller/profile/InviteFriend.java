package controller.profile;

import com.google.gson.Gson;
import model.User;
import persistence.DAOFactory;
import persistence.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/inviteFriend")
public class InviteFriend extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("inviteFriend.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer newFriend = null;
        try{
            newFriend = Integer.parseInt(req.getParameter("id"));
        }catch(NumberFormatException e)
        {}

        if(newFriend != null)
        {
            UserDAO dao = DAOFactory.getInstance().makeUserDAO();
            User friend = dao.getUserByIdUser(newFriend);
            User principale = dao.getUserByIdUser((int) req.getSession().getAttribute("userId"));
            if(friend != null)
            {
                String jsonSend;
                if(principale.addFriend(friend))
                {
                    dao.addRequestUserFriend(friend.getId(), principale.getId());
                    jsonSend = "ok";
                }
                else
                {
                    jsonSend = "notok";
                }
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().print(jsonSend);
                resp.getWriter().flush();
            }
        }

    }
}
