package controller.profile;

import model.User;
import persistence.DAOFactory;
import persistence.FriendRequestsDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/friendRequests")
public class FriendRequests extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser;
        if(req.getSession().getAttribute("userId") != null){
            idUser = (int) req.getSession().getAttribute("userId");
            FriendRequestsDAO dao = DAOFactory.getInstance().makeFriendRequestsDAO();
            ArrayList<User> sentRequests = dao.getReceivedSentRequests(idUser, false);
            ArrayList<User> receivedRequests = dao.getReceivedSentRequests(idUser, true);
            req.getSession().setAttribute("sentRequests", sentRequests);
            req.getSession().setAttribute("receivedRequests", receivedRequests);
            RequestDispatcher rd = req.getRequestDispatcher("friendRequests.jsp");
            rd.forward(req, resp);
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("notLogged.jsp");
            rd.forward(req,resp);
        }

    }
}
