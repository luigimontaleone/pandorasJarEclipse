package controller.library;

import model.Game;
import model.User;
import persistence.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(value = "/addComment", name = "addComment")
public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(401);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User actual = DAOFactory.getInstance().makeUserDAO().getUserByIdUser((int) req.getSession().getAttribute("userId"));
        DAOFactory.getInstance().makeReviewDAO().addCommentForGame(Integer.parseInt(req.getParameter("game")), Integer.parseInt(req.getParameter("stars")),
                req.getParameter("content"), actual.getId(), actual.getUsername());
    }
}
