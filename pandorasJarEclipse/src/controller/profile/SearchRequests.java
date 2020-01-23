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

@WebServlet(value = "/SearchRequests")
public class SearchRequests extends HttpServlet {
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        Integer received = null;
        try{
            received = Integer.parseInt(req.getParameter("received"));
            id = Integer.parseInt(req.getParameter("id"));
        }
        catch(NumberFormatException e)
        {}
        ArrayList<User> requests = null;
        if(received != null && received == 1)
            requests = (ArrayList<User>) req.getSession().getAttribute("receivedRequests");
        else if(received != null && received == 0)
            requests = (ArrayList<User>) req.getSession().getAttribute("sentRequests");
        if(requests != null && !requests.isEmpty()) {
            if (id != null) {
                for (User u : requests) {
                    if (u.getId() == id) {
                        String jsonSend = gson.toJson(u);
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().println(jsonSend);
                        resp.getWriter().flush();
                        break;
                    }
                }
            } else {
                String jsonSend = gson.toJson(requests);
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().println(jsonSend);
                resp.getWriter().flush();
            }
        }
    }
}
