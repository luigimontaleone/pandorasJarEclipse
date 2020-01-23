package controller.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import persistence.DAOFactory;

@WebServlet(value = "/profile")
public class GeneralProfile extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer idUser = null;
		User principale = null;
		if(req.getSession().getAttribute("userId") != null){
			idUser = (int) req.getSession().getAttribute("userId");
			principale = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(idUser);
		}

		if(req.getParameter("id") == null || (idUser != null && Integer.parseInt(req.getParameter("id")) == idUser))
		{
			req.getSession().setAttribute("user", principale);
			req.getSession().setAttribute("friend", false);
		}
		else
		{
			int idFriend = Integer.parseInt(req.getParameter("id"));
			User friend = DAOFactory.getInstance().makeUserDAO().getUserByIdUser(idFriend);
			req.getSession().setAttribute("user", friend);
			req.getSession().setAttribute("friend", true);
		}
		RequestDispatcher rd = null;
		rd = req.getRequestDispatcher("profile.jsp");
		rd.forward(req, resp);

	}

}
