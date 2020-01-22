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

@WebServlet(value = "/addFriend")
public class AddFriend extends HttpServlet {
    private Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        ArrayList<User> requests = (ArrayList<User>) req.getSession().getAttribute("receivedRequests");
        if(id != null)
        {
            DAOFactory.getInstance().makeFriendRequestsDAO().addUserFriend(id, (int)req.getSession().getAttribute("userId"));
            for(User u: requests){
                if(u.getId() == id)
                {
                    requests.remove(u);
                    break;
                }
            }
            String jsonSend = gson.toJson(requests);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(jsonSend);
            resp.getWriter().flush();

        }
    }
}
