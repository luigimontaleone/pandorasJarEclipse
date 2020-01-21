package controller.profile;

import model.User;
import persistence.DAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/friendsList")
public class friendsList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User principale = null;
        int idUser;
        if(req.getSession().getAttribute("userId") != null){
            idUser = (int) req.getSession().getAttribute("userId");
            principale = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(idUser);
            req.getSession().setAttribute("friends", principale.getFriends());
            RequestDispatcher rd = req.getRequestDispatcher("friendsList.jsp");
            rd.forward(req, resp);
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("notLogged.jsp");
            rd.forward(req,resp);
        }
    }
}
