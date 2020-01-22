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

@WebServlet(value = "/DeleteRequestFriend")
public class DeleteRequestFriend extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        ArrayList<User> requests = (ArrayList<User>) req.getSession().getAttribute("sentRequests");
        if(id != null)
        {
            DAOFactory.getInstance().makeFriendRequestsDAO().deleteRequestFriend((int)req.getSession().getAttribute("userId"), id);
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
